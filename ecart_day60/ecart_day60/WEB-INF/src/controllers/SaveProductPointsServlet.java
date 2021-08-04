package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.User;
import models.Product;
import models.ProductPoint;

public class SaveProductPointsServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = null;
		if(user!=null){
			String[] pointTitels = request.getParameterValues("p_ttl");
			String[] pointValues = request.getParameterValues("p_val");

			Product product = (Product)session.getAttribute("product");

			ProductPoint.saveProductPoints(product.getProductId(),pointTitels,pointValues);
			
			resp = "{\"resp\":1}";

		}else{
			resp = "{\"resp\":-1}";
		}

		response.getWriter().write(resp);
	}
}