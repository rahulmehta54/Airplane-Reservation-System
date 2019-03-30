package com.ars.dto;

import java.util.Comparator;

public class FlightNameComparator implements Comparator<Flight>{

	@Override
	public int compare(Flight f1, Flight f2) {
		
		return f1.getAirlineName().compareTo(f2.getAirlineName());
	}

}
