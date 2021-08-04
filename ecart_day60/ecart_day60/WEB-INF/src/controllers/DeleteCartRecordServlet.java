package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import java.util.Map;

public class DeleteCartRecordServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		
		int productId = Integer.parseInt(request.getParameter("product_id"));

		Map<Integer,Integer> cart = (Map<Integer,Integer>)session.getAttribute("cart");

		cart.remove(productId);

		response.getWriter().write("{\"done\":\"true\"}");
	}
}