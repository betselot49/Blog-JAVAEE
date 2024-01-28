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
						<a class="nav-link" href="blog">Home</a>
					</li>
					<li class="nav-item <%= request.getRequestURI().endsWith("/users") ? "active" : "" %>">
						<a class="nav-link" href="users">Users</a>
					</li>
					<%
						User user = (User)request.getAttribute("user");
						if(user == null){ %>
					<li class="nav-item <%= request.getRequestURI().endsWith("/login") ? "active" : "" %>">
						<a class="nav-link" href="login">Log in</a>
					</li>
					<% } else { %>
					<li class="nav-item <%= request.getRequestURI().endsWith("/library") ? "active" : "" %>">
						<a class="nav-link" href="library">Libraries</a>
					</li>
					<li class="nav-item <%= request.getRequestURI().endsWith("/profile") ? "active" : "" %>">
						<a class="nav-link" href="profile">Profile</a>
					</li>
					<% } %>
				</ul>
				<%
					String currentURI = request.getRequestURI();
					String[] splitURI = currentURI.split("\\.");

				%>
				<form class="form-inline ml-auto p-0" style="background-color: #343A40" action="<%=splitURI[0]%>">
					<div class="form-group mr-2 p-0">
						<input type="text" class="form-control" name="search"
							   placeholder="Search">
					</div>
					<button type="submit" class="btn btn-primary">Search</button>
				</form>
				<form class="m-0 p-0" style="background-color: #343A40" action="logout" method="get">
					<button type="submit" class="btn btn-primary ml-4">Logout</button>
				</form>
			</div>
		</div>
	</nav>
</header>

</body>
</html>
