/**
 * 
 */
function goToAdminLoginSuccess() {
	window.location = './AdminLoginSuccess.jsp';
}

function yearGenerate() {
	var date = new Date(), yearArray = new Array(), i;
	curYear = date.getFullYear();
	for (i = 0; i < 5; i++) {
		yearArray[i] = curYear + i;
	}
	return yearArray;
}

var monthArray = [ "None", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
		"Aug", "Sept", "Oct", "Nov", "Dec" ];
var years = yearGenerate();

function deleteRow(row) {
	
	var i = row.parentNode.parentNode.sectionRowIndex+1;
	//var i = row.parentNode.parentNode.rowIndex;
	document.getElementById('sodexoDetails').deleteRow(i);
}

function insRow() {
	var x = document.getElementById('sodexoDetails');

	var len = x.rows.length;
	var val1 = document.getElementById("val1" + len) == null ? "" : document
			.getElementById("val1" + len).value;
	var val2 = document.getElementById("val2" + len) == null ? "" : document
			.getElementById("val2" + len).value;
	var val3 = document.getElementById("val3" + len) == null ? "" : document
			.getElementById("val3" + len).value;
	//alert("hi"+ val1 +" " +val2+" "+val3);
	var row = '';
	console.log(x.rows.length);
	if (x.rows.length >= 1) {
		row = document.createElement("tr");
		var cell1 = document.createElement("td");
		var emel1 = document.createElement("input");
		emel1.setAttribute("value", val1);
		emel1.setAttribute("size", "5");
		emel1.setAttribute("name", "amount");
		var cell2 = document.createElement("td");
		//cell2.setAttribute("width","100%");
		var s1 = document.createElement("select");
		s1.setAttribute("name", "month");
		for (i = 1; i < monthArray.length; i++) {

			var opt = document.createElement("option");
			opt.text = monthArray[i];
			opt.setAttribute("value", i);
			if (i == val2)
				opt.setAttribute("selected", "selected");
			s1.add(opt);
		}
		var s2 = document.createElement("select");
		s2.setAttribute("name", "year");
		for (i = 0; i < years.length; i++) {

			var opt = document.createElement("option");
			opt.text = years[i];
			opt.setAttribute("value", years[i]);
			if(years[i]==val3)
				opt.setAttribute("selected", "selected");
			s2.add(opt);
		}
		var cell3 = document.createElement("td");
		var emel3 = document.createElement("input");
		//type="button" id="deleteSodexoDetail" value="Delete" onclick="deleteRow(this)
		emel3.setAttribute("type", "button");
		emel3.setAttribute("id", "deleteSodexoDetail");
		emel3.setAttribute("value", "Delete  ");
		emel3.setAttribute("onclick", "return deleteRow(this)");
		cell1.appendChild(emel1);
		cell2.appendChild(s1);
		cell2.appendChild(s2);
		cell3.appendChild(emel3);
		row.appendChild(cell1);
		row.appendChild(cell2);
		row.appendChild(cell3);
	} else {
		row = x.rows[1].cloneNode(true);

		var inp1 = row.cells[0].getElementsByTagName('input')[0];
		inp1.id += len;
		inp1.value = val1;
		var inp2 = row.cells[1].getElementsByTagName('select')[0];
		inp2.id += len;
		inp2.value = val2;
		var inp2 = row.cells[1].getElementsByTagName('select')[1];
		inp2.id += len;
		inp2.value = val3;
	}
	x.appendChild(row);
	return false;
}