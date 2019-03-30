package com.ars.dto;

import java.util.Comparator;

public class FlightPriceComparator implements Comparator<Flight>{

	@Override
	public int compare(Flight f1, Flight f2) {
		return f1.getClassDetail().getPrice().compareTo(f2.getClassDetail().getPrice());
	}

}
