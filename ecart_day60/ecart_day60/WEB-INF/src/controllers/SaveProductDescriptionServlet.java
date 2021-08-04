package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.User;
import models.Product;

public class SaveProductDescriptionServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = "";
		if(user!=null){
			int productId = Integer.parseInt(request.getParameter("product_id"));
			String description = request.getParameter("description");

			Product product = (Product)session.getAttribute("product");
			product.setDescription(description);
						
			if(product.saveDescription()){
				resp += "{\"resp\":1}";
			}else{
				resp += "{\"resp\":0}";
			}			
		}else{
			resp += "{\"resp\":-1}";
		}

		response.getWriter().write(resp);
	}
}