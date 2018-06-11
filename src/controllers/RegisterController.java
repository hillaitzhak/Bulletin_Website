package controllers;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Repository.PanddingRepository;
import model.Authenticator;
import model.User;
import utils.Xss;
import utils.validator;


@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class RegisterController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public RegisterController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Authenticator authenticator = new Authenticator();
		String id= request.getParameter("id");
		String first_name = request.getParameter("firstname");
		String last_name = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPassword = authenticator.toSha256(password);
		String email = request.getParameter("email");
		String bdate = request.getParameter("bdate");
		String gender = request.getParameter("gender");
		String telephone = request.getParameter("telephone");
		
		RequestDispatcher rd = null;
		
		//Input validation
		if(inputvalidation(id, first_name, last_name, username, email, bdate, gender, telephone) == false){
			request.setAttribute("error", "Invalid input");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
			}
				
		username = Xss.cleanString("username", username);
		id = Xss.cleanString("userId", id);
		email = Xss.cleanString("email", email);
		first_name = Xss.cleanString("firstName", first_name);
		last_name = Xss.cleanString("lastName", last_name);
		bdate = Xss.cleanString("bdate", bdate);
		telephone = Xss.cleanString("telephone", telephone);
		
		User newUser= new User(username,id,email,first_name,last_name,bdate,gender,telephone);
		PanddingRepository rep = new PanddingRepository();
		String result = rep.addUser(newUser,hashedPassword);
		if (!result.equals("success"))
			result = "error please try again";
		if (result.equals("success")) {
			doGet(request, response);
		} else {
			request.setAttribute("error", result);
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/registration.jsp");
		rd.forward(request, response);
	}
	
	//Input validation functions server side
	public boolean inputvalidation(String id ,String firstName,String lastName, String userName,String email,String bday, String gender, String telephone){
		
		if(!validator.validateUserId(id)) return false;
		
		if(!validator.validateFirstname(firstName)) return false;
		
		if(!validator.validateLastname(lastName)) return false;
		
		if(!validator.validateUsername(userName)) return false;
		
		if(!validator.validateEmail(email)) return false;
		
		if(!validator.validateBirthDate(bday)) return false;
		
		if(!validator.validateTelephone(telephone)) return false;
		
		//check gender field	
		if(!Objects.equals(gender, "Male")&&(!Objects.equals(gender, "Female"))) return false;
		
		return true;
	}
}
