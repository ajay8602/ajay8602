package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.User;
import models.Product;

public class SaveWarrantyServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = "";
		if(user!=null){
			//int productId = Integer.parseInt(request.getParameter("product_id"));
			String warranty = request.getParameter("warranty");

			Product product = (Product)session.getAttribute("product");
			product.setWarranty(warranty);
						
			if(product.saveWarranty()){
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