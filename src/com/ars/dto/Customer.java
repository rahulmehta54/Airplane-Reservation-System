package com.ars.dto;

import java.time.LocalDate;

public class Customer 
{
	private int customerID;
	private String IDCardNumber;
	private String name;
	private String email;
	private String password;
	private LocalDate dateOfBirth;
	private String address;
	private String phoneNumber;
	private String gender;
	private String IDCardType;
	
	
	
	
	@Override
	public String toString() {
		return "1.customerID=" + customerID + "\n2.IDCardNumber=" + IDCardNumber + "\n3.name=" + name + "\n4.email="
				+ email + "\n5.password=" + password + "\n6.dateOfBirth=" + dateOfBirth + "\n7.address=" + address
				+ "\n8.phoneNumber=" + phoneNumber + "\n9.gender=" + gender + "\n10.IDCardType=" + IDCardType ;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getIDCardNumber() {
		return IDCardNumber;
	}
	public void setIDCardNumber(String iDCardNumber) {
		IDCardNumber = iDCardNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIDCardType() {
		return IDCardType;
	}
	public void setIDCardType(String iDCardType) {
		this.IDCardType = iDCardType;
	}
	
	
	
	
}
