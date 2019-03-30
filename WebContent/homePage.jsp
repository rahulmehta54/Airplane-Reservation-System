<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
         <link rel="stylesheet" type="text/css" href="Public/css/style.css">
		<link rel="stylesheet" type="text/css" href="Public/css/registration.css">
		<link rel="stylesheet" type="text/css" href="Public/css/navbar.css">
		 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
		 <script type="text/javascript" src="Public/js/Script.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
       <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<!--datepicker-->
 <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
<title>Insert title here</title>
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
						<li class="active"><a href="registration-login.html">home<span class="sr-only">(current)</span></a></li>
						<li><a href="Planedetail.html">Search Result Page</a></li>
						<li><a href="CancelTicket.html">Cancel Page</a></li>
						<li ><a href="paymentform.html">Payment Form</a></li>
						<li ><a href="bookingconfirm.html">Booking Confirm</a></li>
						<li ><a href="succesticket.html">Confirm Ticket</a></li>
				  </ul>
				  <ul class="nav navbar-nav navbar-right">
						<li ><a href="registration.html">Sign-up</a></li>
						<li ><a href="login.html">Log-in</a></li>
				  </ul>
			 </div>
		</div>
  </nav>
</div>
</header>	

 

 <div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel-default">
			<div class="Booking_search_box">
			
        		<div class="panel-heading">
			    		<h2 class="panel-title"><span class="glyphicon glyphicon-plane"></span> Book Domestic & International Flight</h2>
			 			</div>
			 			<div class="panel-body">
			    		<form role="form">
			    				
							<div class="form-group">
			    					<center><input type="radio" name="triptype" id="one_way" value="oneway">one-way
                                    <input type="radio" name="triptype" id="round_trip" value="roundtrip">Roundtrip</center>
							</div>
			    			
							
							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" class="form-control" name="from" placeholder="From: City or Airport">
			    					</div>
			    				</div>
			    				
								
								
								<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<input type="text" class="form-control" name="to" placeholder="To: City or Airport">
								    </div>
			    				</div>
								
							</div>
							
							
							
							<div class="row">
			    				
								<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
									<center>Departure DATE:</center><input type="text"  class="form-control" id="dep_date"  name="dept_date" readonly ><i class="icon-th"></i>
			    					</div>
			    				</div>
			    				
								
								<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<center>Return DATE:</center><input type="text" class="form-control" id="return_date" name="dept_date" readonly><i class="icon-th"></i>
								    </div>
			    				</div>
								
								
								
							</div>
							
							
							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
									<center>Traveller:</center><input type="number" class="form-control" name="dept_date" min="1"> 
			    					</div>
			    				</div>
			    				
								
								<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    					<center>Class:</center><select class="form-control"><option value="economy">Economy</option> <option value="pre_economy">Premium Economy</option><option value="bussiness">Bussiness</option><option value="first_clas">First Class</option></select> 
								    </div>
			    				</div>
							</div>
							
							<div class="form-group">
			    					
							<input type="submit" name="" class="btn btn-primary" value="Sign In">
							
						   </div>
							
							</div>
						
			    		</form>
						
			    	</div>
	    		</div>
				</div>
    		</div>
 </div>
  <!------------------->
 
 <div class="footer">
  <p><h3 >@Copyright 2019</h3></p>
</div>
</body>
</html>