package com.ars.dto;

public class ClassDetail 
{
	private String classId;
	private String type;
	private Double price;
	private int totalSeat;
	private String seatNumber;
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public ClassDetail(String classId, String type, Double price, int totalSeat, String seatNumber) {
		super();
		this.classId = classId;
		this.type = type;
		this.price = price;
		this.totalSeat = totalSeat;
		this.seatNumber = seatNumber;
	}
	public ClassDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ClassDetail [classId=" + classId + ", type=" + type + ", price=" + price + ", totalSeat=" + totalSeat
				+ ", seatNumber=" + seatNumber + "]";
	}
	
	
	
	
}
