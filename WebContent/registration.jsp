<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Reg.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js\sc.js" ></script>
<title>Registration</title>
</head>
<body>
<jsp:include page="FirstHeader.jsp"></jsp:include>


	<form name="registrationForm" action="RegisterController" method="post" enctype="multipart/form-data" onsubmit="return validateRegistrationForm()">
		<div class="registration">
				<div class="formFields">
					<div class="modal-header">
						<h1 class="text-center">Create new account</h1>
					</div>
						<div class="modal-body">
			
							<div class="form-group">
								<label for="id" class="label">ID</label>
								<input type="text" name="id" class="form-control" placeholder="Your ID"/>
							</div>
							
							<div class="form-group">
								<label for="firstName" class="label">First Name</label>
								<input type="text" name="firstname" class="form-control" placeholder="First name"/>
							</div>
							
							<div class="form-group">
								<label for="lastname" class="label">Last Name</label>
								<input type="text" name="lastname" class="form-control" placeholder="Last name"/>
							</div>
							
							<div class="form-group">
								<label for="email" class="label">Email</label>
								<input type="email" name="email" class="form-control" placeholder="Your Email"/>
							</div>
							
							<div class="form-group">
								<label for="username" class="label">Username</label>
								<input type="text" name="username" class="form-control" placeholder="Username"/>
							</div>
							
							<div class="form-group">
								<label for="password" class="label">Password</label>
								<input type="password" name="password" class="form-control" placeholder="Password"/>
							</div>
							
							<div class="form-group">
							<label for="b-date" class="label">Date of Birth</label>
								<input type="date" name="bdate" class="form-control" placeholder="Date of Birth"/>
							</div>
							
							<div class="form-group">
							<label for="telephone" class="label">Telephone Number</label>
								<input type="text" name="telephone" class="form-control" placeholder="Telephone Number"/>
							</div>
							
							<div class="form-group">
								 <label for="gender" class="label">Gender</label><br>
							 	 <input type="radio" name="gender" value="Male" checked>Male<br>
  								 <input type="radio" name="gender" value="Female">Female
  							</div>  							
							<div class="form-group">
								<input type="submit" class="btn btn-block btn-lg btn-primary" onclick="validateRegistrationForm()" value="Create account"/>
							</div>
						</div>
				</div>
		</div>

	</form>

<jsp:include page="Footer.jsp"></jsp:include>

</body>

</html>

<!--  <script type="text/javascript">

function validateRegistrationForm(){
	//validate the ID number
	var id = document.registrationForm.id.value;
	if(id == null || id==""){
	    alert("You must fill your ID number");
	    return false;
	}else
	    if(id.length<9){
	        alert ("Please fill in correct ID number with 9 figuers");
	        return false;
	    }else
	    if(isNaN(id))
	        {
	            alert("Please fill only numbers in the ID section");
	            return false;
	        }
	//validate first name
	var name = document.registrationForm.firstname.value;
	var onlyletters = /^[a-zA-Z]{2,20}$/;   
	if (name == null || name=="")
	    {
	        alert("Please fill your first name");
	        return false;
	    }
	else
	    if(!name.match(onlyletters)){
	            alert("Please enter your name with only 2-20 letters");
	            return false;
	        }
	//validate last name
	var lastname = document.registrationForm.lastname.value;
	if (lastname == null || lastname=="")
	    {
	        alert("Please fill your last name");
	        return false;
	    }
	else
	    if(!lastname.match(onlyletters)){
	            alert("Please enter your last name with only 2-20 letters");
	            return false;
	        }
	//validate user name
	var letterNumber = /^[0-9a-zA-Z]{2,12}$/; 
	var username = document.registrationForm.username.value;
	if (username == null || username=="")
	    {
	        alert("Please fill user name");
	        return false;
	    }
	else
	    if(!username.match(letterNumber)){
	            alert("Please enter your user name with only 2-12 letters and numbers");
	            return false;
	        }
	var passreg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,16}$/;
	var password = document.registrationForm.password.value;
	if (password == null || password=="")
	    {
	        alert("Please fill password");
	        return false;
	    }
	else
	    if(!password.match(passreg)){
	            alert("Please enter your password  with 8-16 charachters, at least one sign and at least one capital letter");
	            return false;
	        }
	var bdayreg = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/; 
	var bdate = document.registrationForm.bdate.value;
	if (bdate == null || bdate=="")
	    {
	        alert("Please fill your Birth Date");
	        return false;
	    }
	else
	    if(!bdate.match(bdayreg)){
	            alert("Please enter your Birth Date in format dd/mm/yyyy");
	            return false;
	        }
	var phoneReg = /^\d{10}$/;
	var phone = document.registrationForm.telephone.value;
	if (phone == null || phone=="")
	{
	    alert("Please fill the phone number");
	    return false;
	}
	else
	if(!phone.match(phoneReg)){
	        alert("Please enter your phone number with only 10 numbers");
	        return false;
	    }
	return true;
	}


</script>-->