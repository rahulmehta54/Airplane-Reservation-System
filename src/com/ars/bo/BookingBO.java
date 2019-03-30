package com.ars.bo;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.ars.controller.BookingController;
import com.ars.dao.BookingDAO;
import com.ars.dto.Card;
import com.ars.dto.ClassDetail;
import com.ars.dto.Flight;
import com.ars.dto.Passenger;
import com.ars.dto.Ticket;
public class BookingBO
{

	private static final Logger LOG=Logger.getLogger(BookingBO.class);
	/**
	 *  Validate the Search Flight From Location and To Location - checking weather from location and to location contain anything other than letters 
	 * @param flightSearch - List of flights
	 * @param errorList - if method returns any error it will be added to errorList
	 * @return boolean variable - returns true if from_location and to_location contain only letters else return false  
	 */
	
	
	
	private boolean locationValidation(Flight flightSearch, List<String> errorList)
	{
		
		boolean flag=true;
		char a[]=flightSearch.getFromLocation().toCharArray();
		char b[]=flightSearch.getToLocation().toCharArray();
		for(int i=0;i<a.length;i++)
		{
			if(!Character.isLetter(a[i]))
			{
				flag=false;
				String error="Enter valid From Location";
				LOG.info("Invalid From Location");
				errorList.add(error);
				break;
			}
		}
		for(int i=0;i<b.length;i++)
		{
			if(!Character.isLetter(b[i]))
			{
				flag=false;
				String error="Enter valid To Location";
				LOG.info("Invalid To Location");
				errorList.add(error);
				break;
			}
		}
		return flag;
		
	}
	/**
	 * Validate the Departure Date 
	 * @param flightSearch - list of flights
	 * @param errorList - errors (if any) will be added to error list 
	 * @return boolean variable. if Departure Date is greater than current date then returns true otherwise false
	 */
	private boolean departureDateValidation(Flight flightSearch, List<String> errorList)
	{
		LocalDate dateTime =flightSearch.getDeparturedate();
		try
		{
			LocalDate currentDate=LocalDate.now();
			if(dateTime.compareTo(currentDate)>=0)
			{
				return true;
			}
			else
			{
				String error="Enter Date greater than current date";
				errorList.add(error);
				LOG.info("Invalid Date");
			}
		}
		catch (Exception e) 
		{
			String error="Enter Date In 'yyyy-MM-dd' format only";
			errorList.add(error);
			LOG.error(e.getMessage());
			return false;
		}
		return false;
	}
  /**
   * Validate the Flight Details 
   * @param flightSearch - List of flights
   * @param errorList -  errors (if any) will be added to error list 
   * @return Flight Search List  - returns list of flights if number of travelers are less than or equal to 9.
   */
	public List<Flight> validateSearch(Flight flightSearch, List<String> errorList)
	{
		List<Flight> FlightSearchList=new ArrayList<>();
		BookingDAO bookingDAO=null;
		if(locationValidation(flightSearch,errorList) && departureDateValidation(flightSearch,errorList)&& noOfTravellersValidation(flightSearch,errorList)&& classTypeValidation(flightSearch,errorList))
		{
			bookingDAO=new BookingDAO();
			FlightSearchList= bookingDAO.findSearchFlight(flightSearch);
			for(int i=0;i<FlightSearchList.size();i++)
			{	
				flightSearch.setFlightId(FlightSearchList.get(i).getFlightId());
				 int bookedSeats=bookingDAO.bookedSeats(flightSearch);
				 if(flightSearch.getClassDetail().getType().equalsIgnoreCase("Economy")||flightSearch.getClassDetail().getType().equalsIgnoreCase("business"))
				 {	 int availableSeats=100-bookedSeats;
					 if(flightSearch.getNoOfTraveller()<=availableSeats)
					 {					
						 if(flightSearch.getNoOfTraveller()>=9)
						 {
							 errorList.add("Maximum of 9 travellers allowed");
							 LOG.info("No Of Travleers Invalid");
						 }
					 }
					 else
					 {
					
						 FlightSearchList.remove(i);
					 }
				 }
				 else
				 {
					 int availableSeats=50-bookedSeats;
					 if(flightSearch.getNoOfTraveller()<=availableSeats)
					 {	
						 if(flightSearch.getNoOfTraveller()>=9)
						 {
							 errorList.add("Maximum of 9 travellers allowed");
							 LOG.info("No Of Travleers Invalid");
						 }
					 }
					 else
					 {
						 FlightSearchList.remove(i);
					 }
				 }
			}
		}
		return FlightSearchList;	
	}
	/**
	 * Validate the class Type
	 * @param flightSearch - List of flights
	 * @param errorList - errors (if any) will be added to error list
	 * @return boolean variable - returns true if class details are validated
	 */
	private boolean classTypeValidation(Flight flightSearch, List<String> errorList)
	{
		String classType=flightSearch.getClassDetail().getType();
		if(classType.equalsIgnoreCase("Economy")||classType.equalsIgnoreCase("Business")||classType.equalsIgnoreCase("First")|| classType.equalsIgnoreCase("Premium"))
		{
			return true;
		}
		String error="Enter Class Type on (economy/business/first class/Premium)";
		errorList.add(error);
		return false;
	}
	private boolean noOfTravellersValidation(Flight flightSearch, List<String> errorList)
	{
		try
		{
		int Travellers=flightSearch.getNoOfTraveller();
			return true;
		}
		catch (Exception e)
		{
			String error="Enter only Integer value in no of Traveller Field";
			errorList.add(error);	
		}
		return false;
	}
	/**
	 * 
	 * @param flightConfirm
	 * @return flight which user book
	 */
	public Flight validateBookingID(Flight flightConfirm)
	{
		BookingDAO bookingDAO=new BookingDAO();
		int updatedAvailableSeats=0;
		int bookedSeats=bookingDAO.bookedSeats(flightConfirm);
		if(flightConfirm.getClassDetail().getType().equalsIgnoreCase("Economy")||flightConfirm.getClassDetail().getType().equalsIgnoreCase("business"))
		{
				 updatedAvailableSeats=100-bookedSeats;
		}
		else
		{
				 updatedAvailableSeats=50-bookedSeats;
		}
		updatedAvailableSeats=updatedAvailableSeats-flightConfirm.getNoOfTraveller();			 
		String classtype=flightConfirm.getClassDetail().getType().trim();
		for(int j=0;j<flightConfirm.getNoOfTraveller();j++)
		{
						
			String s=(generateSeatNumber(classtype,updatedAvailableSeats++));
			BookingController.seatNumberList.add(s);
						
		}
					return flightConfirm;
	}	
		/**
		 * Generate New Seat  Number for Each Passenger
		 * @param classtype
		 * @param updatedAvailableSeats
		 * @return new seat number
		 */
	private String generateSeatNumber(String classtype, int updatedAvailableSeats)
	{
		if(classtype.equalsIgnoreCase("Economy"))
		{
			int currentSeatNumber=100-updatedAvailableSeats;
			if(currentSeatNumber>0 && currentSeatNumber<=9)
			return "E00"+currentSeatNumber;
			else
				return "E0"+currentSeatNumber;
			
		}
		else if(classtype.equalsIgnoreCase("Business"))
		{
			int currentSeatNumber=100-updatedAvailableSeats;
			if(currentSeatNumber>0 && currentSeatNumber<=9)
			return "B00"+currentSeatNumber;
			else
				return "B0"+currentSeatNumber;
		}
		else if(classtype.equalsIgnoreCase("First"))
		{
			int currentSeatNumber=50-updatedAvailableSeats;
			if(currentSeatNumber>0 && currentSeatNumber<=9)
			return "F00"+currentSeatNumber;
			else
				return "F0"+currentSeatNumber;
		}
		else if(classtype.equalsIgnoreCase("Premium"))
		{
			int currentSeatNumber=50-updatedAvailableSeats;
			if(currentSeatNumber>0 && currentSeatNumber<=9)
			return "P00"+currentSeatNumber;
			else
				return "P0"+currentSeatNumber;
		}
		else
		{
			
			return null;
		}
		
	}
	/**
	 * Create Passenger list and assign flight details to the passenger with Ticket Id and Seat Number 
	 * @param passengerDetailsList
	 * @return Passenger List
	 */
		public List<Passenger> validatePassenger(List<Passenger> passengerDetailsList)
		{
			
			BookingDAO passengerDetailsDAO=new BookingDAO();
			int maxTicketID=passengerDetailsDAO.getLastTicketID();
			maxTicketID++;
			for(int i=0;i<passengerDetailsList.size();i++)
			{
				String flight_ID=BookingController.flightConfirm.getFlightId();
				String currentTicketID=getcurrentTicketID(maxTicketID,flight_ID);
				ClassDetail classDetails=new ClassDetail();
				classDetails.setClassId(BookingController.flightConfirm.getClassDetail().getClassId());
				classDetails.setType(BookingController.flightConfirm.getClassDetail().getType());
				classDetails.setPrice(BookingController.flightConfirm.getClassDetail().getPrice());
				classDetails.setTotalSeat(BookingController.flightConfirm.getClassDetail().getTotalSeat());
				Flight flight=new Flight();
				flight.setClassDetail(classDetails);
				flight.setAirlineName(BookingController.flightConfirm.getAirlineName());
				flight.setFlightId(BookingController.flightConfirm.getFlightId());
				flight.setFromLocation(BookingController.flightConfirm.getFromLocation());
				flight.setToLocation(BookingController.flightConfirm.getToLocation());
				flight.setDeparturedate(BookingController.flightConfirm.getDeparturedate());
				flight.setDepartureTime(BookingController.flightConfirm.getDepartureTime());
				flight.setArrivalTime(BookingController.flightConfirm.getArrivalTime());
				flight.setAvailableSeats(BookingController.flightConfirm.getAvailableSeats());
				
				Ticket ticket=new Ticket();
				ticket.setFlightdetails(flight);
				ticket.setTicketId(currentTicketID);
				ticket.setStatus("confirm");
				passengerDetailsList.get(i).setTicket(ticket);
				maxTicketID++;
			}
			validateBookingID(BookingController.flightConfirm);
			boolean  flag=passengerDetailsDAO.addPassengerDetails(passengerDetailsList);
			if(flag)
			{
				return passengerDetailsList;
			}
			else
			{
				
			}
			return passengerDetailsList;
			
		}
		/**
		 * Generate New Ticket Id to Each Passenger
		 * @param maxTicketID  last 
		 * @param flight_ID
		 * @return New Generated Ticket Id 
		 */
		private String getcurrentTicketID(int maxTicketID, String flight_ID)
		{
			
	            if(maxTicketID>=0 && maxTicketID<=9)
	                 return flight_ID+"T000"+maxTicketID;
	            else if(maxTicketID>=10 && maxTicketID<=99)
	                 return flight_ID+"T00"+maxTicketID;
	            else if(maxTicketID>=100 && maxTicketID<=999)
	                 return flight_ID+"T0"+maxTicketID;
	            else if(maxTicketID>=1000 && maxTicketID<=9999)
	            return flight_ID+"T"+maxTicketID;
				return flight_ID+"T"+maxTicketID;

		}
		/**
		 * Validate Card Details 
		 * @param ticketPayment
		 * @return boolean variable if card details valid then return true otherwise return false
		 */
		public boolean ValidatePaymentDetails(Card ticketPayment)
		{
			
			int exMonth=ticketPayment.getExpirationMonth();
			int exYear=ticketPayment.getExpirationYear();
			LocalDate ld=LocalDate.now();
			int y=ld.getYear();
			int m=ld.getMonthValue();
			if(exYear>y )
			{
				BookingDAO paymentDAO=new BookingDAO();
				return paymentDAO.validatePaymentDAO(ticketPayment);
			}
			else if(exYear==y )
			{
				if(exMonth==m ||exMonth>m)
				{
					BookingDAO paymentDAO=new BookingDAO();
					return paymentDAO.validatePaymentDAO(ticketPayment);
				}
				else
				{
					LOG.info("Invalid Card Details");
				}
			}
			else
			{
				LOG.info("Invalid Card Details");
				
			}
			return false;
		}
	
