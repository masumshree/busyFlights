package com.travix.medusa.busyflights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.travix.medusa.airline.controller,"
		+ "com.travix.medusa.airlineService.client,"
		+ "com.travix.medusa.airlineService.service, com.travix.medusa.airlineService.utility"})
/*
 * This class is use start application
 */
public class BusyFlightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusyFlightsApplication.class, args);
	}
}
