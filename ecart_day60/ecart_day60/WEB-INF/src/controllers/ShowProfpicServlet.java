package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.User;
import utils.Util;

public class ShowProfpicServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");		

		String profpicPath = "static/images/user.png";
		if(user!=null){		
			String profpic = user.getProfpic();
			if(profpic!=null)
				profpicPath = "/WEB-INF/uploads/"+profpic;
		}		

		Util.streamPic(getServletContext(),response.getOutputStream(),profpicPath);
	}
}