<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1" session="false"%>
 <%@page import="com.ars.dto.Customer" %>
 <!DOCTYPE html >
<html>
<head>
		<meta charset="utf-8">
		<title>AirPlane Reservation System</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="Public/css/style.css">
		<link rel="stylesheet" type="text/css" href="Public/css/registration.css">
		<link rel="stylesheet" type="text/css" href="Public/css/navbar.css">
		<link rel="stylesheet" type="text/css" href="Public/css/animation.css">
		<link rel="stylesheet" type="text/css" href="Public/css/ticket.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
		<!-- DATE PICKER-->  
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  		<link rel="stylesheet" href="/resources/demos/style.css">
  		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
  		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
		<script type="text/javascript" src="Public/js/Script.js"></script>
	    <script type="text/javascript" src="Public/js/formvalidation.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script src=" https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
   		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
       	<!--    -----------------------------------             --> 
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src=" https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>     
   </head>
	<body>
<header>
<div id="topheader">
  <nav class="navbar navbar-default">
		<div class="container-fluid">
			 <div class="navbar-header">
				  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
				  </button>
				  <a class="navbar-brand" href="#">GO GO Air</a>
			 </div>
			 <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				  <ul class="nav navbar-nav">
						<li ><a href="index.jsp">home<span class="sr-only">(current)</span></a></li>
						
				<%	HttpSession session1=request.getSession(false);	
	if(session1!= null){
		
		Customer customer=(Customer)session1.getAttribute("customer");
		String name="";
		if(customer!=null)
			 name=customer.getName(); 
%>		
               <li ><a href="ticketHistory">Ticket History<span class="sr-only">(current)</span></a></li>
				  </ul>
				  <ul class="nav navbar-nav navbar-right">
				  					

    <li ><a href="profileUpdate">Edit Profile</a></li>
    <li ><a href="changePassword">Change Password</a></li>
    <li><a id="signOut" href="signOut">Sign Out</a></li>
   <li><a><i class="glyphicon glyphicon-user"><%=name %></i></a></li>
<%
	}   else { 
		%>
			           </ul>
				  <ul class="nav navbar-nav navbar-right">
						<li><a href="signup.jsp">Sign-up</a></li>
						<li><a href="login.jsp">Log-in</a></li>
						<%
						} %>
				  </ul>
			 </div>
		</div>
  </nav>
</div>
</header>