<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <link rel="stylesheet" href="static/css/form.css">
  <link rel="stylesheet" href="static/css/address.css">
  <title>ecart::home</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>
	
	<div id="main_body">
	
		<form action="address.do" method="post">
			<table class="form_box">
				<caption>Address</caption>
				<tr>
					<td class="lft">
						<label for="password">Address</label>
					</td>
					<td class="rht">
						<textarea name="address" rows="5" cols="30" maxlength="500" class="input_normal" id="address">${address.address}</textarea>
					</td>
				</tr>
				<tr>
					<td class="lft">
						<label for="repassword">City</label>
					</td>
					<td class="rht" id="ct_box">
						<input type="hidden" name="city_id" value="" id="city_id">
						<input type="text" name="city" class="input_normal" id="city" value="${address.city.city}">
						<div id="srch_res"></div>
					</td>
				</tr>
				<tr>
					<td class="lft">
						<label for="repassword">Pin</label>
					</td>
					<td class="rht">
						<input type="text" name="pin" 
						maxlength="6" class="input_normal" id="pin" value="${address.pin}">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Save&gt;&gt;" class="button">
						<a href="profpic.do" id="button2">Skip</a>		
					</td>
				</tr>
			</table>
		</form>
	
	</div>	
	
	<%@ include file="footer.jsp" %>
  </div>

  <script src="static/js/address.js"></script>
 </body>
</html>
