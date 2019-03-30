<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  session="false"%>
<%@include file="header.jsp" %>

	<div class="loginBox">
		<img src="./Public/images/user.png" class="user">
		<h2>Log In Here</h2>
		<form action="SignIn" method="post" id="loginform">
		<% if(request.getAttribute("count")!=null)
{
	
	
Integer count=(Integer)request.getAttribute("count");

if(count!=0){
%>
<input type="hidden" name="count" value=<%=count %>>
<%} 
}%>
			<p>Email</p>
			<input type="text" name="email" id="emaillog" placeholder="Enter Email">
			<p>Password</p>
			<input type="password" id="passwordlog" name="password" placeholder="Enter your password">
			<input type="submit" name="button" value="Sign In">
		</form>
		<%
		String check=(String)request.getAttribute("invalidcredentials");
		if(check!=null){
		if(check.equals("invalidcredentials"))
			{
			%> <div class="alert alert-info">
    <strong> Invalid Credentials!</strong> Please Try again..
  </div><%}} %>
	</div>
</body>
</html>