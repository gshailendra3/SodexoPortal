<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#E6E6FA">
 <center>
        <form action=LoginServlet> 
        	<p align="right"><a href="LogOut.jsp">Logout</a></p>
            <fieldset style="width: 300px">
            <legend> Manage User Data </legend>
	           <table>
	
				<tr>
					<td><input type="button" id="idname" value = "Create User " onclick="goToCreateUser()"/></td>
				
					<td><input type="button" id="idname" value = "Modify User " onclick="goToModifyUser()"/></td>
					
					<td><input type="button" id="idname" value = "Delete User " onclick="goToDeleteUser()"/></td>
				</tr>
				
			</table>
        </fieldset>
        </form>
    </center>  
    
   <script type="text/javascript">
   function goToCreateUser(){
       window.location = './CreateUser.jsp';
   }

   function goToModifyUser(){
       window.location = './ModifyUser.jsp';
   }
   
   function goToDeleteUser(){
       window.location = './DeleteUser.jsp';
   }
   </script>
   
</body>
</html>