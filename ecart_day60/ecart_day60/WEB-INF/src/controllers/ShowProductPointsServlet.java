package controllers;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.util.ArrayList;

import models.ProductPoint;

import com.google.gson.Gson;

public class ShowProductPointsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		Integer productId = Integer.parseInt(request.getParameter("product_id"));

		ArrayList<ProductPoint> productPoints = ProductPoint.collectAllProductPoints(productId);

		Gson gson = new Gson();
		String resp = gson.toJson(productPoints);

		response.getWriter().write(resp);
	}
}