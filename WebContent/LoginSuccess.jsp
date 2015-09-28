<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Success</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
$(document).ready(function(){
	$('#generatePIN').click(function(e){
		
	$.ajax({
	    url : "GeneratePinServlet",
	    type: 'GET',
	    data: {EmpId:$('#generatePIN').val()},
	    success : function(result){
	    	$('#generatePIN').hide();
	       $("#genPin").html(result);
	       }
	});
	});

});
</script>
</head>
<jsp:useBean id="userDetailsBean" class="sodexoPoc.UserSodexoDetails"
	scope="session" />
<body bgcolor="#E6E6FA">
    
	<center>
	<form method=POST>  <p align="right"><a href="LogOut.jsp">Logout</a></p>
		<center><b>Welcome to Sodexo Account Portal</b></center> 
		
		</br>
		<TH><b>EmpId:</b></th>
		<td><c:out value="${userDetailsBean.getEmpId()}," /></td>
		<TH><b>Name:</b></th>
		<td><c:out value="${userDetailsBean.getName()}" /></td>
        </p>
        <center>Pending coupon amount is ${userDetailsBean.getPendingAmount()} 
        	<c:if test="${userDetailsBean.getPendingAmount() > 0}" >
        	<c:if test="${userDetailsBean.getPin() > 0}" >  
        		<div id="genPin" style="color:#0000FF">Your Pin is ${userDetailsBean.getPin()} </div>
        	</c:if>
        	<c:if test="${userDetailsBean.getPin() <= 0}" >  
        	<div id="genPin" style="color:#0000FF"></div>
			<button type="button" id="generatePIN" value="${userDetailsBean.getEmpId()}">Generate PIN</button>
			</c:if>
<!--         	<td><input type=submit value="Generate PIN"></td> -->
        	</c:if>
        </center>
        
        </p>
        
		<table border="2" width="70%">

			<tr>
				<TH>Amount</TH>
				<TH>Month</TH>
				<TH>Status</TH>
				<TH>Issue Date</TH>

			</tr>
			<c:forEach items="${userDetailsBean.getSodexoList()}" var="sodexo">
				<tr>
					<td><c:out value="${sodexo.getAmount()}" /></td>
					<td><c:out value="${sodexo.getDate()}" /></td>
					<c:if test="${sodexo.getStatus()}" >
					<td>Issued</td>
					<td>${sodexo.getIssueDate()}</td>
					</c:if >
					<c:if test="${sodexo.getStatus() ne 'true'}">
					<td>Pending</td>
					<td>0000-00-00</td>
					</c:if>
					
				</tr>
			</c:forEach>
		</table>
	</form>	
	</center>
</body>
</html>