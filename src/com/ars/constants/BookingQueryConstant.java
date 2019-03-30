package com.ars.constants;
public class BookingQueryConstant 
{
		public final static String SELECTSEARCHQUERY="select fd.flight_id,fd.airline_name,fd.from_location,fd.to_location,fd.departure_time,fd.arrival_time,fd.departure_date,cd.type,cd.price,cd.total_seats,cd.class_id ,fd.economy,fd.business,fd.first_class,fd.premium from flight_details fd join class_details cd on fd.flight_id=cd.flight_id where fd.from_location=? and fd.to_location=? and fd.departure_date=? and cd.type=?";
		public final static String AVAILABLESEATS="select count(*) from airline_reservation_system.ticket_info where flight_id in(?) and flight_departure_date in(?)  and status IN(?) and seat_number like ?;";
		public static final String GETLASTTICKETIDCOUNT="select max(id) from passenger_details;";
		public static final String GETLASTTICKETID="select ticket_id from passenger_details where id=?";
		public static final String INSERTINTOPASSENGERDETAILS="insert into passenger_details (ticket_id,name,gender,age) value(?,?,?,?)";
		public final static String INSERTPAYMENTQUERY="insert into card_details value(?,?,?,?,?)";
		public static final String INSERTINTOTICKETINFO="insert into ticket_info (ticket_id,flight_id,flight_departure_date,status,id_card_number,class_id,seat_number) values(?,?,?,?,?,?,?)";
}
/*
public final static String UPDATEFLIGHTTOTALSEATSE="update flight_details set economy=? where flight_id=? and from_location in(?) and to_location=? and departure_date in(?);";
public final static String UPDATEFLIGHTTOTALSEATSB="update flight_details set business=? where flight_id=? and from_location in(?) and to_location=? and departure_date in(?);";
public final static String UPDATEFLIGHTTOTALSEATSF="update flight_details set first_class=? where flight_id=? and from_location in(?) and to_location=? and departure_date in(?);";
public final static String UPDATEFLIGHTTOTALSEATSP="update flight_details set premium=? where flight_id=? and from_location in(?) and to_location=? and departure_date in(?);";
public final static String UPDATEFLIGHTCLASSTYPETOTALSEAT="update class_details set total_seats=? where type=? and flight_id=?";*/