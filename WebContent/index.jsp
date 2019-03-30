<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html>

<%@include file="header.jsp" %>
<%-- <%
response.setHeader("Cache-Control", "no-cache");

//Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store");

//Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0);

//Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache");%> --%>

 <div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel-default">
			<div class="Booking_search_box">
			
        		<div class="panel-heading">
			    		<h2 class="panel-title"><span class="glyphicon glyphicon-plane"></span> Book Domestic & International Flight</h2>
			 			</div>
			 			<div class="panel-body">
			    		<form role="form" action="search" id="searchform" method="post">
			    		<br>		
						<!-- 	<div class="form-group">
			    					<center><input type="radio" name="triptype" id="one_way" value="oneway" checked>one-way
                                    <input type="radio" name="triptype" id="round_trip" value="roundtrip">Roundtrip</center>
							</div>
			    			 -->
							
							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<center>From:</center><input type="text" class="form-control" name="from" id="from" placeholder="From: City or Airport">
			    					</div>
			    				</div>
			    				
								
								<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<center>To:</center><input type="text" class="form-control" name="to" id="to" placeholder="To: City or Airport">
								    </div>
			    				</div>
								
							</div>
							
							
							
							<div class="row">
			    				
								<div class="col-xs-6 col-sm-12 col-md-12">
			    					<div class="form-group">
									<center>Departure Date:</center><input type="text"  class="form-control" id="dep_date"  name="dept_date" readonly><i class="icon-th"></i>
			    					</div>
			    				</div>
			    				
								
						<!-- 		<div class="col-xs-6 col-sm-6 col-md-6 returnd">
			    					<div class="form-group">
			    					<center>Return DATE:</center><input type="text" class="form-control" id="return_date" name="return_date" readonly><i class="icon-th"></i>
								    </div>
			    				</div>
								 -->
								
								
							</div>
							
							
							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
									<center>Traveller:</center><input type="number" class="form-control" id="traveller" name="noOfTravellers" min="1"> 
			    					</div>
			    				</div>
			    				
								
								<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<center>Class:</center><select name="class_type" class="form-control"><option value="economy">Economy</option> <option value="premium">Premium Economy</option><option value="business">Business</option><option value="first">First Class</option></select> 
								    </div>
			    				</div>
							</div>
							
							<div class="form-group">
			    					
							<input type="submit" name="" class="btn btn-primary" value="Search">
							
						   </div>
						   </form>
							<%
							String res=(String)request.getAttribute("noresult");
							if(res!=null)
							{%>
								<h2><%=res %></h2>
							<%}
							%>
							</div>
						
			    		
						
			    	</div>
	    		</div>
				</div>
    		</div>
 </div>
  <!------------------->

 <%@ include file="footer.jsp"%>














