package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.User;

import utils.*;

public class SendOTPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		if(user!=null){
			String mobile = request.getParameter("mobile");
			String otp = OTPGenerator.generateOTP();
			String message = "Your one time otp: "+otp;
			//TextLocal.sendSms(mobile,message);
			session.setAttribute("otp",otp);
		}else{
			response.getWriter().write("expired");
		}
	}
}
