package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.Product;

public class ViewProductServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		Integer productId = Integer.parseInt(request.getParameter("product_id"));

		Product product = new Product(productId);
		product.getProductInfo();

		request.setAttribute("product",product);

		request.getRequestDispatcher("product.jsp").forward(request,response);
	}
}