<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Bulletin"%>
<%@page import="utils.OpenCloseChart"%>
<%@page import="model.User"%>
<%@page import="model.Logs"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject;"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
		if(session.getAttribute("username") != null){
			String username = session.getAttribute("username").toString();
		}
List<Bulletin> BulletinList = (List<Bulletin>)request.getAttribute("allbulletins");
List<OpenCloseChart> opList = (List<OpenCloseChart>)request.getAttribute("op");



%>
<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();


for(OpenCloseChart OP:opList){
		map = new HashMap<Object,Object>(); map.put("label",OP.getProduct()); map.put("y", OP.getOpen()); list.add(map);	
}
String dataPoints = gsonObj.toJson(list);
list = new ArrayList<Map<Object,Object>>();

for(OpenCloseChart OP:opList){
	map = new HashMap<Object,Object>(); map.put("label",OP.getProduct()); map.put("y", OP.getClose()); list.add(map);	
}

String dataPoints1 = gsonObj.toJson(list);
int numPC=0;
int numNA=0;
int numUCMDB=0;
int numSIS=0;
for(Bulletin bulletin:BulletinList){
	if (bulletin.getProduct().compareTo("PC")==0){
		numPC++;
	}
	if (bulletin.getProduct().compareTo("NA")==0){
		numNA++;
	}

	if (bulletin.getProduct().compareTo("UCMDB")==0){
		numUCMDB++;
	}
	if (bulletin.getProduct().compareTo("SIS")==0){
		numSIS++;
	}
}
list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("label", "PC"); map.put("y", numPC); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "UCMDB"); map.put("y", numUCMDB); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "NA"); map.put("y", numNA); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "SIS"); map.put("y", numSIS); list.add(map);
String dataPoints2 = gsonObj.toJson(list);
%>

<html lang="en">
<head>
<title>Analytics Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script> 
  <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    
<script type="text/javascript">
window.onload = function() { 
 
var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,  	
	title:{
		text: "All Products By Open/Close"
	},
	subtitles: [{
		text: ""
	}],
	axisY: {
		title: "Amount"
	},
	toolTip: {
		shared: true,
		reversed: true
	},
	legend: {
		reversed: true,
		horizontalAlign: "right",
		verticalAlign: "center"
	},
	data: [{
		type: "column",
		name: "Open",
		showInLegend: true,
		yValueFormatString: "#,##0 ",
		dataPoints: <%out.print(dataPoints);%>
	},
	{
		type: "column",
		name: "Close",
		showInLegend: true,
		yValueFormatString: "#,##0 ",
		dataPoints: <%out.print(dataPoints1);%>
	}]
});
chart.render();
var chart2 = new CanvasJS.Chart("chartContainer2", {
	animationEnabled: true,
	title: {
		text: "All Bulletins By Product"
	},
	axisX: {
		title: ""
	},
	axisY: {
		title: "Amount"
	},
	data: [{
		type: "column",
		yValueFormatString: "$#,##0",
		showInLegend: true,
		dataPoints: <%out.print(dataPoints2);%>
	}]
});
chart2.render();
 
}
</script>
</head>
<body >
<jsp:include page="Header.jsp" />
<!-- Charts page -->
<div class="row">
<div id="chartContainer" style="padding-left: 25%; height: 370px; width: 50%;"></div>
<div id="chartContainer2" style="padding-left: 25%; height: 370px; width: 50%;"></div>
</div>
</body>
</html>