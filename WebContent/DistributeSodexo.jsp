<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
var is_checked = false;
$('input[type="checkbox"]').each(function() {
    if ($(this).is(":checked")) {
        is_checked = true;
    }
});
</script>
</head>
<jsp:useBean id="userDetailsBean" class="sodexoPoc.UserSodexoDetails"
	scope="session" />
<body>
    
	<center>
	<form name="form1" action=IssueCouponServlet method=POST >  <p align="right"><a href="LogOut.jsp">Logout</a></p>
		<center><b>Welcome to Sodexo Distribution Portal</b></center> 
		
		</br>
		<TH><b>EmpId:</b></th>
		<td><c:out value="${userDetailsBean.getEmpId()}," /></td>
		<TH><b>Name:</b></th>
		<td><c:out value="${userDetailsBean.getName()}" /></td>
        <p></p>
        <center>Pending coupon amount is ${userDetailsBean.getPendingAmount()}   </center>  	
        <p></p>
        
		<table border="2" width="70%">

			<tr>
				<TH>Amount</TH>
				<TH>Month</TH>
				<TH>Status</TH>
				<TH>Issue Date</TH>
				<TH>Select</TH>

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
					<td><input type="checkbox" name="${sodexo.getDate()}" value="true"></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<p></p><p></p>
		<input type=submit value="Issue Coupon" onclick="checkBoxValidation();">
	</form>	
	</center>
</body>

</html>