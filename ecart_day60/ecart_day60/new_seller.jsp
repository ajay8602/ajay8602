<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <link rel="stylesheet" href="static/css/form.css">
  <title>ecart::new seller</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>
	
	<div id="main_body">
		<form action="new_seller.do" method="post">
			<table class="form_box">
				<caption>New Seller Account</caption>
				<tr>
					<td class="lft">Account Name</td>
					<td class="rht">
						<input type="text" name="account_name" class="input_normal">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Create New Seller Account" class="button">
					</td>
				</tr>
			</table>
		</form>
	</div>	
	
	<%@ include file="footer.jsp" %>
  </div>
 </body>
</html>
