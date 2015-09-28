<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Form</title>
</head>
<body bgcolor="#E6E6FA">
<p align="right"><a href="LogOut.jsp">Logout</a></p>
  <center>
        <form action=DeleteUserServlet>
            <fieldset style="width: 300px">
            <legend> Delete User Details </legend>
            <table>
				<tr>
				<td>User ID</td>
					<td><input type="text" name="userId" required="required" /></td>
				</tr>
				
				<tr>
					<td><input type="submit" value="Search" /></td>
				</tr>
			</table>
        </fieldset>
		<input type=button name=back value=Back onClick="history.go(-1);" />
        </form>
        <i style="color:#FF0000"><%= request.getAttribute("msg")==null?"":request.getAttribute("msg") %></i>
    </center>  
	
</body>
</html>