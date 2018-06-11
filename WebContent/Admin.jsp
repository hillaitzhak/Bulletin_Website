<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Bulletin"%>
<%@page import="model.User"%>
<%@page import="model.Logs"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
		if(session.getAttribute("username") != null){
			String username = session.getAttribute("username").toString();
		}
List<Bulletin> BulletinList = (List<Bulletin>)request.getAttribute("allbulletins");
List<Logs> LogsList = (List<Logs>)request.getAttribute("alllogs");
List<User> UserList = (List<User>)request.getAttribute("allusers");
List<User> PanddingList = (List<User>)request.getAttribute("allpandding");
%>

<html lang="en">
<head>
<title>Admin Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script> 
</head>
<style>
*{margin:0px; padding:0px; font-family:Helvetica, Arial, sans-serif;}
input[type=text], input[type=password],input[type=date],input[type=email] {
    width: 50%;
    padding: 12px 20px;
    margin: 8px 26px;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
	font-size:16px;
}
/* Set a style for all buttons */

button:hover {
    opacity: 0.8;
}

/* The Modal (background) */
.modal {
	display:none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow-y:scroll;
    background-color: rgba(0,0,0,0.4);
}

/* Modal Content Box */
.modal-content {
    background-color: #fefefe;
    margin: 4% auto 15% auto;
    border: 1px solid #888;
    width: 60%; 
	overflow-y:scroll;
}

/* The Close Button (x) */
.close {
    position: absolute;
    right: 25px;
    top: 0;
    color: #000;
    font-size: 35px;
    font-weight: bold;
}
.close:hover,.close:focus {
    color: red;
    cursor: pointer;
}

/* Add Zoom Animation */
.animate {
    animation: zoom 0.6s
}
@keyframes zoom {
    from {transform: scale(0)} 
    to {transform: scale(1)}
}
</style>
<body onload="hide()">
<jsp:include page="Header.jsp" />

<button onclick="HideShowUser()" type="submit" class="btn btn-info btn-lg">
	<span class="glyphicon glyphicon-user" value="Edit"></span> Users Information 
</button>

<button onclick="HideShowLogs()" type="submit" class="btn btn-info btn-lg">
	<span class="glyphicon glyphicon-list-alt" value="Edit"></span> Logs 
</button>

<button onclick="HideShowBulletin()" type="submit" class="btn btn-info btn-lg">
	<span class="glyphicon glyphicon-list-alt" value="Edit"></span> Bulletin Information 
</button>

<button onclick="HideShowPandding()" type="submit" class="btn btn-info btn-lg">
	<span class="glyphicon glyphicon-hourglass" value="Edit"></span> Pandding Requests  
</button>

<div id="pandding">
	  <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i><h3> Pendding Table:</h3></div> 
          <div class="table-responsive">
            <table class="table table-striped table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th >ID</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Birth Date</th>
                  <th>Email</th>
                  <th>User Name</th>
                  <th>Telephone Number</th>
                  <th>Is Admin</th>
                  <th>Approve Request</th>
                  <th>Delete User</th>
                </tr>
              </thead>
              <tbody>
<%
	for(User user:PanddingList)
	{	
     	out.print("<tr >"+
                  "<td>"+user.getId()+"</td>"+
                  "<td>"+user.getFirstName()+"</td>"+
                  "<td>"+user.getLastName()+"</td>"+
                  "<td>"+user.getBday()+"</td>"+
                  "<td>"+user.getEmail()+"</td>"+
                  "<td>"+user.getUsername()+"</td>"+
                  "<td>"+user.getTelephone()+"</td>"+
                  "<td name=\"admin\">"+user.isAdmin()+"</td>"+
                  "<td>"+"<form name=\"deleteu\"action=\"Admin\" method=\"post\">"+
                  "<input type=\"hidden\" name=\"hiddenApprove\"value="+user.getId()+">"+"</input>"+
                  "<input type=\"hidden\" name=\"hiddenEmail\"value="+user.getEmail()+">"+"</input>"+
                  "<button type=\"submit\" class=\"btn btn-default btn-sm\">"+
                  "<span class=\"glyphicon glyphicon-ok\" value=\"Change\">"+"</span>"+" Approve"+ 
                  "</button>"+"</form>"+"</td>"+
                  "<td>"+"<form name=\"deleteu\"action=\"Admin\" method=\"post\">"+
                  "<input type=\"hidden\" name=\"hiddenut\"value="+user.getId()+"></input>"+
                  "<button type=\"submit\" class=\"btn btn-default btn-sm\">"+
                  "<span class=\"glyphicon glyphicon-trash\" value=\"Delete\">"+"</span>"+" Delete"+ 
                  "</button>"+"</form>"+"</td>"+
               	  "</tr>");
	}
%>
				</tbody>
            </table>
          </div>
      </div>
  </div>

<div id="users">
	  <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i><h3> Users Table:</h3></div> 
          <div class="table-responsive">
            <table class="table table-striped table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th >ID</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Birth Date</th>
                  <th>Email</th>
                  <th>User Name</th>
                  <th>Telephone Number</th>
                  <th>Is Admin</th>
                  <th>Change Admin Status</th>
                  <th>Delete User</th>
                </tr>
              </thead>
              <tbody>
