<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function showdv(obj,id1,id2)
{
txt=obj.options[obj.selectedIndex].text; 
document.getElementById("box").style.display='none';
if(txt.match(id1))
{
document.getElementById("box").style.display='block';
document.getElementById("boxx").value=txt
}
if(txt.match(id2))
{
document.getElementById("box").style.display='block';
document.getElementById("boxx").value=txt
}
}
</script>
<body>

<select id="opts" onchange="showdv(this,'one','two');" >
<option value="">select</option>
<option value="one">one</option>
<option value="two">two</option>
</select>

<div id="box" style="display:none;">			
<input Type="text" id="boxx"  maxlength="20" >
</div>
</body>
</html>