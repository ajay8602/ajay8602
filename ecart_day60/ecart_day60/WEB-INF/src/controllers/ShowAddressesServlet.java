package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.User;
import models.Address;
import models.City;

import java.util.ArrayList;

public class ShowAddressesServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String nextPage = "signin.jsp";
		if(user!=null){
			ArrayList<Address> addresses = Address.getAllAddresses(user.getUserId()); 
			request.setAttribute("addresses",addresses);
			nextPage = "all_addresses.jsp";
		}

		request.getRequestDispatcher(nextPage).forward(request,response);
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		
		if(user!=null){		
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			int cityId = Integer.parseInt(request.getParameter("city_id"));
			String pin = request.getParameter("pin");

			Address addr = new Address(user,address,new City(cityId),pin,name);
			addr.saveAddress();
		}
		
		response.getWriter().write("{\"done\":1}");
	}
}