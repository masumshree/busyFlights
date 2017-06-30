package com.travix.medusa.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travix.medusa.airlineService.service.AirLineService;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;

@RestController
@RequestMapping("/flightApi")
/*
 * This class is use process the request.
 */
public class AirLineController {
@Autowired
AirLineService airLineService;
String Erorr_Messge =" Either maxumum 4 passenger is allowed or minimum 1 passenger is allowed";
@RequestMapping(value = "/search/{origin}/{destination}/{departureDate}/{returnDate}/{numberOfPassengers}", method = RequestMethod.GET)
public ResponseEntity<?> getFlightResponse(@PathVariable("origin") String origin,
											@PathVariable("destination") String destination,
											@PathVariable("departureDate") String departureDate,
											@PathVariable("returnDate") String returnDate,
											@PathVariable("numberOfPassengers") int numberOfPassengers){
	
	System.out.println("Fetching available Airline Flights  with origin {} , destination {} , departureDate {}, returnDate {}, numberOfPassengers {} ::"+ 
															      origin+ " "+destination+" "+departureDate +" "+returnDate+ " "+numberOfPassengers);

	BusyFlightsRequest busyFlightsrequest = new BusyFlightsRequest();
	busyFlightsrequest.setOrigin(origin);
	busyFlightsrequest.setDestination(destination);
	busyFlightsrequest.setDepartureDate(departureDate);
	busyFlightsrequest.setReturnDate(returnDate);
	busyFlightsrequest.setNumberOfPassengers(numberOfPassengers);
	String airLineSearchData = null;
	
	if(((busyFlightsrequest.getNumberOfPassengers())==0) || ((busyFlightsrequest.getNumberOfPassengers())>4)){
		return new ResponseEntity(Erorr_Messge, HttpStatus.FORBIDDEN);
	}else {
		airLineSearchData = airLineService.findAllAvailableFlights(busyFlightsrequest);
		return new ResponseEntity<>(airLineSearchData, HttpStatus.OK);
}

	
}

}
