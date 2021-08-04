<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="static/css/common.css">
  <title>ecart::result</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>
	
	<%@ include file="menu.jsp" %>
	+++++++++++${success}++++++++++
	<div id="main_body">
	<c:choose>	
		<c:when test="${success!=NULL}">
			<div class="result success">
				${success}
			</div>
		</c:when>
		<c:otherwise>
			<div class="result fail">
				${fail}
			</div>
		</c:otherwise>
	</c:choose>
	</div>	
	
	<%@ include file="footer.jsp" %>
  </div>
 </body>
</html>
