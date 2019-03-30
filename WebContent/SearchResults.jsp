<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" contentType="text/html; charset=ISO-8859-1" session="false"%>
    <%@page import="com.ars.dto.Flight,com.ars.controller.BookingController"%>
    <%@page import="java.util.*"%>
  
<!DOCTYPE html>
  <%@include file="header.jsp" %>

 <%
	Flight flightSearch =new Flight();
	List<Flight> list=(List<Flight>)request.getAttribute("flightlist");
	
	%>
  
<form role="form" action="BookNow" method="post">
<div class="search_box_detail">
			 			<div class="panel-body">
			    		
						
			    			
			    			
			    			
			    			
							<div class="row form-group">
			    					<%if(request.getAttribute("return_date")!=null){ %>
			    							<!-- <center><input type="radio" name="triptype" id="one_way" value="oneway">one-way
                                            <input type="radio" name="triptype" id="round_trip" value="roundtrip" checked>Roundtrip</center> -->
							
			    					<%}else{ %>
			    									<!-- <center><input type="radio" name="triptype" id="one_way"  value="oneway" checked>one-way
                                            <input type="radio" name="triptype" id="round_trip" value="roundtrip" >Roundtrip</center> -->
							
			    					
			    					
			    					
			    					<%} %>
			    			</div>
			    			
							
							<div class="row">
			    				<div class="col-xs-2 col-sm-2 col-md-2">
			    					From:<div class="form-group">
			    						<input type="text" class="form-control" name="from" value=<%=request.getAttribute("from") %>>
			    					</div>
			    				</div>
			    				
								
								
								<div class="col-xs-2 col-sm-2 col-md-2">
			    					To:<div class="form-group">
			    					<input type="text" class="form-control" name="to" value=<%=request.getAttribute("to") %>>
								    </div>
			    				</div>
								
								
								<div class="col-xs-2 col-sm-2 col-md-2">
			    					<div class="form-group">
									<center>Departure DATE:</center><input type="text"  value=<%=request.getAttribute("depatureDate") %> class="form-control" id="dep_date"  name="dept_date" readonly ><i class="icon-th"></i>
			    					</div>
			    				</div>
			    				
								
							<!-- 	<div class="col-xs-2 col-sm-2 col-md-2">
			    					<div class="form-group">
			    					<center>Return DATE:</center> --><!-- <input type="text" class="form-control" id="return_date" name="dept_date" readonly><i class="icon-th"></i>
								    </div>
			    				</div> -->
								
									<div class="col-xs-2 col-sm-2 col-md-2">
			    					<div class="form-group">
									<center>Traveller:</center><input type="number"  value=<%=request.getAttribute("traveller") %> class="form-control" name="noOfTravellers" min="1"> 
			    					</div>
			    				</div>
			    				
								
								<div class="col-xs-2 col-sm-2 col-md-2">
			    					<div class="form-group">
			    					<center>Class:</center><select name="class_type"  class="form-control" value=<%=request.getAttribute("class") %> ><option value="economy">Economy</option> <option value="premium">Premium Economy</option><option value="business">Bussiness</option><option value="first">First Class</option>
			    					</select> 
								    </div>
			    				</div>
								<div class="col-xs-2 col-sm-2 col-md-2">
			    					<button type="submit" name="index" class="btn btn-primary" value="Search" style="    margin-left: 33px;
    margin-top: 17px;">Search</button>
			    					
			    				</div>
							</div>
							
							</div>
						
			    		
						
			    	</div>
	    		</div>



<div class="container mt-3">
  <h2>List of Flight</h2>
  <div class="row">    
  <div class="col-md-10">
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
  </div>
  <div class="col-md-2">
  
  

  </div>
  
  </div>
  
  <br>
  <table class="table table-bordered flight_list">
    <thead>
      <tr>
      <th>Index No</th> 
	  <th>Flight Id</th>
	  <th>Airline Name</th>
	  <th>From Location</th>
	  <th>To Location</th>
	  <th>Departure Time</th>
	  <th>Arrival Time</th>
	  <th>Price</th>
	  <th>Action</th>
	 </tr>
    </thead>
    
	<tbody id="myTable">
	
	
	<% 
	if(list!=null)
	{
	for(int i=0;i<list.size();i++)
	{
			
	
	%> 
      <tr>
     
            <td><%=i+1%></td><td><%=list.get(i).getFlightId()%></td><td><%=list.get(i).getAirlineName()%></td><td><%=list.get(i).getFromLocation()%></td><td><%=list.get(i).getToLocation()%></td><td><%=list.get(i).getDepartureTime()%></td><td><%=list.get(i).getArrivalTime()%></td><td><%=list.get(i).getClassDetail().getPrice()%></td><td><button type="submit" class="btn btn-primary" name="index" value="<%=i+1%>">Book Now</button><!-- <input type="submit" name="button" value="Book Now"  class="btn btn-primary active"> --></td>
      </tr>
      <%}%>
	
	</form>
	
	<%}%>
    
    </tbody>
	</table>
	
</div>
</form>

<div class="row">
   <div class="col-md-4 col-md-offset-4">
  <form action="sort" method="post">
  <select name="sortby" class="form-control"><option>Sort data based on</option><option  value="Price">Price</option> <option value="time">Time</option><option value="Name">Name</option></select>
  <input type="submit" value="submit" class="btn btn-primary" style="margin-left: 174px;">
  </form>
  </div>
</div>


<%
							String res=(String)request.getAttribute("noresult");
							if(res!=null)
							{%>
								<h2><%=res %></h2>
							<%
							}
	
							%>
 <%@ include file="footer.jsp"%>