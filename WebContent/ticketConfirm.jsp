<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<%@page import="com.ars.dto.Passenger,java.util.*"%>
<!DOCTYPE html >
<%@include file="header.jsp"%>
<%if(request.getSession(false)==null){ 
response.sendRedirect("index.jsp");}%>

<%
  
 List<Passenger> passengerList= (List<Passenger>)session1.getAttribute("passengerDetailsList");
if(!passengerList.isEmpty())
{
	
%>


<div class="box">
	<div class='inner'>
		<% for(int i=0;i<passengerList.size();i++)
	{
%>
<div style="color:red;"><center><h2>Your ticket is Confirmed</h2></center></div>
		<h1>GO GO Air Ticket</h1>
		<div class='info clearfix'>
	
			<div class="row">

				<div class="col-xs-6 col-sm-6 col-md-6">
					<div class="form-group">
						<p>
							Ticket ID : <span style="color: red"><%=passengerList.get(i).getTicket().getTicketId() %></span>
						<p>
					</div>
				</div>


				<div class="col-xs-6 col-sm-6 col-md-6">
					<div class="form-group">
						<p>
							Flight ID : <span style="color: red"><%=passengerList.get(i).getTicket().getFlightdetails().getFlightId() %><span><p>
					</div>
				</div>

			</div>

			<div class="row">

				<div class="col-xs-6 col-sm-6 col-md-6">
					<div class="form-group">
						<p>
							AirLine Name : <span style="color: red"><%=passengerList.get(i).getTicket().getFlightdetails().getAirlineName() %></span>
						</p>
					</div>
				</div>


				<div class="col-xs-6 col-sm-6 col-md-6">
					<div class="form-group">
						<p>
							Depature Date : <span style="color: red"><%=passengerList.get(i).getTicket().getFlightdetails().getDeparturedate() %></span>
						</p>
					</div>
				</div>

			</div>
			
			<div class="row">

				<div class="col-xs-6 col-sm-6 col-md-6">
					<div class="form-group">
						<p>
							Name : <span style="color: red"><%=passengerList.get(i).getPassengerName() %></span>
						</p>
					</div>
				</div>


				<div class="col-xs-6 col-sm-6 col-md-6">
					<div class="form-group">
						<p>
							Departure Time : <span style="color: red"><%=passengerList.get(i).getTicket().getFlightdetails().getDepartureTime()%></span>
						</p>
					</div>
				</div>

			</div>


			<div class="row">

				<div class="col-xs-6 col-sm-6 col-md-6">
					<div class="form-group">
						<p>
							Staus : <span style="color: red"><%=passengerList.get(i).getTicket().getStatus() %></span>
						</p>
					</div>
				</div>


				<div class="col-xs-6 col-sm-6 col-md-6">
					<div class="form-group">
						<p>
							Class Type : <span style="color: red"><%=passengerList.get(i).getTicket().getFlightdetails().getClassDetail().getType()%></span>
						</p>
					</div>
				</div>

			</div>


			<div class="row">

				<div class="col-xs-6 col-sm-6 col-md-6">
					<div class="form-group">
						<p>
								Gender : <span style="color: red"><%=passengerList.get(i).getGender()%></span>
							</p>
					</div>
				</div>
				<div class="col-xs-6 col-sm-6 col-md-6">
					<div class="form-group">
							<p>
								Seat Number : <span style="color: red"><%=passengerList.get(i).getTicket().getFlightdetails().getClassDetail().getSeatNumber()%></span>
							</p>	
					</div>
				</div>
			</div>
		</div>
			
		<%
                                }
                                }%>

</div>
</div>


<%@ include file="footer.jsp"%>
