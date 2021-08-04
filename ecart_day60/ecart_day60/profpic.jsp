<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <link rel="stylesheet" href="static/css/form.css">
  <link rel="stylesheet" href="static/css/profpic.css">
  <title>ecart::home</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>
	
	<div id="main_body">
		<table id="outer_box">
			<tr>
				<td class="outer_box_cell">
					<img src="showprofpic.do" id="profpic">
				</td>
				<td class="outer_box_cell">
					<form action="profpic.do" method="post" enctype="multipart/form-data">
						<table class="form_box">
							<caption>Profile Pic</caption>
							<tr>
								<td class="lft">
									<label for="username">Profile Pic :</label>
								</td>
								<td class="rht">
									<input type="file" name="profpic" class="input_normal" id="profpic_fld">
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="submit" value="Upload" class="button">
								</td>
							</tr>
						</table>
					</form>				
				</td>
			</tr>
		</table>

		
		
	</div>	
	
	<%@ include file="footer.jsp" %>
  </div>
 </body>
</html>
