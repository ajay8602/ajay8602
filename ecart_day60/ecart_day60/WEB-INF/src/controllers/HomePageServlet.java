package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.User;

public class HomePageServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String nextPage = "signin.jsp";
		if(user!=null){
			nextPage = "index.jsp";
		}

		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}
