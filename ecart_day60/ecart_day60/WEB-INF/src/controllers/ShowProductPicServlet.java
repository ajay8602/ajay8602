package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import utils.Util;

public class ShowProductPicServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String productPic = "/WEB-INF/uploads/"+request.getParameter("product_pic");
		
		Util.streamPic(getServletContext(),response.getOutputStream(),productPic);
	}
}