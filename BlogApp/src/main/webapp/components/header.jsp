<%@ page import="com.blog.models.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<header>
<%--	<h1>Welcome to Our System</h1>--%>
<%--	<h2>Sigin to Start Using our task Management. If you dont have an account register. </h2>--%>
	<ul>
		<li><a href="blog">Home</a></li>
		<li><a href="user">Users</a></li>
		<%
			User user = (User)request.getAttribute("user");
			if(user == null){ %>
				<li><a href="login">Log in</a></li>
			<% } else { %>
				<li><a href="library">Librarys</a></li>
				<li><a href="profile">Profile</a></li>
		<% } %>
	</ul>
	</header>
	
	
	
</body>
</html>