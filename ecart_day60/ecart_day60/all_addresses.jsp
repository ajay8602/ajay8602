<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <link rel="stylesheet" href="static/css/all_addresses.css">
  <link rel="stylesheet" href="static/css/form.css">
  
  <title>ecart::all_addresses</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>
	
	<div id="main_body">
		<div id="address_box">
			<div id="rht_box">
				<div id="record_box_container">
					<c:forEach var="address" items="${addresses}" varStatus="seq">
						<div id="record_box">					
							<div class="hd">
								Address ${seq.count}
								<input type="button" value="Send To" class="sendtobtn">
							</div>
							<div class="addr">${address.name}</div>
							<div class="addr">${address.address}</div>
							<div class="addr">${address.city.city}</div>
							<div class="addr">${address.pin}</div>				
						</div>
					</c:forEach>
				</div>
				<div id="form_box_container">
					<form>
						<table class="form_box">
							<caption>Address</caption>
							<tr>
								<td class="lft">
									<label for="name">Name</label>
								</td>
								<td class="rht">
									<input type="text" name="name" 
									maxlength="60" class="input_normal" id="name">
								</td>
							</tr>
							<tr>
								<td class="lft">
									<label for="password">Address</label>
								</td>
								<td class="rht">
									<textarea name="address" rows="5" cols="30" maxlength="500" class="input_normal" id="address"></textarea>
								</td>
							</tr>
							<tr>
								<td class="lft">
									<label for="repassword">City</label>
								</td>
								<td class="rht" id="ct_box">
									<input type="hidden" name="city_id" value="" id="city_id">
									<input type="text" name="city" class="input_normal" id="city" >
									<div id="srch_res"></div>
								</td>
							</tr>
							<tr>
								<td class="lft">
									<label for="repassword">Pin</label>
								</td>
								<td class="rht">
									<input type="text" name="pin" 
									maxlength="6" class="input_normal" id="pin">
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="submit" value="Save&gt;&gt;" class="button">
											
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div id="lft_box">
				<c:forEach var="addr" items="${addresses}" varStatus="seq">
					<div class="adrtab">Address ${seq.count}</div>
				</c:forEach>
				<div id="addnew">+ Add New</div>
			</div>			
		</div>
	</div>	
	
	<%@ include file="footer.jsp" %>
  </div>

  <script src="static/js/all_addresses.js"></script>
  <script src="static/js/address.js"></script>
 </body>
</html>
