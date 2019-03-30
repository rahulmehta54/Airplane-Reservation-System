package com.ars.dao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.ars.bo.BookingBO;
import com.ars.constants.BookingQueryConstant;
import com.ars.constants.LoginQueryConstant;
import com.ars.dto.*;
import com.ars.util.DBUtil;
public class BookingDAO
{
	private static final Logger LOG=Logger.getLogger(BookingDAO.class);
	
	
	/**
	 * Find Available Flights in the given date, from_location and to_location  
	 * @param flightSearch  
	 * @return the available flight list 
	 * @exception SQL Exception
	 */
	public List<Flight> findSearchFlight(Flight flightSearch)
	{
		
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Flight> itemList=new ArrayList<>();
		try {
				
				cn= DBUtil.getConnected();
				if (cn != null)
				{
				 ps=cn.prepareStatement(BookingQueryConstant.SELECTSEARCHQUERY);
					ps.setString(1,flightSearch.getFromLocation());
					ps.setString(2, flightSearch.getToLocation());
					Date departureDate=Date.valueOf(flightSearch.getDeparturedate());
					ps.setDate(3, departureDate);
					ps.setString(4, flightSearch.getClassDetail().getType());
					rs=ps.executeQuery();
					while (rs.next())
					{
						Flight flightSearch1=new Flight();
						flightSearch1.setFlightId(rs.getString(1));	
						flightSearch1.setAirlineName(rs.getString(2));
						flightSearch1.setFromLocation(rs.getString(3));
						flightSearch1.setToLocation(rs.getString(4));
						Time t=rs.getTime(5);
						LocalTime localTime = t.toLocalTime();
						flightSearch1.setDepartureTime(localTime);
						Time t1=rs.getTime(6);
						LocalTime localTime1 = t1.toLocalTime();
						flightSearch1.setArrivalTime(localTime1);
						Date date=rs.getDate(7);
						LocalDate localDate=date.toLocalDate();
						flightSearch1.setDeparturedate(localDate);
						ClassDetail cd=new ClassDetail();
						cd.setType(rs.getString(8));
						cd.setPrice(rs.getDouble(9));
						cd.setTotalSeat(rs.getInt(10));
						cd.setClassId(rs.getString(11));
						flightSearch1.setClassDetail(cd);
						if(flightSearch.getClassDetail().getType().trim().equalsIgnoreCase("economy"))
						{
							flightSearch1.setAvailableSeats(rs.getInt(12));
						}
						else if(flightSearch.getClassDetail().getType().trim().equalsIgnoreCase("business"))
						{
							flightSearch1.setAvailableSeats(rs.getInt(13));
						}
						else if(flightSearch.getClassDetail().getType().trim().equalsIgnoreCase("first"))
						{
							flightSearch1.setAvailableSeats(rs.getInt(14));
						}
						else if(flightSearch.getClassDetail().getType().trim().equalsIgnoreCase("premium"))
						{
							flightSearch1.setAvailableSeats(rs.getInt(15));
						
						}
						flightSearch1.setNoOfTraveller(flightSearch.getNoOfTraveller());											
						itemList.add(flightSearch1);
					}
					return itemList;
				}
			}catch (Exception ex) 
			{
				LOG.error(ex.getMessage());
				
			}
			finally 
			{
			try {
				ps.close();
				cn.close();
				rs.close();
			} catch (SQLException e) {
				
				LOG.error(e.getMessage());
				
			}
		}
			return itemList;
	}
	
