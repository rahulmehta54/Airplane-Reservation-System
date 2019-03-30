package com.ars.dto;

import java.io.Serializable;

public class Passenger implements Serializable
{
	@Override
	public String toString() {
		return "Passenger [ticket=" + ticket + ", passengerName=" + passengerName + ", age=" + age + ", gender="
				+ gender + "]";
	}
	private Ticket ticket;
    private String passengerName;
	private int age;
	private String gender;
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
}
