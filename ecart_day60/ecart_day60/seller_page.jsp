<!doctype html>

<%@ taglib prefix="app" uri="ecart_tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <link rel="stylesheet" href="static/css/seller_page.css">
  <title>ecart::seller</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>
	
	<div id="main_body">
		
		<div id="new_seller_box">
			<a href="new_seller.do" id="new_seller">+ New Seller Account</a>
		</div>
		
		<c:choose>
			<c:when test="${empty sellers}">
				<h3 id="msg">No Seller Account Found</h3>
			</c:when>
			<c:otherwise>
				<table id="seller_box">
					<tr id="hd">
						<th>&nbsp;</th>
						<th>Seller Account</th>
						<th>Start Date</th>
					</tr>
					
					<c:forEach var="seller" items="${sellers}">
						<tr>
							<td class="center cl1">
								<input type="checkbox" name="seller_id" value="${seller.sellerId}">
							</td>
							<td class="center cl2">
								<a href="seller_dashboard.do?seller_id=${seller.sellerId}&seller=${seller.sellerAccountName}">${seller.sellerAccountName}</a>
							</td>
							<td class="center cl3">${app:format(seller.startDate)}</td>
						</tr>
					</c:forEach>
				</table>				
			</c:otherwise>
		</c:choose>
		
	
	</div>	
	
	<%@ include file="footer.jsp" %>
  </div>

  
 </body>
</html>
