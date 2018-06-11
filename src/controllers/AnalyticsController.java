package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bulletin;
import Repository.BulletinRepository;
import utils.OpenCloseChart;

public class AnalyticsController extends HttpServlet {
		private static final long serialVersionUID = 1L;

		public AnalyticsController() {
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
			
			BulletinRepository Bulletin_Repository = new BulletinRepository();
			List<Bulletin> allbulletins = Bulletin_Repository.getAllBulletins();
			List<OpenCloseChart> OpenCloseChart = new ArrayList<>();
			int openNA=0;
			int closeNA=0;
			int openUCMDB=0;
			int closeUCMDB=0;
			int openSIS=0;
			int closeSIS=0;
			int openPC=0;
			int closePC=0;
	
			for(Bulletin bulletin:allbulletins){
				if (bulletin.getProduct().compareTo("PC")==0){
					if (bulletin.getStatus().compareTo("open")==0){
						openPC++;
					}
					if(bulletin.getStatus().compareTo("close")==0){
						closePC++;
					}
				}
				if (bulletin.getProduct().compareTo("NA")==0){
					if (bulletin.getStatus().compareTo("open")==0){
						openNA++;
					}
					if(bulletin.getStatus().compareTo("close")==0){
						closeNA++;
					}
				}

				if (bulletin.getProduct().compareTo("UCMDB")==0){
					if (bulletin.getStatus().compareTo("open")==0){
						openUCMDB++;
					}
					if(bulletin.getStatus().compareTo("close")==0){
						closeUCMDB++;
					}
				}
				if (bulletin.getProduct().compareTo("SIS")==0){
					if (bulletin.getStatus().compareTo("open")==0){
						openSIS++;
					}
					if(bulletin.getStatus().compareTo("close")==0){
						closeSIS++;
					}
				}
			}
			OpenCloseChart op = new OpenCloseChart("NA", openNA, closeNA);
			OpenCloseChart.add(op);
			op = new OpenCloseChart("UCMDB", openUCMDB, closeUCMDB);
			OpenCloseChart.add(op);
			op = new OpenCloseChart("SIS", openSIS, closeSIS);
			OpenCloseChart.add(op);
			op = new OpenCloseChart("PC", openPC, closePC);
			OpenCloseChart.add(op);
			request.setAttribute("op", OpenCloseChart);
			request.setAttribute("allbulletins", allbulletins);
			
			request.getRequestDispatcher("/Analytics.jsp").forward(request, response);
		}
}

