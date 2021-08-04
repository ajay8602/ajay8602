<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <link rel="stylesheet" href="static/css/form.css">
  <script src="https://www.google.com/recaptcha/api.js" async defer></script>
  <title>ecart::signup</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>
	
	<div id="main_body">
		<%@ include file="error.jsp" %>
		
		<form action="signup.do" method="post">
			<table class="form_box">
				<caption>SignUp</caption>
				<tr>
					<td class="lft">
						<label for="username">Username</label>
					</td>
					<td class="rht">
						<input type="text" name="username" maxlength="30" minlength="5" class="input_normal" id="username">
					</td>
				</tr>
				<tr>
					<td class="lft">
						<label for="email">Email</label>
					</td>
					<td class="rht">
						<input type="email" name="email" class="input_normal" id="email">
					</td>
				</tr>
				<tr>
					<td class="lft">
						<label for="password">Password</label>
					</td>
					<td class="rht">
						<input type="password" name="password" minlength="6" maxlength="12" class="input_normal" id="password">
					</td>
				</tr>
				<tr>
					<td class="lft">
						<label for="repassword">Retype Password</label>
					</td>
					<td class="rht">
						<input type="password" name="repassword" class="input_normal" id="repassword">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<div class="g-recaptcha" data-sitekey="6LckhtcZAAAAABimZqDd1yTB9A4S22jk1Ik25rAf"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Signup" class="button">
					</td>
				</tr>
			</table>
		</form>
	</div>	
	
	<%@ include file="footer.jsp" %>
  </div>

  <script src="static/js/signup.js"> </script>
 </body>
</html>
