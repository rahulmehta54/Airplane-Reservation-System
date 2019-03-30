package com.ars.constants;
public class LoginQueryConstant
{
	public final static String SELECTLOGINQUERY="select * from customer_details where (e_mail=? or id_card_number=?) and password=?";
	
	public final static String RegisterQuerry="insert into customer_details (id_card_number,name,e_mail,password,date_of_birth,address,phone_no,gender,id_card_type) values(?,?,?,?,?,?,?,?,?)";

	public static final String UPDATECUSTOMERPROFILEQUERY = "update customer_details set name=?, e_mail=?, phone_no=?,gender=?,address=?, date_of_birth=? where id_card_number=? ";

	public static final String UPDATEEMAILQUERY = "update customer_details set e_mail=? where id_card_number=? ";

	public static final String UPDATEDATEOFBIRTHQUERY = "update customer_details set date_of_birth=? where id_card_number=? " ;

	public static final String UPDATEPHONENUMBERQUERY ="update customer_details set phone_no=? where id_card_number=? " ;

	public static final String UPDATEADDRESSQUERY = "update customer_details set address=? where id_card_number=? ";

	public static final String UPDATEGENDERQUERY = "update customer_details set gender=? where id_card_number=? ";
	
	public static final String UPDATEPASSWORDQUERY ="update customer_details set password=? where id_card_number=? ";

	public static final String ViewUserTicketQuery = "select distinct ti.ticket_id,pd.name,pd.age,pd.gender,fd.airline_name,fd.from_location,fd.departure_time,fd.to_location,fd.arrival_time,ti.flight_id,ti.flight_departure_date,ti.status,sd.type,ti.seat_number,sd.price from "+ 
			"ticket_info ti join class_details sd "+
			"on ti.class_id=sd.class_id "+
			"join passenger_details pd "+
			"on pd.ticket_id=ti.ticket_id "+
			"join flight_details fd "+
			"on fd.flight_id=ti.flight_id "+
			"where ti.id_card_number IN (?)  and ti.flight_departure_date=fd.departure_date";
	
	

/*public static final String CANCELTICKETSTATUSQUERY ="update  ticket_info ti join class_details cd on ti.flight_id=cd.flight_id "+
            " join flight_details fd on fd.flight_id=ti.flight_id set ti.status=?,fd."+BookingDAO.cancelTicketType+"=fd."+BookingDAO.cancelTicketType+"+1 where ti.class_id=cd.class_id and  ti.ticket_id=?";*/

public static final String SELECTCANCELTICKETQUERY = "select ti.id_card_number,ti.ticket_id,ti.flight_departure_date,cd.price from passenger_details pd join ticket_info ti on pd.ticket_id=ti.ticket_id join class_details cd on cd.class_id=ti.class_id  where pd.ticket_id=?";

public static final String INSERTCANCELTICKETQUERY = "insert into cancel_ticket (ticket_id,refund_amount,cancellation_charge,cancel_date,departure_date,status,id_card_number) values (?,?,?,?,?,?,?)";

public static final String SearchQuerry = "select * from customer_details where e_mail=? or id_card_number=?";

}
