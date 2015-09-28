<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page import="sodexoPoc.Role"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify User Details</title>

</head>
<script src="Util.js"></script>

<jsp:useBean id="userDetailsBean" class="sodexoPoc.UserSodexoDetails"
	scope="session" />
<body bgcolor="#E6E6FA">
<script src="Util.js"></script>
	<center>
		<form action=UpdateUserDataServlet method=POST>
			<p align="right">
				<a href="LogOut.jsp">Logout</a>
			</p>
			<center>
				<b>Welcome to Sodexo Account Portal</b>
			</center>
			</br>
			</p>
			<fieldset style="width: 340px">
				<table>
					<tr>
						<td>Employee ID</td>
						<td><input type="text" name="empId" required="required"
							value="${userDetailsBean.getEmpId()}" readonly /></td>
					</tr>

					<tr>
						<td>Employee Name</td>
						<td><input type="text" name="name" required="required"
							value="${userDetailsBean.getName()}" /></td>

					</tr>

					<tr>
						<td>Employee email</td>
						<td><input type="text" id="mail" name="email"
							required="required" value="${userDetailsBean.getEmail()}" /></td>

					</tr>
				</table>
				</br>
				<b>Sodexo Details</b>
				<%
					if (userDetailsBean.getRole() == Role.USER) {
				%>
				<table border="1" width="100%" id="sodexoDetails">

					<tr>
						<th>Amount</th>
						<th width="100%">Date</th>
						<td><input type="button" id="addSodexoDetail" value="Add Row"
							onclick="return insRow()" /></td>
					</tr>
					<c:set var="count" value="1" scope="page" />
					<c:forEach items="${userDetailsBean.getSodexoList()}" var="sodexo">
						<input type="hidden" name="val1${count}"
							value="${sodexo.getAmount()}" id="val1${count}" />
						<input type="hidden" name="val2${count}"
							value="${sodexo.getDate().substring(5,7)}" id="val2${count}" />
						<input type="hidden" name="val3${count}"
							value="${sodexo.getDate().substring(0,4)}" id="val3${count}" />
						<c:set var="count" value="${count + 1}" scope="page" />
						<script>
							insRow()
						</script>
					</c:forEach>
				</table>
				<%
					}
				%>
				<div></div>
			</fieldset>
			<br>
			<i style="color: #FF0000"><%=request.getAttribute("msg") == null ? "" : request
					.getAttribute("msg")%></i> 
			<center>
				<input type="submit" value="Submit" /> <input type=button name=back value=Back onClick="goToAdminLoginSuccess();" />
			</center>
		</form>
	</center>
</body>
</html>