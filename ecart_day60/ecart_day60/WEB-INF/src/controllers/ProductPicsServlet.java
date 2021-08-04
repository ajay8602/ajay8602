package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.ProductPic;
import java.util.ArrayList;

import com.google.gson.Gson;

public class ProductPicsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		Integer productId = Integer.parseInt(request.getParameter("product_id"));

		ArrayList<ProductPic> productPics = ProductPic.getAllProductPics(productId);

		Gson gson = new Gson();
		String resp = gson.toJson(productPics);

		response.getWriter().write(resp);
	}
}
