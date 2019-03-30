<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
     <%@page import="com.ars.dto.Flight" %>
<!DOCTYPE html >
<%@include file="header.jsp" %>

<!------ Include the above in your HEAD tag ---------->

<div class="container">
    <div class='row payment-gateway'>
        
<div class="gateway_heading">
<h2>Payment Gateway</h2>
</div>
		<div class='col-md-4'>
		  
		</div>
        <div class='col-md-4'>
          <script src='https://js.stripe.com/v2/' type='text/javascript'></script>
          <form action="Pay"  method="post" id="paymentform">
            <div class='form-row'>
              <div class='col-xs-12 form-group required'>
                <label class='control-label'>Name on Card</label>
                <input class='form-control' size='4' type='text' name="nameOnCard" id="nameOnCard">
              </div>
            </div>
             <div class='form-row'>
              <div class='col-xs-12 form-group required'>
                <label>Card Type</label>
               <select name="cardType" id="cardType" class="form-control">
               <option value="Debit Card">Debit Card</option>
               <option value="credit Card">Credit Card</option>
               
               </select>
              </div>
            </div>
            <div class='form-row'>
              <div class='col-xs-12 form-group card required'>
                <label class='control-label'>Card Number</label>
                <input autocomplete='off' class='form-control card-number' size='20' type='text' name="cardNumber" id="cardNumber">
              </div>
            </div>
            <div class='form-row'>
              <div class='col-xs-4 form-group cvc required'>
                <label class='control-label'>CVV</label>
                <input autocomplete='off' class='form-control card-cvc' placeholder='ex. 311' size='4' type='text' name="cvv" id="cvv">
              </div>
              <div class='col-xs-4 form-group expiration required'>
                <label class='control-label'>Exp Month</label>
                <input class='form-control card-expiry-month' placeholder='MM' size='2' type='text' name="expirationMonth" id="expirationMonth">
              </div>
              <div class='col-xs-4 form-group expiration required'>
                <label class='control-label'>Year</label>
                <input class='form-control card-expiry-year' placeholder='YYYY' size='4' type='text' name="expirationYear" id="expirationYear">
              </div>
            </div>
            <div class='form-row'>
              <div class='col-md-12'>
              <% 
  
  Flight flightConfirm=(Flight)session1.getAttribute("flightConfirm");
if(flightConfirm!=null)
{
%>
                <div class='form-control total btn btn-info'>
                  Total:
                  <span class='amount'><%=(flightConfirm.getClassDetail().getPrice() *flightConfirm.getNoOfTraveller()) %></span>
                </div>
                <%
                }
                %>
              </div>
            </div>
			
            <div class='form-row'>
              <div class='col-md-12 form-group'>
			  <input type="submit"  name="button" value="Pay" class="form-control btn btn-primary"/> <!-- onclick="window.location.href='succesticket.html'" --> 
               
              </div>
            </div>
            <div class='form-row'>
              <div class='col-md-12 error form-group hide'>
                <div class='alert-danger alert'>
                  Please correct the errors and try again.
                </div>
              </div>
            </div>
          </form>
        </div>
        <div class='col-md-4'></div>
    </div>
</div>

 <%@ include file="footer.jsp"%>
 <head>
  <script type="text/javascript">
    window.history.forward();

    function noBack() {
      window.history.forward();
    }
  </script>
</head>

<body onload="noBack();" onpageshow="if (event.persisted) noBack();">
  <!-- your html/jsp code -->
</body>
 