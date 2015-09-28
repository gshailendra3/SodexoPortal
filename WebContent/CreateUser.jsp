<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="sodexoPoc.UserSodexoDetails"%>
<%@ page import="sodexoPoc.Role" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome To Create User Page</title>
</head>
<script src="Util.js"></script>
<script>
	function showdv(obj) {
		var newDiv = document.createElement('div');
		divName = "sodexoDetailsDiv";
		txt = obj.options[obj.selectedIndex].text;
		var myNode = document.getElementById(divName);
		while (myNode.firstChild) {
			myNode.removeChild(myNode.firstChild);
		}
		document.getElementById(divName).style.display = 'none';
		if (txt.match("User")) {
			var html = '<input Type="text" id="amount"  maxlength="5" size="5" placeholder="Enter Amount" required="required">';
			html += '<select id="startingMonth">';
			html += "<option value=-1> Starting Month </option>";
			for (i = 1; i < monthArray.length; i++) {
				html += "<option value='"+i+"'>" + monthArray[i] + "</option>";
			}
			html += '</select>';

			html += '<select id="startingYear">';
			html += "<option value=-1> Starting Year </option>";
			for (i = 0; i < years.length; i++) {
				html += "<option value='"+i+"'>" + years[i]
						+ "</option>";
			}
			html += '</select>';
			html += '<input Type="text" id="numberOfMonths"  required="required" maxlength="5" size="10" placeholder="Number of Months">';
// 			html += '<button id="add" onclick="return addSelect(\'sodexoDetails\');">Add Sodexo details</button>';
			html += '<button id="add" onclick="return addTable(divName);">Add Sodexo details</button>';
			newDiv.innerHTML = html;
			document.getElementById(divName).appendChild(newDiv);
			document.getElementById(divName).style.display = 'block';
			//document.getElementById("boxx").value = txt
		}
	}
	
	//The Above function will create an array of five consecutive year from the the current year.

// 	function addSelect(divname) {
		
// 		var newDiv = document.createElement('div1');
// 		var html='<br/><b>Sodexo Details </b>';
// 		html += '<table border="1"><th>Amount</th><th>Date</th>';
// 		var startingMonth = document.getElementById("startingMonth").value;
// 		var startingYear = document.getElementById("startingYear").value;
// 		var couponAmount=document.getElementById("amount").value;
// 		var numberOfMonths=document.getElementById("numberOfMonths").value;
		
// 		if(startingMonth==-1 || startingYear==-1 || numberOfMonths==0 || couponAmount==0 )
// 		{
// 			alert("Pease fill all fields");
// 			return false;
// 		}
		
// 		for (j = 0; j < document.getElementById("numberOfMonths").value; j++) {
// 			html += "<tr><td>";
// 			html += '<input Type="text" name="amount"  size="5" value="'
// 					+ couponAmount + '">';
// 			html += "</td><td>";
// 			html += '<select name="month">';
// 			for (i = startingMonth; i < monthArray.length; i++) {
// 				html += "<option value='"+i+"'>" + monthArray[i]
// 						+ "</option>";
// 			}
// 			html += '</select>';

// 			html += '<select name="year">';
// 			for (i = startingYear; i < years.length; i++) {
// 				html += "<option value='"+years[i]+"'>" + years[i]
// 						+ "</option>";
// 			}
// 			html += '</select>' + "</td>" + "</tr>";
			
// 			startingMonth++;
// 			if(startingMonth>=monthArray.length)
// 			{
// 				startingMonth=1;
// 				startingYear++;
// 			}
// 		}
// 		html += "</table>";
		
