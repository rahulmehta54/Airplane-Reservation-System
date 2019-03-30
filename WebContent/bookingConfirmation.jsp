<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
    <%@page import="com.ars.dto.Flight,java.time.*" %>
<!DOCTYPE html >
<%@include file="header.jsp" %>

<% 
  
Flight flightConfirm=(Flight)session1.getAttribute("flightConfirm");
if(flightConfirm!=null)
{
	Duration d=Duration.between(flightConfirm.getDepartureTime() , flightConfirm.getArrivalTime());
%>


<div id="rows" class="row"  >
<div id="ticket_detail" class="col-sm-12 col-lg-7" >
<h2 align="center"><b>Ticket Details</b></h2>
<table align ="center" width="100%">
<tr>
<td><h3>Flight Id:</h3> <%=flightConfirm.getFlightId() %></td><td><h3>Airline Name:</h3> <%=flightConfirm.getAirlineName() %></td>
</tr>
<tr>
<td><h3>From:</h3> <%=flightConfirm.getFromLocation() %></td><td><h3>To:</h3> <%=flightConfirm.getToLocation() %></td>
</tr>
<tr>
<td><h3> Date</h3></td><td><%=flightConfirm.getDeparturedate() %></td>
</tr>
<tr>
<td><h3>Departure Time <%=flightConfirm.getDepartureTime() %></h3></td><td><h3>Arrival Time <%=flightConfirm.getArrivalTime() %></h3></td>
</tr>
<tr>
<td><h3> Duration</h3></td><td><%=d.toHours() %>Hours </td>
</tr>
<tr>
<td><h3>No. Of Person</h3></td><td><%=flightConfirm.getNoOfTraveller() %></td>
</tr>
<tr><td><h3>Class</h3></td><td><%=flightConfirm.getClassDetail().getType() %></td></tr>
</table>
</div>

<div  id="ticket_summery" class="col-sm-12 col-lg-4" >
<h2 align="center"><b>Fare Summary</b></h2>
<table align ="center" width="100%" >

<tr>
<td><h3>Fare</h3></td><td><%=flightConfirm.getClassDetail().getPrice() %></td>
</tr>
<tr>
<td><h3>No. Of Person</h3></td><td><%=flightConfirm.getNoOfTraveller() %></td>
</tr>
<tr><td><h3>Total Cost</h3></td><td><%=flightConfirm.getClassDetail().getPrice() *flightConfirm.getNoOfTraveller()%></td></tr>
</table>
</div>
</div>

<%
}
%>
<!--   <h2>Add Member Details</h2> -->
          <form action="BookingConfirm" method="post">  
  <div class="container" id="addmembercontainer">
    <h2><b>Add Member</b></h2> 
                <div class="row">
                                                                                                                
                                                                                                                                <div class="col-xs-4 col-sm-4 col-md-4">
                                                                                                                                <div class="form-group">
                                                                                                                                                <center>Name</center>
                                                                                                                                </div>
                                                                                                                </div>
                                                                                                                
                                                                                                                                <div class="col-xs-4 col-sm-4 col-md-4">
                                                                                                                                <div class="form-group">
                                                                                                                                <center>Age</center>
                                                                                                                                    </div>
                                                                                                                </div>
                                                                                                                                
                                                                                                                                <div class="col-xs-4 col-sm-4 col-md-4">
                                                                                                                                <div class="form-group">
                                                                                                                                <center>Gender</center>
                                                                                                                                    </div>
                                                                                                                </div>
                                                                                                                                
  </div>
             <%
    if(flightConfirm!=null)
    {
    for(int i=0;i<flightConfirm.getNoOfTraveller();i++)
    	{%>    
                
                <div class="row">
                                                                                                                
                                                                                                                                <div class="col-xs-4 col-sm-4 col-md-4">
                                                                                                                                <div class="form-group">
                                                                                                                                                <center> <input type="text" id="first_name" class="form-control"  name="name" ></center>
                                                                                                                                </div>
                                                                                                                </div>
                                                                                                                
                                                                                                                                <div class="col-xs-4 col-sm-4 col-md-4">
                                                                                                                                <div class="form-group">
                                                                                                                                <center><input type="text" id="age" class="form-control"  name="age"></center>
                                                                                                                                    </div>
                                                                                                                </div>
                                                                                                                                
                                                                                                                                <div class="col-xs-4 col-sm-4 col-md-4">
                                                                                                                                <div class="form-group">
                                                                                                                                <center><select  class="form-control" name="gender">
                            <option value="Male" selected>Male</option>
                            <option value="Female">Female</option>
                        </select></center>
                                                                                                                                    </div>
                                                                                                                </div>
                                                                                                                                
  </div>
  
  <%}
    }%>
<%-- <table id="mytable" >
    <thead>
        <th>Name</th>
      
        <th>Age</th>
		<th>Gender</th>
    </thead>
    <%
    if(flightConfirm!=null)
    {
    for(int i=0;i<flightConfirm.getNoOfTraveller();i++)
    	{%>
    <tbody>
        <tr>
            <td>
                <input type="text" id="first_name" class="form-control"  name="name">
            </td>
           
            <td>
                <input type="text" id="mobile" class="form-control" name="age">
            </td>
			<td>
                <input type="e-mail" id="e-mail" class="form-control" name="gender">
            </td>
        </tr>
        <% 
    	}
    	}%>
    </tbody>
	</table> --%>
  <center>  <input  type="submit"  name="button" class="btn btn-primary" value="submit" ></center><!-- onclick="window.location.href='paymentform.html'" -->
</div>
</form>



  



 <%@ include file="footer.jsp"%>


