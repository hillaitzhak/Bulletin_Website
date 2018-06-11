package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher rd = null;
		if (request.getSession().getAttribute("userID")!= null){
			request.getRequestDispatcher("/Home.jsp").forward(request, response);
		}
		else{
			request.setAttribute("error", "Not in a session");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}
}
