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


