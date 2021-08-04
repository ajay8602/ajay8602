package listeners;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

import utils.EmailSender;
import utils.GoogleCaptcha;
import utils.EmailSender.MailPasswordAuthenticator;

import models.Category;

import java.util.ArrayList;

public class AppListener implements ServletContextListener{
	public void contextInitialized(ServletContextEvent ev){
		ServletContext context = ev.getServletContext();

		EmailSender.MailPasswordAuthenticator.senderEmail 
			= context.getInitParameter("sender_email");
		EmailSender.MailPasswordAuthenticator.senderEmailPassword
			= context.getInitParameter("sender_email_password");
		
		GoogleCaptcha.SECRET_KEY = context.getInitParameter("secret_key");
		
		ArrayList<Category> categories = Category.collectCategories();
		context.setAttribute("categories",categories);

		ArrayList<Integer> x = new ArrayList<Integer>();
		for(int i=1;i<=10;i++){
			x.add(i);
		}
		context.setAttribute("item_count",x);
	}

	public void contextDestroyed(ServletContextEvent ev){
	
	}
}