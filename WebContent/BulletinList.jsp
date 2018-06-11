<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Bulletin"%>
<%@page import="model.User"%>

<%@ page language="java" contentType="text/html; charset=windows-1255" pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<Bulletin> BulletinList = (List<Bulletin>)request.getAttribute("allbulletins");
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
  <title>Full Bulletin List</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<!-- Page that hold all the bulletins -->
<jsp:include page="Header.jsp" />
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i><h3> Bulletins Table:</h3></div>
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
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
</body>
</html>