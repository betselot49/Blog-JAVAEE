<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<%@ include file="components/header.jsp" %>
<h2>Login Here</h2>
<form action="login" method="POST">
	<label> Email: </label>
	<input type="text" name="email" />
	<br />
	<label> Password: </label>
	<input type="password" name="password" />
	<br />
	<button type="submit">Log In</button>
</form>
<p>Don't have an account? <a href="/blog/register">Create an account</a></p>
<%
	// Check for an error condition
	String error = (String) request.getAttribute("error");

	if (error != null && !error.isEmpty()) {
%>
<div style="color: red;">
	<!-- Display the error message -->
	<%= error %>
</div>
<%
	}
%>
</body>
</html>