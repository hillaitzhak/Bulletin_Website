<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
  <title>Bulletin Website</title>
  <meta charset="utf-8">
  <script src="js\sc.js" ></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<!-- Header For All Pages -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/SecureDev/Home">Bulletin Tracking Website</a>
    </div>
<!-- Navigation Bar all pages are mapped in the xml file to the correct controller -->
    <ul class="nav navbar-nav">
      <li><a href="/SecureDev/BulletinList">Bulletin List</a></li>
      <li><a href="/SecureDev/CustomList">Custom List</a></li>
      <li><a href="/SecureDev/Analytics">Analytics</a></li>
      <li><a href="/SecureDev/logout">LogOut</a></li>
<!-- Making sure the user is an admin in order to know if to present the admin tab -->
      <%
      if(session.getAttribute("isAdmin").equals(true))
    	 out.print("<li><a href=\"/SecureDev/Admin\">Admin</a></li>");
      %>

    </ul>
  </div>
</nav>	
</body>
</html>