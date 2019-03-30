package com.ars.dto;

import java.time.LocalDate;

public class Ticket
{   private String ticketId;
	private String status;
	private Flight flightdetails;
	private  double refundAmount;
	private double cancellationCharge;
	private LocalDate cancelDate;
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Flight getFlightdetails() {
		return flightdetails;
	}
	public void setFlightdetails(Flight flightdetails) {
		this.flightdetails = flightdetails;
	}
	public double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public double getCancellationCharge() {
		return cancellationCharge;
	}
	public void setCancellationCharge(double cancellationCharge) {
		this.cancellationCharge = cancellationCharge;
	}
	public LocalDate getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(LocalDate cancelDate) {
		this.cancelDate = cancelDate;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", status=" + status + ", flightdetails=" + flightdetails
				+ ", refundAmount=" + refundAmount + ", cancellationCharge=" + cancellationCharge + ", cancelDate="
				+ cancelDate + "]";
	}
	
}
