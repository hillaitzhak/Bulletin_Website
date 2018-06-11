package controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Authenticator;
import model.Bulletin;
import model.User;
import Repository.BulletinRepository;
import Repository.UserRepository;
import utils.Xss;

public class CustomListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomListController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		Authenticator authenticator = new Authenticator();
		
		UserRepository ur = new UserRepository();
		RequestDispatcher rd = null;
		User creator = ur.getUserById((String) request.getSession().getAttribute("userID"));
		BulletinRepository br = new BulletinRepository();
		
		//Making sure user is in session
		if (creator == null) {
			request.setAttribute("error", "Not in a session");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		} else {
			//Collecting requested information from the user
			List<Bulletin> allbulletin = br.getAllBulletins();
			String product = request.getParameter("ProductSelcted");
			String CVE = request.getParameter("CVESelcted");
			String contact = request.getParameter("contactSelcted");
			String status = request.getParameter("statusSelcted");
			
			if (product.compareTo("1")!=0){
				for(Bulletin bulletin:allbulletin){
					if (bulletin.getProduct().compareTo(product)!=0){
						allbulletin.remove(bulletin);
					}
				}
			}
			
			if (!CVE.equals("1")){
				for(Bulletin bulletin:allbulletin){
					if (bulletin.getCVE().compareTo(CVE)!=0){
						allbulletin.remove(bulletin);
					}
				}
			}
			
			if (!contact.equals("1")){
				for(Bulletin bulletin:allbulletin){
					String c = Xss.cleanString("contact", contact);
					if (bulletin.getContact().compareTo(c)!=0){
						allbulletin.remove(bulletin);
					}
				}
			}
			
			if (!status.equals("1")){
				for(Bulletin bulletin:allbulletin){
					if (bulletin.getStatus().compareTo(status)!=0){
						allbulletin.remove(bulletin);
					}
				}
			}
			if(allbulletin==null){
				allbulletin = new ArrayList<Bulletin>();
			}
			request.setAttribute("allbulletins", allbulletin);
			request.getRequestDispatcher("/CustomList.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		//Making sure user is in session
		if(request.getSession().getAttribute("userID")==null){
			request.setAttribute("error", "Not in a session");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
		
		BulletinRepository br = new BulletinRepository();
		List<Bulletin> allbulletins = br.getAllBulletins();
		request.setAttribute("allbulletins", allbulletins);
		
		request.getRequestDispatcher("/CustomList.jsp").forward(request, response);
	}
}
