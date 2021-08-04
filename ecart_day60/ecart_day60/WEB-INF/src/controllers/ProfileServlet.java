package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.Date;

import models.User;

public class ProfileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		
		String nextPage = "signin.jsp";
		if(user!=null)
			nextPage = "profile.jsp";
		

		request.getRequestDispatcher(nextPage).forward(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		
		if(user!=null){
			String otpClient = request.getParameter("otp");
			String otpSession = (String)session.getAttribute("otp");
			otpSession = "123456";
			if(otpSession.equals(otpClient)){
				String firstName = request.getParameter("firstname");
				String middleName = request.getParameter("middlename");
				String lastName = request.getParameter("lastname");
				String dob = request.getParameter("dob");
				String mobile = request.getParameter("mobile");
				
				user.setFirstName(firstName);
				user.setMiddleName(middleName);
				user.setLastName(lastName);
				user.setDob(Date.valueOf(dob));
				user.setMobile(mobile);

				if(user.saveProfile()){
					response.sendRedirect("address.do");
				}else{
					request.getRequestDispatcher("profile.jsp").forward(request,response);
				}	
			}else{
				
			}
		}else{
			response.sendRedirect("signin.jsp");
		}
	}
}