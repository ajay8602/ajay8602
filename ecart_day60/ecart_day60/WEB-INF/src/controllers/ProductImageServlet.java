package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.ProductPic;

public class ProductImageServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		int productId = Integer.parseInt(request.getParameter("product_id"));

		String picPath = ProductPic.getSingleProductPic(productId);

		InputStream is = getServletContext().getResourceAsStream("/WEB-INF/uploads/"+picPath);
		OutputStream os = response.getOutputStream();

		int count = 0;
		byte[] ar = new byte[1024];

		while((count=is.read(ar))!=-1){
			os.write(ar);
		}
		
		os.flush();
		os.close();
	}
}