<%
	for(User user:UserList)
	{	
     	out.print("<tr >"+
                  "<td>"+user.getId()+"</td>"+
                  "<td>"+user.getFirstName()+"</td>"+
                  "<td>"+user.getLastName()+"</td>"+
                  "<td>"+user.getBday()+"</td>"+
                  "<td>"+user.getEmail()+"</td>"+
                  "<td>"+user.getUsername()+"</td>"+
                  "<td>"+user.getTelephone()+"</td>"+
                  "<td name=\"admin\">"+user.isAdmin()+"</td>"+
                  "<td>"+"<form name=\"deleteu\"action=\"Admin\" method=\"post\">"+
                  "<input type=\"hidden\" name=\"hiddenAdmin\"value="+user.getId()+">"+"</input>"+
                  "<button type=\"submit\" class=\"btn btn-default btn-sm\">"+
                  "<span class=\"glyphicon glyphicon-pencil\" value=\"Change\">"+"</span>"+" Change"+ 
                  "</button>"+"</form>"+"</td>"+
                  "<td>"+"<form name=\"deleteu\"action=\"Admin\" method=\"post\">"+
                  "<input type=\"hidden\" name=\"hiddenu\"value="+user.getId()+"></input>"+
                  "<button type=\"submit\" class=\"btn btn-default btn-sm\">"+
                  "<span class=\"glyphicon glyphicon-trash\" value=\"Delete\">"+"</span>"+" Delete"+ 
                  "</button>"+"</form>"+"</td>"+
               	  "</tr>");
	}
%>
				</tbody>
            </table>
          </div>
      </div>
  </div>

<div id="bull">
       <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i><h3> Bulletin Table:</h3></div>
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
                  "<td>"+"<form name=\"deleteb\"action=\"Admin\" method=\"post\">"+
                  "<input type=\"hidden\" name=\"hiddenb\" value="+bulletin.getId()+"></input>"+
                  "<button type=\"submit\" class=\"btn btn-default btn-sm\">"+
                  "<span class=\"glyphicon glyphicon-trash\" value=\"Delete\">"+"</span>"+" Delete"+ 
                  "</button>"+"</form>"+"</td>"+
                  "</tr>");
	}

%>
				</tbody>
            </table>
          </div>
      </div>

	<button onclick="document.getElementById('modal-wrapper-newBulletin').style.display='block'" type="submit" class="btn btn-default btn-sm">
	<span class="glyphicon glyphicon-plus" value="newB"></span> New 
	</button>
         <div id="modal-wrapper-newBulletin" class="modal">
      <form class="modal-content animate" name="newBulletin" id="newBulletin" class="newBulletin" action="Admin" method="post">
      		<div class="imgcontainer">
				<span onclick="document.getElementById('modal-wrapper-newBulletin').style.display='none'" class="close" title="Close PopUp">&times;</span>
				<h1 style="text-align:center">Create New Bulletin</h1>
			</div>
			<div class="container">
					<div class="modal-body">
						
						<select name="Product">
						  <option value="UCMDB">UCMDB</option>
						  <option value="SIS">SIS</option>
						  <option value="NA">NA</option>
						  <option value="PC">PC</option>
						</select>
						
						<br>
						<br>
						<br>

						<input type="text" name="Link" class="form-control" placeholder="Link"/>

						<input type="text" name="CVE" class="form-control" placeholder="CVE"/>
						
						<input type="email" name="contact" class="form-control" placeholder="contact"/>

						<input type="text" name="Status" class="form-control" placeholder="Status"/>

						<input type="date" name="OpenDate" class="form-control" placeholder="Open Date"/>

						<input type="date" name="CloseDate" class="form-control" placeholder="Close Date"/>				
					
						<button name="newB" type="submit" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-ok" value="Create Bulletin"></span> Create 
						</button>

						</div>
					</div>
      </form>
      </div>

	<button onclick="editBulletin()" type="submit" class="btn btn-default btn-sm">
	<span class="glyphicon glyphicon-pencil" value="Edit"></span> Edit 
	</button>
         <div id="modal-wrapper-EditBulletin" class="modal">
      <form class="modal-content animate" name="newBulletin" id="newBulletin" class="newBulletin" action="Admin" method="post">
      		<div class="imgcontainer">
				<span onclick="document.getElementById('modal-wrapper-EditBulletin').style.display='none'" class="close" title="Close PopUp">&times;</span>
				<h1 style="text-align:center">Edit The Bulletin </h1>
			</div>
			<div class="container">
					<div class="modal-body">
					
						<input id="IDToEdit" type="text" name="IDToEdit" class="form-control" placeholder="ID"/>
						<select id="ProductToEdit" name="ProductToEdit">
						  <option value="UCMDB">UCMDB</option>
						  <option value="SIS">SIS</option>
						  <option value="NA">NA</option>
						  <option value="PC">PC</option>
						</select>
						<br>
						<br>
						<br>
						<br>
						<input id="LinkToEdit" type="text" name="LinkToEdit" class="form-control" placeholder="Link"/>

						<input id="CVEToEdit" type="text" name="CVEToEdit" class="form-control" placeholder="CVE"/>
						
						<input id="contactToEdit" type="email" name="contactToEdit" class="form-control" placeholder="contact"/>

						<input id="StatusToEdit" type="text" name="StatusToEdit" class="form-control" placeholder="Status"/>

						<input id="OpenDateToEdit" type="date" name="OpenDateToEdit" class="form-control" placeholder="Open Date"/>

						<input id="CloseDateToEdit" type="date" name="CloseDateToEdit" class="form-control" placeholder="Close Date"/>				
					
						<button name="editB" type="submit" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-ok" value="Create Bulletin"></span> Save 
					   	</button>
						

						</div>
					</div>
      </form>
      </div>
      </div>

