<% String error = (String)request.getAttribute("error"); %>

<div id="error_message" style='display:<%= error==null?"none":"block" %>'>
	<%= error %>
</div>