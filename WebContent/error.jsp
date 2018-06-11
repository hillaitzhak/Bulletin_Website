<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
<jsp:include page="FirstHeader.jsp"></jsp:include>
<%
	if(request.getAttribute("error")!=null){
		out.print("<h1><center>"+request.getAttribute("error")+"</center></h1>");
	}
%>
</body>
</html>