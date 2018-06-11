package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Repository.LogRepository;
import Repository.PanddingRepository;
import Repository.PasswordRepository;
import Repository.UserRepository;
import model.User;
import utils.Xss;
import utils.validator;
import model.Authenticator;
import model.Bulletin;
import Repository.BulletinRepository;
import model.Logs;
import model.Password;
import utils.Email;

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminController() {
		super();
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		
		Authenticator authenticator = new Authenticator();
		
		RequestDispatcher Request_Dispatcher = null;
		
		LogRepository Log_Repository = new LogRepository();		
		BulletinRepository Bulletin_Repository = new BulletinRepository();
		PanddingRepository Pandding_Repository = new PanddingRepository();
		UserRepository User_Repository = new UserRepository();

		User creator = User_Repository.getUserById((String) request.getSession().getAttribute("userID"));
		
		//Making sure the user is admin if not return error
		if (creator.isAdmin() == false) {
			request.setAttribute("error", "Not permmision");
			Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
			Request_Dispatcher.forward(request, response);
			return;
		}
		
		//Making sure user is in session if not error
		if (creator == null) {
			request.setAttribute("error", "Not in a session");
			Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
			Request_Dispatcher.forward(request, response);
			return;
		} else {
			//If the user is admin and in session do
			String uIdToDelete = request.getParameter("hiddenu");
			String bIdToDelete = request.getParameter("hiddenb");
			String uToAdmin = request.getParameter("hiddenAdmin");
			String newBulletin = request.getParameter("newB");
			String newUser = request.getParameter("newUser");
			String editBulletin = request.getParameter("editB");
			String newUserApprove = request.getParameter("hiddenApprove");
			String userToDelete = request.getParameter("hiddenut");
			
			String result = null;
			
			if (editBulletin != null) {
				
				//If the admin requested to change bulletin
				
				String IDToEdit = request.getParameter("IDToEdit");
				String ProductToEdit = request.getParameter("ProductToEdit");
				String LinkToEdit = request.getParameter("LinkToEdit");
				String CVEToEdit = request.getParameter("CVEToEdit");
				String contactToEdit = request.getParameter("contactToEdit");
				String StatusToEdit = request.getParameter("StatusToEdit");
				String OpenDateToEdit = request.getParameter("OpenDateToEdit");
				String CloseDateToEdit = request.getParameter("CloseDateToEdit");
				
				if(BulletinInputvalidation(ProductToEdit, LinkToEdit, CVEToEdit, contactToEdit, StatusToEdit, OpenDateToEdit, CloseDateToEdit) == false){
				
					//Input validation server side
					if(!validator.validateURL(LinkToEdit)) {
						request.setAttribute("error", "Invalid url");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateText(ProductToEdit)) {
						request.setAttribute("error", "Invalid product");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateUsername(CVEToEdit)) {
						request.setAttribute("error", "Invalid cve");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateEmail(contactToEdit)) {
						request.setAttribute("error", "Invalid contact");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateBirthDate(OpenDateToEdit)) {
						request.setAttribute("error", "Invalid open");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateBirthDate(CloseDateToEdit)) {
						request.setAttribute("error", "Invalid close");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateText(StatusToEdit)) {
						request.setAttribute("error", "Invalid input");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					request.setAttribute("error", "Invalid input");
					Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
					Request_Dispatcher.forward(request, response);
					
					return;
					
					}
						
				ProductToEdit = Xss.cleanString("ProductToEdit", ProductToEdit);
				LinkToEdit = Xss.cleanString("LinkToEdit", LinkToEdit);
				CVEToEdit = Xss.cleanString("CVEToEdit", CVEToEdit);
				contactToEdit = Xss.cleanString("contactToEdit", contactToEdit);
				StatusToEdit = Xss.cleanString("StatusToEdit", StatusToEdit);
				OpenDateToEdit = Xss.cleanString("OpenDateToEdit", OpenDateToEdit);
				CloseDateToEdit = Xss.cleanString("CloseDateToEdit", CloseDateToEdit);

				Bulletin Bulletin =new Bulletin(ProductToEdit,OpenDateToEdit,CloseDateToEdit,LinkToEdit, CVEToEdit,contactToEdit,StatusToEdit);
				Bulletin.setId(Integer.parseInt(IDToEdit));
				result = Bulletin_Repository.editB(Bulletin);
				
				//Saving changes in log table
				if (result.equals("success")){
					LocalDate localDate = LocalDate.now();
					String change = "Bulletin Number: "+IDToEdit+" Was Edited";
					String by = (String) request.getSession().getAttribute("userID");
					String date = localDate.toString();
					Logs newLog = new Logs (by,date,change);
					Log_Repository.addLog(newLog);
				}
			}
			
			if (newUser != null) {
				
				//Adding new user
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
				
				//Input validation
				if(inputvalidation(id, first_name, last_name, username, email, bdate, gender, telephone) == false){
					request.setAttribute("error", "Invalid input");
					Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
					Request_Dispatcher.forward(request, response);
					return;
					}
						
				username = Xss.cleanString("username", username);
				id = Xss.cleanString("userId", id);
				email = Xss.cleanString("email", email);
				first_name = Xss.cleanString("firstName", first_name);
				last_name = Xss.cleanString("lastName", last_name);
				bdate = Xss.cleanString("bdate", bdate);
				telephone = Xss.cleanString("telephone", telephone);
				
				User User= new User(username,id,email,first_name,last_name,bdate,gender,telephone);
				UserRepository rep = new UserRepository();
				result = rep.addUser(User,hashedPassword);
				
				//Saving changes in the log table
				if (result.equals("success")){
					LocalDate localDate = LocalDate.now();
					String change = "New User: "+id+" Was Created";
					String by = (String) request.getSession().getAttribute("userID");
					String date = localDate.toString();
					Logs newLog = new Logs (by,date,change);
					Log_Repository.addLog(newLog);
				}
			}
			
			//Replacing user admin status
			if (uToAdmin != null) {
				User userToAdmin = User_Repository.getUserById(Xss.cleanString("uToAdmin", uToAdmin));
				if(userToAdmin.isAdmin()){
					userToAdmin.setAdmin(false);
					result = User_Repository.editUser(userToAdmin);
				}else{
					userToAdmin.setAdmin(true);
					result = User_Repository.editUser(userToAdmin);
					//Saving changes to log table
					if (result.equals("success")){
						LocalDate localDate = LocalDate.now();
						String change = "User Number:"+userToAdmin+" To Admin";
						String by = (String) request.getSession().getAttribute("userID");
						String date = localDate.toString();
						Logs newLog = new Logs (by,date,change);
						Log_Repository.addLog(newLog);
					}
				}
				
			}
			
			//Deleting user
			if (uIdToDelete != null) {
				User UserToDelete = User_Repository.getUserById(Xss.cleanString("uIdToDelete", uIdToDelete));
				result = User_Repository.deleteUser(UserToDelete);
				//Saving changes to log table
				if (result.equals("success")){
					LocalDate localDate = LocalDate.now();
					String change = "User Number: "+UserToDelete+" Was Deleted";
					String by = (String) request.getSession().getAttribute("userID");
					String date = localDate.toString();
					Logs newLog = new Logs (by,date,change);
					Log_Repository.addLog(newLog);
				}
			}
			
			//Deleting user from pending table
			if (userToDelete != null) {
				User User = Pandding_Repository.getUserById(Xss.cleanString("userToDelete", userToDelete));
				result = Pandding_Repository.deleteUser(User);
				//Saving changes in the log table
				if (result.equals("success")){
					LocalDate localDate = LocalDate.now();
					String change = "User Number: "+User.getId()+" Was Deleted from pandding";
					String by = (String) request.getSession().getAttribute("userID");
					String date = localDate.toString();
					Logs newLog = new Logs (by,date,change);
					Log_Repository.addLog(newLog);
				}
			}
			
			//New user to save
			if (newUserApprove != null) {
				User User = Pandding_Repository.getUserById(Xss.cleanString("newUserApprove", newUserApprove));
				result = User_Repository.addUser(User, Pandding_Repository.getPass(newUserApprove));
				result = Pandding_Repository.deleteUser(User);
				//Saving changes to the log table
				if (result.equals("success")){
					LocalDate localDate = LocalDate.now();
					int day = localDate.getDayOfMonth();
					int month = localDate.getMonthValue();
					int year = localDate.getYear();
					String change = "User Number: "+User.getId()+" Was created from pandding";
					String by = (String) request.getSession().getAttribute("userID");
					String date = localDate.toString();
					Logs newLog = new Logs (by,date,change);
					Password newPassword = new Password(newUserApprove,year,day,month);
					PasswordRepository passR = new PasswordRepository();
					passR.addPassword(newPassword);
					Log_Repository.addLog(newLog);
					//Sending approval email
					String reciver = request.getParameter("hiddenEmail");
					Email send = new Email(reciver,"Your Account Is Approved","Hello Dear"+User.getFirstName()+","+"\n your new bulletin account is now approved");
				}
			}
			
			//Deleting bulletin
			if (bIdToDelete != null) {
				int id = Integer.parseInt(bIdToDelete);
				Bulletin bulletinToDelete = Bulletin_Repository.getBulletin(id);
				result = Bulletin_Repository.DeleteB(id);
				//Saving changes to the log table
				if (result.equals("success")){
					LocalDate localDate = LocalDate.now();
					String change = "Bulletin Number:"+bIdToDelete+"Was Deleted";
					String by = (String) request.getSession().getAttribute("userID");
					String date = localDate.toString();
					Logs newLog = new Logs (by,date,change);
					Log_Repository.addLog(newLog);
				}
			}
			
			//Creating new bulletin
			if (newBulletin != null) {
				String Product = request.getParameter("Product");
				String Link = request.getParameter("Link");
				String CVE = request.getParameter("CVE");
				String contact = request.getParameter("contact");
				String Status = request.getParameter("Status");
				String OpenDate = request.getParameter("OpenDate");
				String CloseDate = request.getParameter("CloseDate");
				
				//Input validation
				if(BulletinInputvalidation(Product, Link, CVE, contact, Status, OpenDate, CloseDate) == false){
					if(!validator.validateURL(Link)) {
						request.setAttribute("error", "Invalid url");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateText(Product)) {
						request.setAttribute("error", "Invalid product");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateUsername(CVE)) {
						request.setAttribute("error", "Invalid cve");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateEmail(contact)) {
						request.setAttribute("error", "Invalid contact");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateBirthDate(OpenDate)) {
						request.setAttribute("error", "Invalid open");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateBirthDate(CloseDate)) {
						request.setAttribute("error", "Invalid close");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					
					if(!validator.validateText(Status)) {
						request.setAttribute("error", "Invalid input");
						Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
						Request_Dispatcher.forward(request, response);
						
						return;
					}
					request.setAttribute("error", "Invalid input");
					Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
					Request_Dispatcher.forward(request, response);
					
					return;
					
					}
						
				Product = Xss.cleanString("Product", Product);
				Link = Xss.cleanString("Link", Link);
				CVE = Xss.cleanString("CVE", CVE);
				contact = Xss.cleanString("contact", contact);
				Status = Xss.cleanString("Status", Status);
				OpenDate = Xss.cleanString("OpenDate", OpenDate);
				CloseDate = Xss.cleanString("CloseDate", CloseDate);

				Bulletin Bulletin =new Bulletin(Product,OpenDate,CloseDate,Link, CVE,contact,Status);
				result = Bulletin_Repository.addBulletin(Bulletin);
				//Saving changes to the log table
				if (result.equals("success")){
					LocalDate localDate = LocalDate.now();
					String change = "Bulletin Number: "+Bulletin.getId()+ "Was Created";
					String by = (String) request.getSession().getAttribute("userID");
					String date = localDate.toString();
					Logs newLog = new Logs (by,date,change);
					Log_Repository.addLog(newLog);
				}
			}
			
			if (result.equals(null))
				result = "error";
			if (result.equals("success")) {
				doGet(request, response);
			} else {
				request.setAttribute("error", result);
				Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
				Request_Dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher Request_Dispatcher = null;
		BulletinRepository Bulletin_Repository = new BulletinRepository();
		UserRepository User_Repository = new UserRepository();
		PanddingRepository Pandding_Repository = new PanddingRepository();
		LogRepository Log_Repository = new LogRepository();

		User creator = User_Repository.getUserById((String) request.getSession().getAttribute("userID"));
		//Making sure user in session if not error
		if (creator == null) {
			request.setAttribute("error", "Not in a session");
			Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
			Request_Dispatcher.forward(request, response);
			return;
		}
		
		//Making sure user is admin if not error
		if (creator.isAdmin() == false) {
			request.setAttribute("error", "Not permmision");
			Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
			Request_Dispatcher.forward(request, response);
			return;
		}

		List<Bulletin> allbulletin = Bulletin_Repository.getAllBulletins();
		List<User> allusers = User_Repository.getAllUsers();
		List<User> allpandding = Pandding_Repository.getAllUsers();
		List<Logs> allLogs = Log_Repository.getAllLogs();
		request.setAttribute("allbulletins", allbulletin);
		request.setAttribute("allusers", allusers);
		request.setAttribute("alllogs", allLogs);
		request.setAttribute("allpandding", allpandding);
		request.getRequestDispatcher("/Admin.jsp").forward(request, response);
	}
	
//Input validation functions	
public boolean BulletinInputvalidation(String Product, String Link,String CVE,String contact,String Status,String OpenDate,String CloseDate){
		
		if(!validator.validateURL(Link)) return false;
		
		if(!validator.validateText(Product)) return false;
		
		if(!validator.validateUsername(CVE)) return false;
		
		if(!validator.validateEmail(contact)) return false;
		
		if(!validator.validateBirthDate(OpenDate)) return false;
		
		if(!validator.validateBirthDate(CloseDate)) return false;
		
		if(!validator.validateText(Status)) return false;
		
		return true;
	}
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
