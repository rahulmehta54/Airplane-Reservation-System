package com.ars.dto;

import java.util.Comparator;

public class FlightTimeComparator implements Comparator<Flight>{

	@Override
	public int compare(Flight f1, Flight f2) {
		if(f1.getDepartureTime()==f2.getDepartureTime())
		return 0;
		else if(f1.getDepartureTime().isAfter(f2.getDepartureTime()))
			return 1;
		else
			return -1;
	}

}
