package controllers;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.util.ArrayList;

import models.Product;

import com.google.gson.Gson;

public class SearchServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String searchKeyword = request.getParameter("search");

		ArrayList<Product> products = Product.searchProduct(searchKeyword);

		Gson gson = new Gson();
		String prods = gson.toJson(products);

		response.getWriter().write(prods);
	}
}