package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.User;

public class AccountActivationServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String userName = request.getParameter("ukey");
		String activationCode = request.getParameter("actCode");
		System.out.println("+++++++++++++++");

		if(User.activateAccount(userName,activationCode)){
			request.setAttribute("success","Account Activated Successfully...");
		}else{
			request.setAttribute("fail","Account Activativation Failed...");
		}

		request.getRequestDispatcher("result.jsp").forward(request,response);
	}
}