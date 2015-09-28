<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page import="sodexoPoc.Role" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete User</title>
<script src="Util.js"></script>
</head>
<jsp:useBean id="userDetailsBean" class="sodexoPoc.UserSodexoDetails"
	scope="session" />
<body bgcolor="#E6E6FA">
    
	<center>
	<form name="tstest" action=DeleteUserDataServlet method=POST>  
		<p align="right">
			<a href="LogOut.jsp">Logout</a>
		</p>
		<center><b>Welcome to Sodexo Account Portal</b></center> 
			</br>              
        </p>
        <fieldset style="width: 340px">
        <table>
					<tr>
						<td>Employee ID</td>
						<td><input type="text" name="empId" required="required"  value="${userDetailsBean.getEmpId()}" readonly/></td>
					</tr>

					<tr>
						<td>Employee Name</td>
						<td><input type="text" name="name" required="required" value="${userDetailsBean.getName()}" readonly/></td>

					</tr>

					<tr>
						<td>Employee email</td>
						<td><input type="text" id="mail" name="email" required="required" value="${userDetailsBean.getEmail()}" readonly/></td>

						
					</tr>
					<tr>
						<td>Role</td>
						<td><input type="text" name="role" required="required" value="${userDetailsBean.getRole()}" readonly/></td>
					</tr>
		</table>
		</br>
		<% if (userDetailsBean.getRole() == Role.USER) { %>
		<center>Pending coupon amount is Rs.${userDetailsBean.getPendingAmount()} 
        	<c:if test="${userDetailsBean.getPendingAmount() > 0}" > 

        	<td>.<td> 	
        	</c:if>
        </center>
		        </br>
		<table border="2" width="100%">

			<tr>
				<TH>Amount</TH>
				<TH>Month</TH>
			</tr>
						
			<c:forEach items="${userDetailsBean.getSodexoList()}" var="sodexo">
				<tr>
					<td><input type="text" name="amount" required="required" value="${sodexo.getAmount()}" readonly/></td>
					<td><input type="text" name="amount" required="required" value="${sodexo.getDate()}" readonly/></td>
				</tr>
			</c:forEach>
		</table>
		<% } %>
		</fieldset>
		<br>
		<center>
			<input type="submit" value="Delete" />
			<i style="color:#FF0000"><%= request.getAttribute("msg")==null?"":request.getAttribute("msg") %></i>
			<input type=button name=back value=Back onClick="goToAdminLoginSuccess();" />
		</center>
	</form>	
	</center>
</body>
</html>