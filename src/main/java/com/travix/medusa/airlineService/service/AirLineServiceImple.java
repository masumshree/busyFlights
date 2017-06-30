package com.travix.medusa.airlineService.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.airlineService.client.AirLineClient;
import com.travix.medusa.airlineService.utility.AirlineUtility;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.mesdus.airlineSevice.supplier.AirlineSuplier;



@Service("AirLineService")
/*
 * This service class. 
 */
public class AirLineServiceImple implements AirLineService{
	@Autowired
	AirLineClient airLineClient;
	private static String flightData;
	@Autowired
	AirlineUtility airlineUtility;
	@Override
	public String findAllAvailableFlights(BusyFlightsRequest busyFlightsRequest) {
		
 		//CrazyAir
		CrazyAirRequest crazyAirRequest = new CrazyAirRequest();
		crazyAirRequest.setDepartureDate(busyFlightsRequest.getDepartureDate());
		crazyAirRequest.setReturnDate(busyFlightsRequest.getReturnDate());
		crazyAirRequest.setOrigin(busyFlightsRequest.getOrigin());
		crazyAirRequest.setDestination(busyFlightsRequest.getDestination());
		crazyAirRequest.setPassengerCount(busyFlightsRequest.getNumberOfPassengers());
		
		//ToughJet
		ToughJetRequest toughJetRequest = new ToughJetRequest();
		toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
		toughJetRequest.setTo(busyFlightsRequest.getDestination());
		toughJetRequest.setOutboundDate(busyFlightsRequest.getDepartureDate());
		toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
		toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());
		
		//JSON CrazyAirResponse being stored into List
				List<CrazyAirResponse> crazyFlightList = null;
				
				//JSON ToughJetResponse being stored into List
				List<ToughJetResponse> toughJetFlightList = null;
				
								
				try {

					if (null != (airLineClient.SendGetCrazy(crazyAirRequest, "crazyUrl"))) {
						crazyFlightList = (List<CrazyAirResponse>) airlineUtility.jSonToObjectCrazyAir(airLineClient.SendGetCrazy(crazyAirRequest, "crazyUrl"));
					

					}  if (null != (airLineClient.SendGetTough(toughJetRequest, "toughUrl"))) {
						toughJetFlightList = (List<ToughJetResponse>) airlineUtility.jSonToObjectToughJet(airLineClient.SendGetTough(toughJetRequest, "toughUrl"));
					} else {
									flightData = populateDummyFlights(busyFlightsRequest);
						}
					flightData = convertListToJson(crazyFlightList, toughJetFlightList, busyFlightsRequest);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					flightData = populateDummyFlights(busyFlightsRequest);
					e.printStackTrace();
				}

				return flightData;
	}
	
	/*
	 * this method is use to populate the data
	 */
	private String populateDummyFlights(BusyFlightsRequest busyFlightsRequest){
		String flightsData = null ;
		List<CrazyAirResponse> crazyFlightList = (List<CrazyAirResponse>) airlineUtility.jSonToObjectCrazyAir(airLineClient.crazyFlightJson);
		
		//JSON response being converted into List
		List<ToughJetResponse> toughJetFlightList = (List<ToughJetResponse>) airlineUtility.jSonToObjectToughJet(airLineClient.toughFlightJson);
		flightsData = convertListToJson(crazyFlightList, toughJetFlightList, busyFlightsRequest);		
		
		return flightsData;
	}
	/*
	 * This method is use to convert List to Json representation.
	 */
	private  String convertListToJson(List<CrazyAirResponse> crazyFlightList, List<ToughJetResponse> toughJetFlightList, BusyFlightsRequest busyFlightsRequest) {
		List<Object> allFlightsList = new ArrayList<Object>();
		String flightsData = null;
		// merge list
		allFlightsList.addAll((Collection<? extends Object>) crazyFlightList);
        allFlightsList.addAll((Collection<? extends Object>) toughJetFlightList);

        System.out.println("-------without Sorted fare --------");
		for(Object obj:allFlightsList)
			System.out.println(obj);
		allFlightsList = (List<Object>) airlineUtility.sortOnFlightsFare(allFlightsList);
		System.out.println("------Sorted fare --------");
		for(Object obj:allFlightsList)
			System.out.println(obj);
	
		allFlightsList = createBusyFlightsResponse(allFlightsList, busyFlightsRequest);
		System.out.println(allFlightsList);
		
		try {
	        	ObjectMapper mapper = new ObjectMapper();
	        	mapper.writeValue(System.out, allFlightsList);
	        	flightsData =	mapper.writeValueAsString(allFlightsList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
			return flightsData;
		
	}
	/*
	 * this method use to share the response.
	 */
	private List<Object> createBusyFlightsResponse(List<Object> allFlightsList, BusyFlightsRequest busyFlightsRequest) {
		BusyFlightsResponse busyFlightsResponse = null;
		List<Object> responseList = new ArrayList<Object>();
		for (Object object : allFlightsList) {
			if (object instanceof CrazyAirResponse){
				busyFlightsResponse = new BusyFlightsResponse();
				busyFlightsResponse.setAirline(((CrazyAirResponse) object).getAirline());
				busyFlightsResponse.setSupplier(AirlineSuplier.SUPPLIER_CRAZY_AIR);
				busyFlightsResponse.setFare(((CrazyAirResponse) object).getPrice());
				busyFlightsResponse.setDepartureAirportCode(((CrazyAirResponse) object).getDepartureAirportCode());
				busyFlightsResponse.setDestinationAirportCode(((CrazyAirResponse) object).getDestinationAirportCode());
				busyFlightsResponse.setDepartureDate(busyFlightsRequest.getDepartureDate());
				busyFlightsResponse.setArrivalDate(busyFlightsRequest.getReturnDate());
				
				responseList.add(busyFlightsResponse);
				
			}else if(object instanceof ToughJetResponse){
				busyFlightsResponse = new BusyFlightsResponse();
				busyFlightsResponse.setAirline(((ToughJetResponse) object).getCarrier());
				busyFlightsResponse.setSupplier(AirlineSuplier.SUPPLIER_TOUGH_JET);
				busyFlightsResponse.setFare((((ToughJetResponse) object).getBasePrice() + ((ToughJetResponse) object).getTax()) - 
						(((ToughJetResponse) object).getDiscount()/100)* (((ToughJetResponse) object).getBasePrice()));
				busyFlightsResponse.setDepartureAirportCode(((ToughJetResponse) object).getDepartureAirportName());
				busyFlightsResponse.setDestinationAirportCode(((ToughJetResponse) object).getArrivalAirportName());
				busyFlightsResponse.setDepartureDate(busyFlightsRequest.getDepartureDate());
				busyFlightsResponse.setArrivalDate(busyFlightsRequest.getReturnDate());
				
				responseList.add(busyFlightsResponse);
			}
		}
		return responseList;
	}

	
	

}
