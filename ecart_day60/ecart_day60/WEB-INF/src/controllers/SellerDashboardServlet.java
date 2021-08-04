package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.User;
import models.Seller;

public class SellerDashboardServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String nextPage = "signin.jsp";

		if(user!=null){
			int sellerId = Integer.parseInt(request.getParameter("seller_id"));
			String sellerName = request.getParameter("seller");
			Seller seller = new Seller(sellerId,sellerName);
			
			session.setAttribute("seller",seller);

			//collect seller account activities------
			
			nextPage = "seller_dashboard.jsp";
		}
		
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}