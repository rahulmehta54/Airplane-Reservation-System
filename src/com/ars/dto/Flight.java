package com.ars.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight 
{
	private String flightId;
	private String airlineName;
	private String fromLocation;
	private String toLocation;
	private LocalDate departuredate;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private int availableSeats;
	private ClassDetail classDetail;
	private Integer noOfTraveller;
	
	public Integer getNoOfTraveller() {
		return noOfTraveller;
	}
	public void setNoOfTraveller(Integer noOfTraveller) {
		this.noOfTraveller = noOfTraveller;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public LocalDate getDeparturedate() {
		return departuredate;
	}
	public void setDeparturedate(LocalDate departuredate) {
		this.departuredate = departuredate;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public ClassDetail getClassDetail() {
		return classDetail;
	}
	public void setClassDetail(ClassDetail classDetail) {
		this.classDetail = classDetail;
	}
@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", airlineName=" + airlineName + ", fromLocation=" + fromLocation
				+ ", toLocation=" + toLocation + ", departuredate=" + departuredate + ", departureTime=" + departureTime
				+ ", arrivalTime=" + arrivalTime + ", AvailableSeats =" + availableSeats + ", classDetail=" + classDetail
				+ ", noOfTraveller=" + noOfTraveller + "]";
	}


	
	
}
