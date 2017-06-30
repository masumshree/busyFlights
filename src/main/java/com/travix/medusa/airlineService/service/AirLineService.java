package com.travix.medusa.airlineService.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;

public interface AirLineService {
	String findAllAvailableFlights(BusyFlightsRequest busyFlightsRequest);
}
