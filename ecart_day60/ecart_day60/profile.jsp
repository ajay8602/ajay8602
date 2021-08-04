<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <link rel="stylesheet" href="static/css/form.css">
  <link rel="stylesheet" href="static/css/profile.css">
  <title>ecart::profile</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>
	
	<div id="main_body">	

		<form action="profile.do" method="post">
			<table class="form_box">
				<caption>Profile</caption>
				<tr>
					<td class="lft">
						<label for="firstname">First Name</label>
					</td>
					<td class="rht">
						<input type="text" name="firstname" maxlength="20" class="input_normal" id="firstname" value="${user.firstName}">
					</td>
				</tr>
				<tr>
					<td class="lft">
						<label for="middlename">Middle Name</label>
					</td>
					<td class="rht">
						<input type="text" name="middlename" maxlength="20" class="input_normal" id="middlename" value="${user.middleName}">
					</td>
				</tr>
				<tr>
					<td class="lft">
						<label for="lastname">Last Name</label>
					</td>
					<td class="rht">
						<input type="text" name="lastname" maxlength="20" class="input_normal" id="lastname" value="${user.lastName}">
					</td>
				</tr>
				<tr>
					<td class="lft">
						<label for="dob">Date of Birth</label>
					</td>
					<td class="rht">
						<input type="date" name="dob" class="input_normal" id="dob" value="${user.dob}">
					</td>
				</tr>
				<tr>
					<td class="lft">
						<label for="mobile">Mobile Number</label>
					</td>
					<td class="rht">
						<input type="text" name="mobile" maxlength="10" minlength="10" class="input_normal" id="mobile" value="${user.mobile}">
					</td>
				</tr>				
				<tr>
					<td colspan="2">
						<input type="submit" value="Send OTP" class="button">
						<a href="address.do" id="button2">Skip</a>		
					</td>
				</tr>
			</table>

			<div id="otp_box_wrapper">
				<div id="loader">
					<img src="static/images/loader.gif" id="loader_img">
				</div>
				<div id="otp_box">
					<div id="close_box">
						<img src="static/images/close.png" id="close">
					</div>
					<h3>
						OTP sent to your registered Number:
					</h3>					
					<label for="otp">Enter OTP : </label> 
					<input type="text" maxlength="6" name="otp" id="otp">
					<input type="submit" value="Submit" id="otp_btn">	
				</div>
			</div>
		</form>

	</div>
	
	
	<%@ include file="footer.jsp" %>
  </div>  

  <script src="static/js/profile.js"> </script>
 </body>
</html>
