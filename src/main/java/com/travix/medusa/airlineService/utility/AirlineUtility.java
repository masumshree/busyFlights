package com.travix.medusa.airlineService.utility;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.airlineService.utility.AirLineSortedOrder;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
@Service("AirlineUtility")
public class AirlineUtility {

public  List<CrazyAirResponse> jSonToObjectCrazyAir(String responseJSON) {
	List<CrazyAirResponse> list = null;
	
	ObjectMapper mapper = new ObjectMapper();
	try {
		list = mapper.readValue(responseJSON, new TypeReference<List<CrazyAirResponse>>(){});
	} catch (IOException e) {
		e.printStackTrace();
	}
	return list;
}

public  List<ToughJetResponse> jSonToObjectToughJet(String responseJSON) {
	List<ToughJetResponse> list = null;
	
	ObjectMapper mapper = new ObjectMapper();
	try {
		list = mapper.readValue(responseJSON, new TypeReference<List<ToughJetResponse>>(){});
	} catch (IOException e) {
		e.printStackTrace();
	}
	return list;
}

/*
 *  sorted list will be returned.
 * 
 */
public List<?> sortOnFlightsFare(List<Object> allFlightsList) {
	AirLineSortedOrder airLineSortedOrder=new AirLineSortedOrder();
	airLineSortedOrder.sortAllFlightList(allFlightsList);
	
	return allFlightsList;
}
}
