package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.ArrayList;

import models.User;
import models.Product;

import com.google.gson.Gson;

public class AllProductsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = "expired";

		if(user!=null){
			int sellerId = Integer.parseInt(request.getParameter("seller_id"));

			ArrayList<Product> list = Product.collectAllProducts(sellerId);

			Gson gson = new Gson();
			resp = gson.toJson(list);			
		}

		response.getWriter().write(resp);
	}
}