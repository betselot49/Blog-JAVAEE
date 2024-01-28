<%@ page import="com.blog.models.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Sticky Header</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<style>
		ul {
			list-style: none;
			margin: 0;
			padding: 0;
			text-align: center;
		}

		li {
			display: inline-block;
			margin-right: 20px;
		}

		li a {
			color: #dee2e6; /* Light gray text color */
			text-decoration: none;
			font-weight: bold;
			font-size: 18px;
			transition: color 0.3s;
		}

		li a:hover {
			color: #adb5bd; /* Slightly lighter shade on hover */
		}
	</style>
</head>
<body>
<header class="fixed-top bg-dark" style="padding-top: 20px; padding-bottom: 10px">
	<nav class="container">
		<ul>
			<li class="<%= (request.getRequestURI().endsWith("/blog") || request.getRequestURI().equals("/")) ? "active" : "" %>">
				<a href="/blog/blog">Home</a>
			</li>
			<li class="<%= request.getRequestURI().endsWith("/users") ? "active" : "" %>">
				<a href="/blog/users">Users</a>
			</li>
			<%
				User user = (User)request.getAttribute("user");
				if(user == null){ %>
			<li class="<%= request.getRequestURI().endsWith("/login") ? "active" : "" %>">
				<a href="/blog/login">Log in</a>
			</li>
			<% } else { %>
			<li class="<%= request.getRequestURI().endsWith("/library") ? "active" : "" %>">
				<a href="/blog/library">Libraries</a>
			</li>
			<li class="<%= request.getRequestURI().endsWith("/profile") ? "active" : "" %>">
				<a href="/blog/profile">Profile</a>
			</li>
			<li class="<%= request.getRequestURI().endsWith("/logout") ? "active" : "" %>">
				<a href="/blog/logout">Logout</a>
			</li>
			<% } %>
		</ul>
	</nav>
</header>

<!-- Bootstrap JS and Popper.js (required for Bootstrap) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
