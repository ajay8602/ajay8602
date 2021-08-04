package controllers;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.util.ArrayList;

import models.Seller;
import models.User;


public class SellerPageServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		doGet(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");

		String nextPage = "signin.jsp";

		if(user!=null){
			ArrayList<Seller> sellers = Seller.collectSellerAccounts(user.getUserId());
			request.setAttribute("sellers",sellers);
			nextPage = "seller_page.jsp" ;
		}
		
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}