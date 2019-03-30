<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  session="false"%>
<%@page import="java.time.format.DateTimeFormatter,java.util.*,com.ars.bo.*"%>
<%@include file="header.jsp" %>
<%if(request.getSession(false)==null){ 
response.sendRedirect("index.jsp");}%>
<%
/* HttpSession session2=request.getSession(false);	 */

if(session1!= null){
		
		Customer customer=(Customer)session1.getAttribute("customer");
		String name="";
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("MM/dd/yyyy");
		if(customer!=null)
			 name=customer.getName();
		
		String dob=dtf.format(customer.getDateOfBirth());
%>
<div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default  registrationBox">
        		<div class="panel-heading">
			    		<h2 class="panel-title"><strong>Edit User</strong></h2>
			    		<%if(request.getAttribute("updateMsg")!=null){ %>
                        <div class="alert alert-info">
    <strong> <strong><%=request.getAttribute("updateMsg")%>!</strong><%} %>
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
			    		<form action="updateProfile" id="updateProfile"  method="post" role="form">
			    				
						<div class="form-group">
						<p>Name</p> 
			                    <input type="text" name="name" id="name" class="form-control"  value="<%=customer.getName()%>">
			    		</div>
			    			
							<div class="form-group">
							<p>Email</p> 
			    				<input type="email" name="email" id="email" class="form-control" value="<%=customer.getEmail() %>">
			    			</div>

			    			 <div class="form-group">
			    			 <p>Phone Number</p> 
			    						<input type="text" name="phoneNumber" id="mob_num" class="form-control" value=<%=customer.getPhoneNumber() %>>
			    			</div>
							
							
							<div class="form-group">
			    						<p>Gender</p> 
										<select name="gender" class="form-control">
										<%if(customer.getGender().equalsIgnoreCase("Male"))
											{
											%>
																<option selected value="Male">Male</option>
																<option value="Female">Female</option>
																<%
																}  else if(customer.getGender().equalsIgnoreCase("Female"))
																{  %>
																<option  value="Male">Male</option>
																<option selected="selected" value="Female">Female</option>
																<%
																} %>
												</select>
							</div>
							
							
							<%-- <div class="form-group">
							
			    						<p>card type</p> <select name="cardType" class="form-control">
			    						<% if(customer.getIDCardType().equalsIgnoreCase("Aadhar Card"))
								{
								%>
																<option selected="selected">Adahar Card</option>
																<option >Pan Card</option>
																
																<%
																}  else if(customer.getIDCardType().equalsIgnoreCase("Pan Card"))
																	{ %>
																	<option value="Aadhar Card">Aadhar Card</option>
																<option selected value="Pan Card">Pan Card</option>
																
																<%}
			    						%>
												</select>
							</div> --%>
							
							<!-- Material inline 1 -->

							
						<%-- 	<div class="form-group">
			    						<p>card number</p><input type="text" name="cardNumber"class="form-control" value=<%=customer.getIDCardNumber() %> name="card_number" />
							</div> --%>
							<div class="form-group">
							<p>Address</p> 
			    						<textarea rows="4" cols="50" class="form-control" name="address" id="address"required placeholder="Address" ><%=customer.getAddress() %></textarea>
							</div>
			    			
			    			<div class="form-group">
			    			<p>Date of Birth</p>			
								<input type="text"  class="form-control" id="dob"  name="dob" value=<%=dob %>>		
							</div>
			    			
			    		   <div class="row">
			    				<div class="col-xs-8 col-sm-8 col-md-8">
			    					<div class="form-group">
			    			             <input type="submit" name="button" value="Update" class="btn btn-info btn-block">	
									</div>
			    				</div>
			    				
			    				</div>
			    				</form>
			    			</div>
							
							</div>
						
			    	
						
			    	</div>
	    		</div>
    		</div>
 
  <%} %>

<%@ include file="footer.jsp"%>