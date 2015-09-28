<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="javascript" type="text/javascript">
function removeSpaces(string) {
 return string.split(' ').join('');
 
}
</script>
</head>
<body>
<p align="right"><a href="LogOut.jsp">Logout</a></p>
  <center>
        <form action=SodexoDistributionServlet method=POST> 
            <fieldset style="width: 300px">
            <legend> Welcome to Sodexo Distribution Page </legend>
            <table>
				<tr>
				<td>User PIN</td>
					<td><input type="text" name="userPin" required="required" onBlur="this.value=removeSpaces(this.value);"/></td>
				</tr>
				
				<tr>
					<td><input type="submit" value="Search" /></td>
				</tr>
					 <i style="color:#FF0000"><%= request.getAttribute("errorMessage")==null?"":request.getAttribute("errorMessage") %></i>
			</table>
        </fieldset>
		<input type=button name=back value=Back onClick="history.go(-1);" />
        </form>
    </center>  
</body>
</html>