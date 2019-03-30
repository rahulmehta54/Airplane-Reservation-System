<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  session="false" 	%>
    <%@page import="com.ars.dto.Passenger,java.util.*"%>
<!DOCTYPE html >
<%@include file="header.jsp" %>
<%if(request.getSession(false)==null){ 
	
response.sendRedirect("index.jsp");}
%>
 <link rel="stylesheet" type="text/css" href="Public/css/tickethistory.css">
<link href="https://fonts.googleapis.com/css?family=Cabin|Indie+Flower|Inknut+Antiqua|Lora|Ravi+Prakash" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"  />
 

<div id="myBtnContainer">
  <button class="btn active" onclick="filterSelection('all')"> Show all Ticket</button>
  <button class="btn" onclick="filterSelection('cancelt')">Show Cancelled Ticket</button>
  <button class="btn" onclick="filterSelection('bookt')">Show Booked Ticket</button>
</div>



<div class="container">

<h1 class="upcomming">Tickets History</h1>
<%if(request.getAttribute("cancelMsg")!=null){ %>
                        <div class="alert alert-info">
    <strong> <strong><%=request.getAttribute("cancelMsg")%>!</strong>
  </div>   <%--  <span style='color:red;'><h2 class="panel-title"><strong><%=request.getAttribute("errMsg")%></strong></h2></span> --%>
                         <%}%>
<%

List<Passenger> passengerHistoryList= (List<Passenger>)session1.getAttribute("passnegerHistoryList");
if(!passengerHistoryList.isEmpty())
{
	
for(int i=0;i<passengerHistoryList.size();i++)
{
%>
<form action="CancelTicket" method="post" id="CancelTicket">
    
   <% if(passengerHistoryList.get(i).getTicket().getStatus().equalsIgnoreCase("cancel")){ %>
   <div class="column cancelt">
   <%} else{ %>
    <div class="column bookt">
   <%} %>
   
    <div class="item">
    <div class="item-right">
    <input type="hidden" name="cancelIndex" value="<%=i%>"> 
    <% if(passengerHistoryList.get(i).getTicket().getStatus().equalsIgnoreCase("cancel")){ %>
   <h2 class="num linethrough"><%=passengerHistoryList.get(i).getTicket().getFlightdetails().getDeparturedate().getDayOfMonth()%></h2>
      <p class="day linethrough"><%=passengerHistoryList.get(i).getTicket().getFlightdetails().getDeparturedate().getMonth() %>,<%=passengerHistoryList.get(i).getTicket().getFlightdetails().getDeparturedate().getYear()%> </p>
   <%}else{ %>
   <h2 class="num"><%=passengerHistoryList.get(i).getTicket().getFlightdetails().getDeparturedate().getDayOfMonth()%></h2>
      <p class="day"><%=passengerHistoryList.get(i).getTicket().getFlightdetails().getDeparturedate().getMonth() %>,<%=passengerHistoryList.get(i).getTicket().getFlightdetails().getDeparturedate().getYear()%> </p>
   
   <%} %>
   <span class="up-border"></span>
      <span class="down-border"></span>
    </div> <!-- end item-right -->
    
    <div class="item-left">
      <p class="event">Ticket Id:<%=passengerHistoryList.get(i).getTicket().getTicketId() %></p>
      <h2 class="title">Airline Name: <%=passengerHistoryList.get(i).getTicket().getFlightdetails().getAirlineName() %></h2>
      
      <div class="sce">
        <div class="icon">
          <i class="fa fa-wheelchair-alt"></i>
        </div>
        <p>Flight ID :<%=passengerHistoryList.get(i).getTicket().getFlightdetails().getFlightId() %><br/></p>
      </div>
      
      <div class="fix">
      
      </div>
      
      <div class="sce">
        <div class="icon">
          <i class="fa fa-wheelchair-alt"></i>
        </div>
        <p>Seat Number :<%=passengerHistoryList.get(i).getTicket().getFlightdetails().getClassDetail().getSeatNumber() %><br/></p>
      </div>
      
      <div class="fix">
      
      </div>
      <div class="loc">
        <div class="icon">
          <i class="fa fa-map-marker"></i>
        </div>
        <p>From:<%=passengerHistoryList.get(i).getTicket().getFlightdetails().getFromLocation() %>  To: <%=passengerHistoryList.get(i).getTicket().getFlightdetails().getToLocation() %></p>
      </div>
      <div class="fix">
      </div>  
      <div class="loc">
        <div class="icon">
          <i class="fa fa-clock-o"></i>
        </div>
         <p>Departure Time:<%=passengerHistoryList.get(i).getTicket().getFlightdetails().getDepartureTime() %></p>
      </div>
        <div class="fix">
       </div>
      <div class="loc">
        <div class="icon">
          <i class="fa fa-clock-o"></i>
        </div>
         <p> Arrival Time:<%=passengerHistoryList.get(i).getTicket().getFlightdetails().getArrivalTime() %></p>
      </div>
        <div class="fix">
       </div>
      
      <div class="loc">
        <div class="icon">
          <i class="fa fa-vcard"></i>
        </div>
         <p>Name:<%=passengerHistoryList.get(i).getPassengerName()%></p></br><br>
      </div>
      
      <div class="loc">
        <div class="icon">
          <i class="fa fa-user"></i>
        </div>
         <p>Gender: <%=passengerHistoryList.get(i).getGender() %></p>
     </div>
     
         <div class="fix">
       </div>
      
      
      <div class="loc">
        <div class="icon">
          <i class="fa fa-ticket"></i>
        </div>
         <p>Status:<%=passengerHistoryList.get(i).getTicket().getStatus() %></p>
     </div>
      
      
      <% if(!passengerHistoryList.get(i).getTicket().getStatus().equalsIgnoreCase("cancel")){ %>
      
   <input type="submit" name="cancelticket" id="cancelticket" value="Cancel Ticket" onClick="confSubmit(this.form);" class="btn btn-primary cancel">
   <%} %>
    </div> <!-- end item-right -->
  </div> <!-- end item -->
  </div>
 
</form>
<%
}
}
%>
 <%@ include file="footer.jsp"%>