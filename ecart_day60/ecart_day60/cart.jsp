<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <link rel="stylesheet" href="static/css/cart.css">
  <title>ecart::cart</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>
	
	<div id="main_body">
		
		<c:forEach var="cartItem" items="${cart}">			
			<input type="hidden" class="prod_id" value="${cartItem.key}">	
		</c:forEach>

		<h2><span>Your Cart</span></h2>
		
		<div id="cart_box">
			<div id="empty">
				No Records to show... Add some Products...
			</div>
			<c:forEach var="product" items="${products}" varStatus="counter">
				<div class="prod_rec" id="prorec_${counter.index}">
					<div class="rec_rht">
						<div class="rec_rht_lft">
							<div class="prod_ttl">
								${product.productName}
							</div>
							<div class="quantity_box">
								<b>Quantity:</b> 
								<select class="pro_qt" data-proqt="${product.productId}">		
									<c:forEach var="cartRec" items="${cart}">			
										<c:choose>
											<c:when test="${cartRec.key==product.productId}">
												<c:forEach var="counter" items="${item_count}">
													<c:choose>
														<c:when test="${cartRec.value==counter}">
															<option selected>${counter}</option>
														</c:when>
														<c:otherwise>
															<option>${counter}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
										</c:choose>						
									</c:forEach>		
								</select>
							
								<span class="del_box" data-del="${product.productId}">
									<img src="static/images/del.png">
									Delete
								</span>
							</div>
						</div>
						<div class="prod_price">
							<span class="prcaftdis" >${product.price*(100-product.discount)/100}</span>
							<span class="discount">(${product.price})</span>
						</div>
					</div>
					<div class="rec_lft">
						<img src="product_image.do?product_id=${product.productId}" />
					</div>			
				</div>	
			</c:forEach>
		</div>	
		<div id="cart_total_box">
			<span id="ttl_lbl">Total: (Rs.)</span>
			<span id="ttl">0</span>
		</div>
		<div id="button_box">
			<input type="button" value="Checkout" id="checkout">
		</div>
	</div>	
	
	<%@ include file="footer.jsp" %>
  </div>

  <script src="static/js/cart.js"> </script>
 </body>
</html>
