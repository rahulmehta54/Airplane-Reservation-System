<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  session="false"%>
<%@include file="header.jsp" %>


<div class="loginBox">
		<%if(request.getAttribute("errMsg")!=null){ %>
                        <div class="alert alert-info">
    <strong><%=request.getAttribute("errMsg")%>!</strong></div><%} %>
		<img src="./Public/images/change-password-img.png" class="user">
			<form action="confirmChangePassword" method="post">
				
				<input type="password" name="oldpassword" id="oldpassword" placeholder="Enter your old password">
				<input type="password" name="newpassword" id="newpassword" placeholder="Enter your new password">
				
				<label class="container" style="color:pink"><span class="checkpass" style="
    margin-left: -121px;
"><input id="show_password" type="checkbox"></span><span class="showpass" style="margin-left: -109px;">Show Password</span></label>  

				<input type="submit" name="" value="Change Password">
				
			</form>
		</div>
 <%@ include file="footer.jsp"%>