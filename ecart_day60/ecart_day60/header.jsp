<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header">
	<div id="hd1">
		<img src="static/images/logo.png" id="logo">
	</div>
	<div id="hd3">
		<img src="static/images/cart.png" id="cart">
			<span id="cart_count">
				<c:choose>
					<c:when test="${cartItemsCount ne null}">
						${cartItemsCount}
					</c:when>
					<c:otherwise>
						0
					</c:otherwise>
				</c:choose>
			</span>
		</img>
		<img src="static/images/ctrl.png" id="ctrl">
		<div id="ctrl_pnl">
			<div id="user">
				<span id="grt">Welcome!!</span><br>
				<c:if test="${user!=null}">
					<span id="usr">${user.userName}</span>
				</c:if>
			</div>
			<div id="auth_ctrl_box">
				<c:choose>
					<c:when test="${user==null}">
						<a href="signin.do">Sin-In</a>
						<a href="signup.do">Sin-Up</a>
					</c:when>
					<c:otherwise>
						<a href="profile.do" id="profpic_box">
							<img src="showprofpic.do" id="profpic_">
						</a>
						<a href="signout.do">Sign-Out</a>					
					</c:otherwise>
				</c:choose>
			</div>			
		</div>
	</div>
	<div id="menu_box">
		<div id="search_box_container">		
			<div id="search_box">				
				<span id="categories">All</span>
				<input type="search" name="search" id="search">
				<img src="static/images/go.png" height="24" id="search_button">
			</div>		
		</div>		
	</div>
</div>

<div id="menu">
	<a href="#">Electronics</a>
	<a href="#">Books</a>
	<a href="#">Kids</a>
	<a href="#">Men's Fashion</a>
	<a href="#">Women's Fashion</a>
	<a href="#">Sports</a>
	<div id="seller_button_box">
		<a href="seller_page.do">Seller</a>
	</div>
</div>
<script src="static/js/header.js"></script>

