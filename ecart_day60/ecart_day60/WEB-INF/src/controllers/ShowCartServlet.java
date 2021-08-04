package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import java.util.*;

import models.Product;

public class ShowCartServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{		
		HttpSession session = request.getSession();

		Map<Integer,Integer> cart = (Map<Integer,Integer>)session.getAttribute("cart");
		
		if(cart!=null){
			Set<Integer> productIds = cart.keySet();

			ArrayList<Product> products = Product.getProductDetailsForCart(productIds);

			request.setAttribute("products",products);
		}
		
		request.getRequestDispatcher("cart.jsp").forward(request,response);
	}
}