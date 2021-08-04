package controllers;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import models.User;
import utils.EmailSender;
import utils.ActivationCode;
import utils.EmailMessages;

import utils.GoogleCaptcha;

public class SignupServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.getRequestDispatcher("signup.jsp").forward(request,response);		
	}	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{	
		boolean flag = true;
		String errorMessage = "";

		String captchaResponse = request.getParameter("g-recaptcha-response");
		
		
		flag = GoogleCaptcha.checkRecaptcha(captchaResponse);

		
		if(flag){
			String userName = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String rePassword = request.getParameter("repassword");

			//validation ###########################################
			

			Pattern p = null;
			Matcher m = null;			

			p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{4,29}$");
			m = p.matcher(userName);
			if(!m.matches()){
				flag = false;
				errorMessage += "<p>Only alphabet and numeric characters allowed...!</p>";
			}

			p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
			m = p.matcher(email);
			if(!m.matches()){
				flag = false;
				errorMessage += "<p>Invalid email!!</p>"; 
			}

			int passwordLength = password.length();
			if(passwordLength<6||passwordLength>12){
				flag = false;
				errorMessage += "<p>Password must be at least 6 and at max 12 characters length</p>"; 
			}

			if(!password.equals(rePassword)){
				flag = false;
				errorMessage += "<p>password and repassword must match!!</p>";
			}
			

			//Interact with the model class User ###################
			if(flag){
				String activationCode = ActivationCode.generateActivationCode();
				User user = new User(userName,email,password,activationCode);
				if(user.saveUser()){										
					String message = EmailMessages.getAccountActivationMail(userName,activationCode);
					String subject = "eCart Account Activation Mail";
					
					EmailSender.sendEmail(email,subject,message);
					
					String uploadsPath = getServletContext().getRealPath("/WEB-INF/uploads");
 					File file = new File(uploadsPath,userName);
					file.mkdir();
					
					response.sendRedirect("signin.jsp");	
				}else{
					flag = false;
					errorMessage += "Either duplicate username or duplicate email";
				}
			}
		}else{
			errorMessage += "Captcha Test Failed";
		}
		
		if(!flag){			
			request.setAttribute("error",errorMessage);
			request.getRequestDispatcher("signup.jsp").forward(request,response);		
		}
	}
}