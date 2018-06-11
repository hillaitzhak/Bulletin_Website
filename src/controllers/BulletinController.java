package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bulletin;
import Repository.BulletinRepository;

public class BulletinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BulletinController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher Request_Dispatcher = null;
		
		//Making sure user is in session
		if(request.getSession().getAttribute("userID")==null){
			request.setAttribute("error", "Not in a session");
			Request_Dispatcher = request.getRequestDispatcher("/error.jsp");
			Request_Dispatcher.forward(request, response);
			return;
		}
		
		BulletinRepository br = new BulletinRepository();
		List<Bulletin> allbulletins = br.getAllBulletins();
		request.setAttribute("allbulletins", allbulletins);
		
		request.getRequestDispatcher("/BulletinList.jsp").forward(request, response);
	}
}
