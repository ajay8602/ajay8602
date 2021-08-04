package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;
import utils.ProductCount;

import com.google.gson.Gson;

public class AddToCartServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		Integer productId = Integer.parseInt(request.getParameter("product_id"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));

		Map<Integer,Integer> cart = (Map<Integer,Integer>)session.getAttribute("cart");

		if(cart==null){
			cart = new LinkedHashMap<Integer,Integer>();
			session.setAttribute("cart",cart);
		}

		cart.put(productId,quantity);	

		//int cartItemsCount = cart.keySet().size();

		Set<Integer> set = cart.keySet();
		int cartItemsCount = 0;
		for(Integer key : set){
			cartItemsCount += cart.get(key);
		}

		session.setAttribute("cartItemsCount",cartItemsCount);

		ProductCount pc = new ProductCount(cartItemsCount);
		String resp = new Gson().toJson(pc);

		response.getWriter().write(resp);

		//String cartItemsCount = Integer.valueOf(cart.keySet().size()).toString();
		//response.getWriter().write("{\"count\":"+cartItemsCount+"}");
	}
}