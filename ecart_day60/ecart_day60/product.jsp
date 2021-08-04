<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <link rel="stylesheet" href="static/css/seller_common.css">
  <link rel="stylesheet" href="static/css/product.css">
  <title>ecart::product</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>
	
	<div id="main_body">
		<input type="hidden" id="product_id" value="${product.productId}">

		<div id="new_product_box">
			<a href="seller_page.do" class="seller_home">Seller Accounts</a>
			<span>&nbsp;&nbsp;/&nbsp;&nbsp;<a href="seller_dashboard.do?seller_id=${seller.sellerId}&seller=${seller.sellerAccountName}"  class="seller_home" >${seller.sellerAccountName}</a></span>

			<span class="seller_name">&nbsp;&nbsp;/&nbsp;&nbsp;Product View</span>

			<input type="hidden" id="slrid_" value="${seller.sellerId}">	
		</div>
		
		<div id="product_box">	
			<div id="lft">
				<div id="all_pics_box">
					<!-- <img class="small_pic" src="static/images/a1.jpg">
					<img class="small_pic" src="static/images/a2.jpg">
					<img class="small_pic" src="static/images/a3.jpg">
					<img class="small_pic" src="static/images/a4.jpg">
					<img class="small_pic" src="static/images/a5.jpg">
					<img class="small_pic" src="static/images/a6.jpg"> -->
				</div>
				<div id="pic_box">
					<!-- <img id="pic_focus" src="static/images/a1.jpg"> -->
				</div>
			</div>
			
			<div id="rht">
				<div id="prod_ttl">${product.productName}</div>
				<div class="recs" id="slr_">
					<span class="ttl">Seller : </span>
					<span id="seller">${product.seller.sellerAccountName}</span>
				</div>
				<p class="recs">
					<span class="ttl">Price : </span>
					<span id="price">${product.price*(100-product.discount)/100}</span>
					<span id="aftdisc">After Discount</span>
				</p>
				<p class="recs">
					<span class="ttl">MRP. : </span>
					<s id="mrp">${product.price}</s>
				</p>
				
				<div id="all_tabs">					
					<div class="desc desc_sel">Description</div>
					<div class="desc desc_nsel">Warranty</div>
					<div class="desc desc_nsel">Returning Policy</div>
					<div class="desc desc_nsel">Shipment Details</div>
					<div class="desc desc_nsel">Payment Details</div>		
				</div>
				<div id="other_details_box">
					<div class="odtl odtl_show">
						${product.description}
					</div>
					<div class="odtl odtl_hide">
						${product.warranty}
					</div>
					<div class="odtl odtl_hide">
						${product.returningPolicy}
					</div>
					<div class="odtl odtl_hide">
						${product.shipmentDetails}
					</div>
					<div class="odtl odtl_hide">
						${product.paymentDetails}
					</div>
				</div>
				<div id="propt_box">
					<table id="propt_tbl">
						<caption>Other Product Details</caption>
						
					</table>
				</div>
			</div>
		</div>
	</div>	
	
	<%@ include file="footer.jsp" %>
  </div>

  <script src="static/js/product.js"> </script>
 </body>
</html>
