package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Repository.PasswordRepository;
import Repository.UserRepository;
import database.Database;
import model.Authenticator;
import model.User;
import utils.Xss;
import utils.DateChecker;
import utils.LockUser;
  
public class LoginController extends HttpServlet {
 
	private static final long serialVersionUID = 407056058428113610L;

	public LoginController() {
		super();
	}
	
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Function that handles the POST request that comes from login.jsp page.
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	*/
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		RequestDispatcher rd = null;
		try {
			Database.getInstance().init();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//Making sure user is valid
		Authenticator authenticator = new Authenticator();
		String result = authenticator.authenticate(Xss.cleanString("username", username), password);
		
		if (result.equals("success")) 
		{
			PasswordRepository pr = new PasswordRepository();
			UserRepository ur = new UserRepository();
			User user = ur.getUserByUsername(username);
			model.Password pass = pr.getPasswordById(user.getId());
			//Making sure 3 months don't pass
			String check = DateChecker.Check(pass.getYear(),pass.getDay(),pass.getMonth());
			
			//Opening session 
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userID", user.getId());
			session.setAttribute("firstname", user.getFirstName());
			session.setAttribute("lastname", user.getLastName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("bdate", user.getBday());
			session.setAttribute("gender", user.getGender());
			session.setAttribute("telephone", user.getTelephone());
			session.setAttribute("isAdmin",user.isAdmin());
			
			//same origin policy
			response.setHeader("Set-Cookie", "JSESSIONID=" + session.getId() + "; HttpOnly; SameSite=strict");
			
			if(check.equals("good"))
			{				
				rd = request.getRequestDispatcher("/Home.jsp");
			}
			else {
				rd = request.getRequestDispatcher("/PassUpdate.jsp");
			}
		} 
		else
		{
			LockUser userFailed = LockUser.getInstance();
			userFailed.insertFailedLogin (request.getRemoteAddr());
			if (userFailed.isHostBlocked(request.getRemoteAddr())){
				request.setAttribute("error", "your user is blocked");
			}else{
				request.setAttribute("error", "wrong username or password");
			}
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}
 
}