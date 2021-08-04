<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <link rel="stylesheet" href="static/css/search_result.css">
  <title>ecart::search result</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>

	<input type="hidden" id="search_key" value="${param.search}">
	
	<div id="main_body">	
		<div id="msg">Product Added Successfully....</div>
		<div id="outer_box">
			<div id="inr_rht">
				<div id="blk1" class="blk_">
					<div id="allprods_ttl">All Products</div>
					<div class="no_recs" id="noprods">No Records Found</div>
				</div>				
			</div>		
		</div>
	</div>	
	
	<%@ include file="footer.jsp" %>
  </div> 

  <script src="static/js/search_result.js"> </script>
 </body>
</html>
