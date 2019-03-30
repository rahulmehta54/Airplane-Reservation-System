<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
    <%@page import="java.util.*,com.ars.bo.*" %>

<%@include file="header.jsp" %>



<div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default  registrationBox">
        		<div class="panel-heading">
			    		<h2 class="panel-title"><strong>Sign up</strong></h2>
			 			<%if(request.getAttribute("errMsg")!=null){ %>
                        <div class="alert alert-info">
    <strong> <strong><%=request.getAttribute("errMsg")%>!</strong>
  </div>   <%--  <span style='color:red;'><h2 class="panel-title"><strong><%=request.getAttribute("errMsg")%></strong></h2></span> --%>
                         <%}%>
                         <%if(!LoginBO.errorList.isEmpty()){
                        	%>
                        <div class="alert alert-info">
    <strong> <strong><%for(int i=0;i<LoginBO.errorList.size();i++){%><%=LoginBO.errorList.get(i) %>!</strong> Please Try again..
  </div>   <%--  <span style='color:red;'><h2 class="panel-title"><strong><%=request.getAttribute("errMsg")%></strong></h2></span> --%>
                         <%}
                        LoginBO.errorList.clear();
                       }%>
			 			</div>
			 			<div class="panel-body">
			    		<form action="register" method="post" role="form" id="signup">
			    				
						<div class="form-group">
						<p>Name</p> 
			                    <input type="text" name="name" id="name" class="form-control" placeholder="Enter your Name" >
			    		</div>
			    			
							<div class="form-group">
							<p>Email</p> 
			    				<input type="text" name="email" id="email" class="form-control" placeholder="Email Address" >
			    			</div>

			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<p>Password</p> 
			    						<input type="password" name="password" id="password" class="form-control" placeholder="Password" >
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<p>Confirm Password</p> 
			    						<input type="password" name="password_confirmation" id="password_confirmation" class="form-control" placeholder="Confirm Password" >
			    					</div>
			    				</div>
			    			</div>
							
							
							 <div class="form-group">
							 <p>Mobile Number</p> 
			    						<input type="text" name="mobile" id="mob_num" class="form-control" placeholder="Enter your mobile number" >
			    			</div>
							
							
							<div class="form-group">
			    						<p>Gender</p> 
										<select name="gender" id="gender" class="form-control">
																<option value="Male">Male</option>
																<option value="Female">Female</option>
												</select>
							</div>
							
							
							<div class="form-group">
			    						<p>card type</p> <select name="card_type" id="card_type" class="form-control">
																<option value="Aadhaar Card">Aadhaar Card</option>
																<option value="Pan Card">Pan Card</option>
												</select>
							</div>
							
							<!-- Material inline 1 -->

							
							<div class="form-group">
			    						<p>card number</p><input type="text" name="card_number" id="card_number" class="form-control" Placeholder="Enter Card Number"  name="card_number" />
							</div>
							<div class="form-group">
			    						<textarea rows="4" cols="50" class="form-control" name="address" id="address" placeholder="Enter address"></textarea>
							</div>
			    			
			    			<div class="form-group">
			    			<p>Date of Birth</p>			
								<input type="text"  class="form-control" id="dob"  name="dob" autocomplete="off">		
							</div>
			    			
			    		   <div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    			             <input type="submit" value="Register" name="button" class="btn btn-info btn-block">	
									</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					
			    			      <input type="reset" value="Reset" class="btn btn-info btn-block">
							</div>
			    				</div>
			    			</div>
							</form>
							</div>
						
			    		
						
			    	</div>
	    		</div>
    		</div>
 </div>


 <%@ include file="footer.jsp"%>