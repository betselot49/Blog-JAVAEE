<%@ page import="com.blog.models.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<header>
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
