package controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Repository.PasswordRepository;
import Repository.UserRepository;
import model.Authenticator;
import model.Password;
import model.User;

public class PasswordUpdateController extends HttpServlet {
		 
		private static final long serialVersionUID = 407056058428113610L;

		public PasswordUpdateController() {
			super();
		}
		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			Authenticator authenticator = new Authenticator();
			
			RequestDispatcher rd = null;
			
			UserRepository ur = new UserRepository();
			
			
			String id = request.getParameter("id");
			
			User creator = ur.getUserById(id);

			
			String passwordOld = request.getParameter("pwdOld");
			String passwordNew = request.getParameter("pwdNew");
			
			//Making sure only the owner user is trying to change the password
			if (!creator.getId().equals(id))
			{
				request.setAttribute("error", "Please Enter Only Your ID");
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
				return;
			}
			
			//Making sure old password is different then the new password 
			if (passwordNew.equals(passwordOld))
			{
				request.setAttribute("error", "Please Change Your Password");
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
				return;
			}
			else
			{
				String hashedPassword = authenticator.toSha256(passwordNew);
				String result = ur.editPass(creator, hashedPassword);
				if(!result.equals("success"))
				{
					request.setAttribute("error", "Something went worng please contact your administrator");
					rd = request.getRequestDispatcher("/error.jsp");
					rd.forward(request, response);
					return;
				}
				LocalDate localDate = LocalDate.now();
				int day = localDate.getDayOfMonth();
				int month = localDate.getMonthValue();
				int year = localDate.getYear();
				Password newPassword = new Password(creator.getId(),year,day,month);
				PasswordRepository passR = new PasswordRepository();
				passR.editPassword(newPassword);
				rd = request.getRequestDispatcher("/Home.jsp");
				rd.forward(request, response);
			}

		}
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("/PassUpdate.jsp");
			rd.forward(request, response);
		}
	 
}
