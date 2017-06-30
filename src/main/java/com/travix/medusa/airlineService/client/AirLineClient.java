package com.travix.medusa.airlineService.client;

import java.io.BufferedReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;



@Service("AirLineClient")
public class AirLineClient {
	public String SendGetCrazy(CrazyAirRequest crazyReq, String crazyUrl) {
		String crazyHTTP = "http"; // https
		String crazyPort = "80";
		URL objUrl;
		BufferedReader in;
		StringBuffer response = null;
		try {
			
			InetSocketAddress proxyinet = new InetSocketAddress(crazyHTTP, Integer.parseInt(crazyPort));
			Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyinet);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(null != response) {
			 return response.toString();
		 } else {
			 return crazyFlightJson;
		 }

	}
	/*
	 * This method is use to send request 
	 */
	public String SendGetTough(ToughJetRequest toughReq, String toughUrl) {
		String crazyHTTP = "http"; // https
		String crazyPort = "82";
		URL objUrl;
		BufferedReader in;
		StringBuffer response = null;
		try {

			InetSocketAddress proxyinet = new InetSocketAddress(crazyHTTP, Integer.parseInt(crazyPort));
			Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyinet);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null != response) {
			 return response.toString();
		 } else {
			 return toughFlightJson;
		 }
}
	public String crazyFlightJson =  ""
            + "[{\"airline\": \"Biritish Airways\",\"price\": \"9865\",\"cabinclass\": \"B\",\"departureAirportCode\": \"LHR\","
            + "\"destinationAirportCode\": \"JFK\",   \"departureDate\": \"2017-12-09\",\"arrivalDate\": \"2017-12-07\"}, "
            + "{\"airline\": \"Flydubai\",\"price\": \"9689\",      \"cabinclass\": \"E\",  \"departureAirportCode\": \"LHR\",  "
            + "\"destinationAirportCode\": \"AMS\",   \"departureDate\": \"2017-11-19\",    \"arrivalDate\": \"2017-11-20\"     },    "
            + "{\"airline\": \"Air Asia\",  \"price\": \"4895\",      \"cabinclass\": \"E\",  \"departureAirportCode\": \"LHR\",  "
            + "\"destinationAirportCode\": \"AMS\",   \"departureDate\": \"2017-11-08\",    \"arrivalDate\": \"2017-12-14\" } ]";;
	
	public String toughFlightJson =  ""
            + "[{\"carrier\": \"Jet Airways\",\"basePrice\": \"8436\",\"tax\": \"1234\",\"discount\": \"3\",\"departureAirportName\": \"LHR\","
            + "\"arrivalAirportName\": \"AMS\", \"outboundDateTime\": \"2017-10-15\",\"inboundDateTime\": \"2017-12-13\"},      "
            + "{\"carrier\": \"Go Air\",\"basePrice\": \"7652\",\"tax\": \"1067\",\"discount\": \"2\",      \"departureAirportName\": \"LHR\",  "
            + "\"arrivalAirportName\": \"AMS\", \"outboundDateTime\": \"2017-11-16\", \"inboundDateTime\": \"2017-12-12\" },    "
            + "{\"carrier\": \"Virgin America\", \"basePrice\": \"6126\",\"tax\": \"999\",\"discount\": \"4\",  \"departureAirportName\": \"LHR\",  "
            + "\"arrivalAirportName\": \"AMS\", \"outboundDateTime\": \"2017-11-09\", \"inboundDateTime\": \"2017-12-23\" }]";


}