	/**
	 * @return the Last Ticket Id 
	 *@exception SQL Exception
	 */
	public int getLastTicketID()
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=DBUtil.getConnected();
			ps=con.prepareStatement(BookingQueryConstant.GETLASTTICKETIDCOUNT);
			rs=ps.executeQuery();
			while(rs.next())
			{
				int maxLastValue=rs.getInt(1);
				return maxLastValue;
			}
		} catch (Exception e) {
			LOG.info(e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
				rs.close();
			} catch (SQLException e) {
				LOG.info(e.getMessage());
				
			}
		}
				
		return 0;
	}

	/**
	 * Add Passenger Detail info into the table
	 * @param passengerFinalDetailsList 
	 * @return the boolean value based on result
	 * @exception SQL Exception
	 */
	public boolean addPassengerDetails(List<Passenger> passengerFinalDetailsList)
	{
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			int count=1;
			con=DBUtil.getConnected();
			for(int i=0;i<passengerFinalDetailsList.size();i++)
			{
				 ps=con.prepareStatement(BookingQueryConstant.INSERTINTOPASSENGERDETAILS);
				ps.setString(1, passengerFinalDetailsList.get(i).getTicket().getTicketId());
				ps.setString(2, passengerFinalDetailsList.get(i).getPassengerName());
				ps.setString(3, passengerFinalDetailsList.get(i).getGender());
				ps.setInt(4, passengerFinalDetailsList.get(i).getAge());
				int c=ps.executeUpdate();
				if(c==1)
				{
					if(count==passengerFinalDetailsList.size())
					{
						
						return true;
					}
					else
					{
						count++;
					}
				}
				else
				{
					LOG.info("Insertion Fails");
					return false;
				}
			}
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				
			}
		}
		return false;	
	}
	/**
	 * Insert Payment Details Information into database of the user who book the Ticket
	 * @param ticketPayment  All Ticket Related Details store in this entity
	 * @return the boolean value based on result if Ticket Details insert successfully then return true otherwise return false
	 * @exception SQL Exception
	 */
	public boolean validatePaymentDAO(Card ticketPayment)
	{
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		try {
			con=DBUtil.getConnected();
		     ps=con.prepareStatement(BookingQueryConstant.INSERTPAYMENTQUERY);
			ps.setBigDecimal(1, new BigDecimal(ticketPayment.getCardNumber()));
			ps.setString(2, ticketPayment.getCardType());
			ps.setInt(3, ticketPayment.getExpirationMonth());
			ps.setInt(4, ticketPayment.getExpirationYear());
			ps.setString(5, ticketPayment.getIdCardNumber());
			count=ps.executeUpdate();
			if(count==1)
			{
				LOG.info("Payment Success");
				return true;
			}
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			
		}
		finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				
			}
		}
		return false;
	}

	/**
	 * Insert Ticket Details information of the passenger who successfully done the payment 
	 * @param ticketInfoList
	 * @return the boolean value based on result if Ticket Details Insertion success then return true otherwise return false
	 * @exception SQL Exception 
	 */
	public boolean ticketInfoDao(List<Passenger> ticketInfoList)
	{
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		try 
		{
			con=DBUtil.getConnected();
			if(con!=null)
			for(int i=0;i<ticketInfoList.size();i++)
			{
				ps=con.prepareStatement(BookingQueryConstant.INSERTINTOTICKETINFO);
				ps.setString(1, ticketInfoList.get(i).getTicket().getTicketId());
				ps.setString(2,ticketInfoList.get(i).getTicket().getFlightdetails().getFlightId());
				Date d=Date.valueOf(ticketInfoList.get(i).getTicket().getFlightdetails().getDeparturedate());
				ps.setDate(3, d);
				ps.setString(4,ticketInfoList.get(i).getTicket().getStatus());
				ps.setString(5,LoginDao.currentUserIDCardNumber);
				ps.setString(6,ticketInfoList.get(i).getTicket().getFlightdetails().getClassDetail().getClassId());
				ps.setString(7,ticketInfoList.get(i).getTicket().getFlightdetails().getClassDetail().getSeatNumber());
				int rs=ps.executeUpdate();
				if(rs==1)
				{
					if(count==ticketInfoList.size())
					{
						return true;
					}
					else
					{
						count++;
					}
				}
				else
				{
					LOG.info("Ticket Insertion Fails");
					return false;
				}	
			}		
		} catch (Exception e) {
			LOG.error(e.getMessage());
			
		}
		finally
		{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				
			}
		}
		return false;
	}
	
	/**
	 * Fetch the History of Tickets who Book the Ticket
	 * @param customer details information store in this entity
	 * @return the Passenger List of booked ticket history of the logging user
	 * @exception SQL Exception
	 */
	public List<Passenger> fetchTicket(Customer customer) {
	
	
		Connection cn=null;
		PreparedStatement ps=null;
		try{
			cn= DBUtil.getConnected();
			if(cn!=null){
				 ps=cn.prepareStatement(LoginQueryConstant.ViewUserTicketQuery);
				ps.setString(1,customer.getIDCardNumber());
		    	 ResultSet rs=ps.executeQuery();
		    	 List<Passenger> passengerList=new ArrayList<>();
		    	 while(rs.next())
		    	 {	 
		    		 Passenger passenger=new Passenger();
		    		 Ticket ticket=new Ticket();
		    		 Flight flight=new Flight();
		    		 ClassDetail classDetail=new ClassDetail();		 
		    			ticket.setTicketId(rs.getString(1));
		    			ticket.setStatus(rs.getString(12));
		    			passenger.setPassengerName(rs.getString(2));
		    			passenger.setAge(rs.getInt(3));
		    			passenger.setGender(rs.getString(4));
		    			flight.setAirlineName(rs.getString(5));
		    			flight.setFromLocation(rs.getString(6));
		    			 LocalTime departureTime=LocalTime.parse(rs.getString(7));
		    			flight.setDepartureTime(departureTime);
		    			flight.setToLocation(rs.getString(8));
		    			 LocalTime arrivalTime=LocalTime.parse(rs.getString(9));
		    			flight.setArrivalTime(arrivalTime);
		    			flight.setFlightId(rs.getString(10));
		    			LocalDate departureDate=LocalDate.parse(rs.getString(11));
		    			flight.setDeparturedate(departureDate);
		    			classDetail.setType(rs.getString(13));
		    			classDetail.setSeatNumber(rs.getString(14));
		    			classDetail.setPrice(rs.getDouble(15));
		    			flight.setClassDetail(classDetail);
		    			ticket.setFlightdetails(flight);
		    			passenger.setTicket(ticket);
		    			passengerList.add(passenger);
	    	  }
		    return passengerList;
			}
		}catch(Exception ex){
			LOG.error(ex.getMessage());
  			return null;
		}
		
		 finally{
	  			try {
	  				cn.close();
	  				ps.close();
	  			} catch (Exception e) {
	  				LOG.error(e.getMessage());
	  			}
		 }
		return null;
	}
	
	
	//public static String cancelTicketType=null;
    //	public String cancelTicketType=null;
	/**
	 * Cancel the Ticket which is user want to the cancel Ticket and update Ticket status  Confirm to  Cancel in the Ticket_Info 
	 * @param passenger 
	 * @return the passenger who cancel the Ticket
	 */
	public Passenger ticketCancellation(Passenger passenger)
	{	
		//cancelTicketType=passenger.getTicket().getFlightdetails().getClassDetail().getType();	
		Connection cn=null;
		PreparedStatement ps2=null;
		ResultSet rs=null;
		try {
			cn= DBUtil.getConnected();
			cn.setAutoCommit(false);
		
			if (cn != null) 
			{
					ps2=cn.prepareStatement(LoginQueryConstant.SELECTCANCELTICKETQUERY);
					ps2.setString(1, passenger.getTicket().getTicketId());
					rs=ps2.executeQuery();
					if(rs.next())
					{
						String idCardNumber=rs.getString(1);
						BookingBO bookingBO=new BookingBO();
						if(bookingBO.cancelTicketCalculation(idCardNumber,passenger))
						{
							PreparedStatement ps3=cn.prepareStatement(LoginQueryConstant.INSERTCANCELTICKETQUERY);
							ps3.setString(1, passenger.getTicket().getTicketId());
							ps3.setDouble(2, passenger.getTicket().getRefundAmount());
							ps3.setDouble(3,passenger.getTicket().getCancellationCharge() );
							ps3.setDate(4, Date.valueOf(passenger.getTicket().getCancelDate()));
							ps3.setDate(5, Date.valueOf(passenger.getTicket().getFlightdetails().getDeparturedate()));
							ps3.setString(6, passenger.getTicket().getStatus());
							ps3.setString(7, LoginDao.currentUserIDCardNumber);
							int count2=	ps3.executeUpdate();
							if(count2>0)
							{
								cn.commit();
								return passenger;
							}	
						}
					}
				}
			
			} catch (Exception ex) {
				try 
				{
					cn.rollback();
				} 
				catch (SQLException e) {

                     LOG.error(e.getMessage());
				
				}
				 LOG.error(ex.getMessage());
				return null;
			}
			finally{
				
					try {
						cn.close();
						ps2.close();
						rs.close();
					} catch (Exception e) {
						 LOG.error(e.getMessage());
					}
				}
		return null;	
	}

	/**
	 * 
	 * @param flightSearch
	 * @return the Total booked  seats 
	 */
	public int bookedSeats(Flight flightSearch)
	{
		int  availableSeat=0;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			cn= DBUtil.getConnected();
			cn.setAutoCommit(false);
			if (cn != null)
			{
				 ps=cn.prepareStatement(BookingQueryConstant.AVAILABLESEATS);
				ps.setString(1, flightSearch.getFlightId());
				Date departureDate=Date.valueOf(flightSearch.getDeparturedate());
				ps.setDate(2, departureDate);
				ps.setString(3, "Confirm");
				StringBuffer seattype=new StringBuffer();
				seattype.append(flightSearch.getClassDetail().getType().charAt(0)).append("0%");
				ps.setString(4, seattype.toString());
				 rs=ps.executeQuery();
				if(rs.next())
				{
					availableSeat=rs.getInt(1);
					cn.commit();
				}
			}
		}
		catch (Exception ex) {
			try {
				cn.rollback();
			} catch (SQLException e) {

                 LOG.error(e.getMessage());
				e.printStackTrace();
			}
			 LOG.error(ex.getMessage());
			return 0;
		}
		finally{
			
				try {
					cn.close();
					ps.close();
					rs.close();
				} catch (Exception e) {
					 LOG.error(e.getMessage());
				}
			}
		return availableSeat;
	}
}