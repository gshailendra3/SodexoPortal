<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<SCRIPT TYPE="text/javascript">
function generate_table() {
	  // get the reference for the body
	  var body = document.getElementsByTagName("body")[0];
	 
	  // creates a <table> element and a <tbody> element
	  var tbl     = document.createElement("table");
	  var tblBody = document.createElement("tbody");
	 
	  // creating all cells
	  for (var i = 0; i < 2; i++) {
	    // creates a table row
	    var row = document.createElement("tr");
	 
	    for (var j = 0; j < 2; j++) {
	      // Create a <td> element and a text node, make the text
	      // node the contents of the <td>, and put the <td> at
	      // the end of the table row
	      var cell = document.createElement("td");
	      var cellText = document.createTextNode("cell in row "+i+", column "+j);
	      cell.appendChild(cellText);
	      var s=document.createElement("select");
	      var o=document.createElement("option");
	      o.text="hello";
	      s.add(o);
	      var o1=document.createElement("option");
	      o1.text="hello-1";
	      s.add(o1);
	      var cell1 = document.createElement("td");
	      cell1.appendChild(s);
	      row.appendChild(cell);
	      row.appendChild(cell1);
	    }
	 
	    // add the row to the end of the table body
	    tblBody.appendChild(row);
	  }
	 
	  // put the <tbody> in the <table>
	  tbl.appendChild(tblBody);
	  // appends <table> into <body>
	  body.appendChild(tbl);
	  // sets the border attribute of tbl to 2;
	  tbl.setAttribute("border", "2");
	}
	</script>
  
<body>
<input type="button" value="Generate a table." onclick="generate_table()">
</body>
</html>