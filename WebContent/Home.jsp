<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="controllers.HomeController" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Bulletin Tracking Website</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
  <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    html,body,h1,h2,h3,h4,h5 {font-family: "Open Sans", sans-serif}
  </style>
</head>
<body class="w3-theme-l5">
<!-- Home page -->
<jsp:include page="Header.jsp" />
<!-- User information -->
<div class="w3-container w3-content" style="max-width:1400px;margin-top:80px">    
<!-- The Grid -->
  <div class="w3-row">
<!-- Left Column -->
    <div class="w3-col m3">
<!-- Profile -->
		<div class="w3-card w3-round w3-white">
		   <div class="w3-container">
		     <h4 class="w3-center">My Information</h4>
		     <hr>
		     <p><i class="fa fa-user fa-fw w3-margin-right w3-text-theme"></i> Name: ${sessionScope.firstname} ${sessionScope.lastname}</p>
		     <p><i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i> Email: ${sessionScope.email}</p>
		     <p><i class="fa fa-birthday-cake fa-fw w3-margin-right w3-text-theme"></i> Birth Date: ${sessionScope.bdate}</p>
		   </div>
		</div>
	</div>
	<div class="w3-col m7">
		<div class="w3-row-padding">
       		 <div class="w3-col m12">
          		<div class="w3-card w3-round w3-white">
            		<div class="w3-container w3-padding">
<!-- Checking the name of the user in order to present it -->
              			<h4 class="w3-center">Welcome ${sessionScope.firstname}</h4>
		  				<p class="w3-center">You are in the Bulletin website</p><br>
		  				<p class="w3-center">Here you can find all the security bulletin's external links</p><br>
            		</div>
          		</div>
        	</div>
      	</div>
      </div>
   </div>
<br>
<br>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>