// 		newDiv.innerHTML = html;
// 		var myNode = document.getElementById(divName);
// 		while (myNode.firstChild) {
// 			myNode.removeChild(myNode.firstChild);
// 		}
// 		document.getElementById(divname).appendChild(newDiv);
// 	}
	
	function addTable(divName)
	{
		var newDiv = document.createElement('div1');
		newDiv.innerHTML = '<br/><b>Sodexo Details </b>';
		var table = document.createElement('table');
		
		table.setAttribute("id", "sodexoDetails");
		table.setAttribute("border", "1");
		var header = table.createTHead();
		var row = header.insertRow(0);
	    var cell = row.insertCell(0);
	    cell.innerHTML = "<b>Amount</b>";
	    var cell1 = row.insertCell(1);
	    cell1.innerHTML = "<b>Date</b>";
	    var cell2 = row.insertCell(2);
	    cell2.innerHTML = '<input type="button" id="addSodexoDetail" value="Add Row" onclick="return insRow()" /></td>';
		newDiv.appendChild(table);
		
		var startingMonth = document.getElementById("startingMonth").value;
		var startingYear = document.getElementById("startingYear").value;
		var couponAmount=document.getElementById("amount").value;
		var numberOfMonths=document.getElementById("numberOfMonths").value;
		
		
//		var html='<br/><b>Sodexo Details </b>';
		
		
		if(startingMonth==-1 || startingYear==-1 || numberOfMonths==0 || couponAmount==0 )
		{
			alert("Pease fill all fields");
			return false;
		}
		
		var myNode = document.getElementById(divName);
		while (myNode.firstChild) {
			myNode.removeChild(myNode.firstChild);
		}
		myNode.appendChild(newDiv);
		
		
		for (j = 1; j <= numberOfMonths; j++) {
			//alert(startingMonth+"=="+years[startingYear]+"===="+numberOfMonths+couponAmount);
			var emel1=document.createElement("input");
			emel1.setAttribute("value", couponAmount);
			emel1.setAttribute("type", "hidden");
			emel1.setAttribute("id", "val1"+j);
			var emel2=document.createElement("input");
			emel2.setAttribute("value", startingMonth);
			emel2.setAttribute("type", "hidden");
			emel2.setAttribute("id", "val2"+j);
			var emel3=document.createElement("input");
			emel3.setAttribute("value", years[startingYear]);
			emel3.setAttribute("type", "hidden");
			emel3.setAttribute("id", "val3"+j);
			myNode.appendChild(emel1);
			myNode.appendChild(emel2);
			myNode.appendChild(emel3);
			insRow();
			startingMonth++;
			if(startingMonth >= monthArray.length)
			{
				startingMonth=1;
				startingYear++;
			}
		}
		
	}
	
	function goToAdminLoginSuccess(){
	       window.location = './AdminLoginSuccess.jsp';
	}
</script>
<body bgcolor="#E6E6FA">
	<p align="right">
		<a href="LogOut.jsp">Logout</a>
	</p>
	<center>
		<form action=CreateUserServlet Method=Post>

			<fieldset style="width: 600px">
				<legend> Fill User Data </legend>
				<table>
					<tr>
						<td>Employee ID</td>
						<td><input type="text" name="empId" required="required" /></td>
					</tr>

					<tr>
						<td>Password</td>
						<td><input type="password" name="passwd" required="required" /></td>

					</tr>

					<tr>
						<td>Employee Name</td>
						<td><input type="text" name="name" required="required" /></td>

					</tr>

					<tr>
						<td>Employee email</td>
						<td><input type="text" name="email" required="required" /></td>

					</tr>

					<tr>
						<td>Role</td>
						<td><select id="u_role" name="role" onchange="showdv(this);">
								<option value="" />
								<option value="<%=Role.USER.getValue() %>">User</option>
								<option value="<%=Role.ADMIN.getValue() %>">Admin</option>
								<option value="<%=Role.DISTRIBUTER.getValue() %>">Distributer</option>
						</select></td>
					</tr>
				</table>

				<div id="sodexoDetailsDiv" style="display: none;">
				<b>Sodexo Details</b>
				</div>
				</p>
				</p>

			</fieldset>
			</p>
			<center>
				<input type="submit" id="submit" value="Submit" />
				 <i style="color:#FF0000"><%= request.getAttribute("msg")==null?"":request.getAttribute("msg") %></i>
			</center>
		</form>
	</center>
	<input type=button name=back value=Back onClick="goToAdminLoginSuccess();" />
	
</body>

</html>