<div id="logs">
	      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i><h3> Logs Table:</h3></div> 
          <div class="table-responsive">
            <table class="table table-striped table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th >ID</th>
                  <th>Date</th>
                  <th>Changed By</th>
                  <th>Change</th>
                </tr>
              </thead>
              <tbody>
<%
	for(Logs log:LogsList)
	{	
     	out.print("<tr >"+
                  "<td>"+log.getId()+"</td>"+
                  "<td>"+log.getDate()+"</td>"+
                  "<td>"+log.getChangedBy()+"</td>"+
                  "<td>"+log.getChange()+"</td>"+
               	  "</tr>");
	}
%>
				</tbody>
            </table>
          </div>
      </div>
      </div>
<jsp:include page="Footer.jsp"></jsp:include>
<script type="text/javascript">
var modal = document.getElementById('modal-wrapper-newUser');
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
    var modal = document.getElementById('modal-wrapper-newBulletin');
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
        var modal = document.getElementById('modal-wrapper-EditBulletin');
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
        var modal = document.getElementById('users');
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }            
        var modal = document.getElementById('bull');
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    var table = document.getElementById("bulletinTable"),brIndex,bcIndex;
    
    // table rows
    for(var i = 1; i < table.rows.length; i++)
    {
        // row cells
        for(var j = 0; j < table.rows[i].cells.length; j++)
        {
            table.rows[i].cells[j].onclick = function()
            {
            	brIndex = this.parentElement.rowIndex;
                bcIndex = this.cellIndex+1;
            };
        }
    }
    
    function hide(){
    	var x = document.getElementById("bull");
    	var y = document.getElementById("users");
    	var u = document.getElementById("pandding");
    	x.style.display ="none";
    	var z = document.getElementById("logs");
    	z.style.display ="none";
    	y.style.display = "none";
    	u.style.display = "none";
    }
    function HideShowUser() {
        var x = document.getElementById("users");
        if (x.style.display === "none") {
        	var y = document.getElementById("bull");
        	var z = document.getElementById("logs");
        	z.style.display ="none";
        	var u = document.getElementById("pandding");
        	u.style.display ="none";
        	y.style.display = "none";
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
    
    function HideShowPandding() {
        var x = document.getElementById("pandding");
        if (x.style.display === "none") {
        	var y = document.getElementById("bull");
        	var z = document.getElementById("logs");
        	var u = document.getElementById("users");
        	z.style.display ="none";
        	y.style.display = "none";
        	u.style.display = "none";
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
    
    function HideShowLogs() {
        var x = document.getElementById("logs");
        if (x.style.display === "none") {
        	var y = document.getElementById("bull");
        	var z = document.getElementById("users");
        	var u = document.getElementById("pandding");
        	u.style.display ="none";
        	z.style.display ="none";
        	y.style.display = "none";
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
    
    function HideShowBulletin() {
        var x = document.getElementById("bull");
        if (x.style.display === "none") {
        	var y = document.getElementById("users");
        	var z = document.getElementById("logs");
        	z.style.display ="none";
        	y.style.display = "none";
        	var u = document.getElementById("pandding");
        	u.style.display ="none";
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
    

 
    function editBulletin(){
    	 
    	document.getElementById('modal-wrapper-EditBulletin').style.display='block';
    	document.getElementById("IDToEdit").value=table.rows[brIndex].cells.item(0).innerHTML;
    	document.getElementById("ProductToEdit").value=table.rows[brIndex].cells.item(1).innerHTML;
    	document.getElementById("LinkToEdit").value=table.rows[brIndex].cells.item(3).innerHTML;
    	document.getElementById("CVEToEdit").value=table.rows[brIndex].cells.item(4).innerHTML;
    	document.getElementById("contactToEdit").value=table.rows[brIndex].cells.item(5).innerHTML;
    	document.getElementById("StatusToEdit").value=table.rows[brIndex].cells.item(6).innerHTML;
    	document.getElementById("OpenDateToEdit").value=table.rows[brIndex].cells.item(2).innerHTML;
    	document.getElementById("CloseDateToEdit").value=table.rows[brIndex].cells.item(7).innerHTML;

    }


</script>
</body>

</html>
