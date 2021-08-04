package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.User;
import models.Seller;

public class NewSellerServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");		

		String nextPage = "signin.jsp";

		if(user!=null){
			String accountName = request.getParameter("account_name");

			Seller seller = new Seller(user,accountName);
			seller.createSellerAccount();

			String userUploadPath = getServletContext().getRealPath("/WEB-INF/uploads/"+user.getUserName());
			File file = new File(userUploadPath,accountName);
			file.mkdir();

			nextPage = "seller_page.do";	
		}
		
		request.getRequestDispatcher(nextPage).forward(request,response);
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");		

		String nextPage = "signin.jsp";

		if(user!=null){
			nextPage = "new_seller.jsp";	
		}
		
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}