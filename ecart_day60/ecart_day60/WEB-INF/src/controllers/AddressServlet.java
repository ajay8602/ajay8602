package controllers;


import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.*;

public class AddressServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");

		String nextPage = "signin.jsp";
		if(user!=null){
			Address address = new Address(user);
			address.fetchAddress();

			request.setAttribute("address",address);

			nextPage = "address.jsp";			
		}
		
		request.getRequestDispatcher(nextPage).forward(request,response);
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{	

		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		if(user!=null){
			String address = request.getParameter("address");
			int cityId = Integer.parseInt(request.getParameter("city_id")); 
			String ct = request.getParameter("city");
			City city = new City(cityId,ct);
			String pin = request.getParameter("pin");

			Address addr = new Address(user,address,city,pin);
			if(addr.saveAddress()){
				response.sendRedirect("profpic.do");
			}else{
				request.getRequestDispatcher("address.jsp").forward(request,response);
			}			
		}else{
			request.getRequestDispatcher("signin.jsp").forward(request,response);
		}		
	}
}