		public boolean ticketInfobo(List<Passenger> ticketInfoList) 
		{
			BookingDAO ticketInfoDAO=new BookingDAO();
			return ticketInfoDAO.ticketInfoDao(ticketInfoList);
		}
		
		
		/**
		 * Cancel the TicketID which user want to cancel
		 * @param idCardNumber
		 * @param passenger
		 * @return boolean variable if Ticket cancel successfully  then return true otherwise return false
		 */
		public boolean cancelTicketCalculation(String idCardNumber, Passenger passenger)
		{
			LocalDate cancelDate=LocalDate.now();
			Period period =Period.between(cancelDate, passenger.getTicket().getFlightdetails().getDeparturedate());
		    int days=period.getDays();
		    double refundAmount=0;
		      double cancellationCharge=0;
		if(days>=10)
		{
			cancellationCharge=passenger.getTicket().getFlightdetails().getClassDetail().getPrice()*(0.1);
			
		}
		else if(days>=5 && days<10)
		{
			cancellationCharge=passenger.getTicket().getFlightdetails().getClassDetail().getPrice()*(0.2);
	       
		}
		else if(days>=1 && days<5 )
		{
	        cancellationCharge=passenger.getTicket().getFlightdetails().getClassDetail().getPrice()*(0.5);
		    
		}
		else
		{
			return false;
		}
		refundAmount=passenger.getTicket().getFlightdetails().getClassDetail().getPrice()-cancellationCharge;
		passenger.getTicket().setRefundAmount(refundAmount);
		passenger.getTicket().setCancellationCharge(cancellationCharge);
		passenger.getTicket().setCancelDate(cancelDate);
		passenger.getTicket().setStatus("cancel");
		return true;
		}
}
