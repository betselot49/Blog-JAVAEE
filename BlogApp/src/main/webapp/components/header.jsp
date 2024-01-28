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
<header class="fixed-top bg-dark" >
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container" >
			<a class="navbar-brand"
			   href="#">Blog</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarNav" aria-controls="navbarNav"
					aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item <%= (request.getRequestURI().endsWith("/blog") || request.getRequestURI().equals("/")) ? "active" : "" %>">
						<a class="nav-link" href="/blog/blog">Home</a>
					</li>
					<li class="nav-item <%= request.getRequestURI().endsWith("/users") ? "active" : "" %>">
						<a class="nav-link" href="/blog/users">Users</a>
					</li>
					<%
						User user = (User)request.getAttribute("user");
						if(user == null){ %>
					<li class="nav-item <%= request.getRequestURI().endsWith("/login") ? "active" : "" %>">
						<a class="nav-link" href="/blog/login">Log in</a>
					</li>
					<% } else { %>
					<li class="nav-item <%= request.getRequestURI().endsWith("/library") ? "active" : "" %>">
						<a class="nav-link" href="/blog/library">Libraries</a>
					</li>
					<li class="nav-item <%= request.getRequestURI().endsWith("/profile") ? "active" : "" %>">
						<a class="nav-link" href="/blog/profile">Profile</a>
					</li>
					<% if (user.Role.equals("admin")) { %>
					<li class="nav-item <%= request.getRequestURI().endsWith("/adminregister") ? "active" : "" %>">
						<a class="nav-link" href="/blog/adminregister">Admin Register</a>
					<% } }%>
				</ul>
				<%
					String currentURI = request.getRequestURI();
					String[] splitURI = currentURI.split("\\.");
					String lastPart = splitURI[0];

					String lastPart2 = lastPart.substring(lastPart.lastIndexOf("/") + 1);


					int margin = 0;
					if (lastPart2.equals("users") || lastPart2.equals("profile")) {
						margin = 3;
					}

				%>
				<form class="form-inline ml-auto p-0" style="background-color: #343A40" action="<%=lastPart%>">
					<div class="form-group mr-2 p-0">
						<input type="text" class="form-control" name="search" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-primary">Search</button>
				</form>
				<% if (user != null) {

				%>
				<form class="m-0 p-0 mb-<%=margin%>" style="background-color: #343A40" action="/blog/logout" method="get">
					<button type="submit" class="btn btn-primary ml-5">Logout</button>
				</form>
				<%}%>
			</div>
		</div>
	</nav>
</header>

</body>
</html>
