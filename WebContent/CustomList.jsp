<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Bulletin"%>
<%@page import="model.User"%>
<%@page import="model.Logs"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
		if(session.getAttribute("username") != null){
			String username = session.getAttribute("username").toString();
		}
List<Bulletin> BulletinList = (List<Bulletin>)request.getAttribute("allbulletins");
List<User> UserList = (List<User>)request.getAttribute("allusers");
%>

<html lang="en">
<head>
<title>Custom Bulletin Link Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script> 
</head>
<style>

.dropdown {
    position: relative;
    display: inline-block;

}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    padding: 12px 16px;
    z-index: 1;
}

.dropdown:hover .dropdown-content {
    display: block;
}
</style>
<body>
<!-- Page to choose custom view -->
<jsp:include page="Header.jsp" />
<form class=" animate" name="CustomList" id="CustomList" action="CustomList" method="post">

<div class="row">
<div class="col-md-3">
<select name="ProductSelcted" class=" btn btn-danger dropdown-toggle form-control" id="ProductSelcted">
	<option class="dropdown-content dropdown-item" value="1" selected="selected">Product</option>
      
<%
	for(Bulletin bulletin:BulletinList)
	{	
     	out.print(
                  "<option class=\"dropdown-item\" value="+bulletin.getProduct()+">"+bulletin.getProduct()+"</option>");
	}

%>
</select>
</div>
<div class="col-md-3">
<select name="CVESelcted" class="  btn btn-danger dropdown-toggle form-control" id="CVESelcted">
	<option class="dropdown-content dropdown-item" value="1" selected="selected">CVE</option>
      
<%
	for(Bulletin bulletin:BulletinList)
	{	
     	out.print(
                  "<option class=\"dropdown-item\" value="+bulletin.getCVE()+">"+bulletin.getCVE()+"</option>");
	}

%>
</select>
</div>
<div class="col-md-3">
<select name="contactSelcted" class="  btn btn-danger dropdown-toggle form-control" id="contactSelcted">
	<option class="dropdown-content dropdown-item" value="1" selected="selected">Contact</option>
      
<%
	for(Bulletin bulletin:BulletinList)
	{	
     	out.print(
                  "<option class=\"dropdown-item\" value="+bulletin.getContact()+">"+bulletin.getContact()+"</option>");
	}

%>
</select>
</div>
<div class="col-md-3">
<select name="statusSelcted" class=" btn btn-danger dropdown-toggle form-control" id="statusSelcted">
	<option class="dropdown-content dropdown-item" value="1" selected="selected">Status</option>
      
<%
	for(Bulletin bulletin:BulletinList)
	{	
     	out.print(
                  "<option class=\"dropdown-item\" value="+bulletin.getStatus()+">"+bulletin.getStatus()+"</option>");
	}

%>
</select>
</div>
</div>
<br>
<br>
<button name="CustomList" type="submit" class="btn btn-default btn-sm">
   <span class="glyphicon glyphicon-ok" value="Create Bulletin"></span> Submit
</button>
</form>
<br>
<br>
<!-- Table of the selected items -->
<div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i><h3> Bulletin Table:</h3></div><br>
          <div class="table-responsive">
            <table class="table table-bordered" id="bulletinTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Product</th>
                  <th>Open Date</th>
                  <th>Link</th>
                  <th>CVE</th>
                  <th>contact</th>
                  <th>Status</th>
                  <th>Close Date</th>
                </tr>
              </thead>
              <tbody>
<%
	for(Bulletin bulletin:BulletinList)
	{	
     	out.print("<tr>"+
                  "<td>"+bulletin.getId()+"</td>"+
                  "<td>"+bulletin.getProduct()+"</td>"+
                  "<td>"+bulletin.getOpenDate()+"</td>"+
                  "<td>"+bulletin.getLink()+"</td>"+
                  "<td>"+bulletin.getCVE()+"</td>"+
                  "<td>"+bulletin.getContact()+"</td>"+
                  "<td>"+bulletin.getStatus()+"</td>"+
                  "<td>"+bulletin.getCloseDate()+"</td>"+
                  "</tr>");
	}

%>
				</tbody>
            </table>
          </div>
      </div>

<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>