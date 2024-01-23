<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Registration</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<style>
		body {
			background-color: #f8f9fa;
		}

		.card {
			width: 500px;
			margin: 0 auto;
			border: none;
			border-radius: 10px;
			box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
		}

		.card-header {
			background-color: #007bff;
			border-radius: 10px 10px 0 0;
			padding: 40px;
			text-align: center;
		}

		.card-header h2 {
			color: #fff;
			font-size: 32px;
			font-weight: bold;
			margin: 0;
		}

		.card-body {
			padding: 40px;
		}

		.form-group label {
			font-weight: bold;
		}

		.form-control {
			border-radius: 5px;
			padding: 10px;
		}

		.btn-primary {
			background-color: #007bff;
			border: none;
			border-radius: 5px;
			padding: 10px 20px;
			font-size: 16px;
			font-weight: bold;
			transition: background-color 0.3s;
		}

		.btn-primary:hover {
			background-color: #0069d9;
		}

		.login-link {
			text-align: center;
			margin-top: 20px;
		}

		.card-footer {
			background-color: #f8f9fa;
			border-top: none;
			border-radius: 0 0 10px 10px;
			padding: 20px;
		}

		.card-footer p {
			margin-bottom: 0;
		}

		.bg-primary {
			background-color: #007bff;
		}

		.text-white {
			color: #fff;
		}

		.container {
			display: flex;
			align-items: center;
			justify-content: center;
			height: 100vh;
		}

		.register-title {
			font-size: 28px;
			font-weight: bold;
			margin-bottom: 20px;
		}

		.register-form {
			max-width: 400px;
			width: 100%;
		}

		.register-form .form-control {
			height: 50px;
		}

		.register-form .btn-primary {
			width: 100%;
		}

		.register-form .login-link {
			margin-top: 30px;
			font-size: 14px;
		}

		.register-form .login-link a {
			color: #6c757d;
		}

		.register-form .login-link a:hover {
			color: #007bff;
		}

		.custom-file-input {
			cursor: pointer;
		}

		.custom-file-label {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
		}

		.profile-picture-preview {
			max-width: 100%;
			height: auto;
			margin-top: 10px;
		}

	</style>
</head>
<body>

<%
	String error = (String) request.getAttribute("error");
	String success = (String) request.getAttribute("success");
%>

<div class="container">
	<div class="card">
		<div class="card-header bg-primary text-white">
			<h2 class="display-4">Registration</h2>
		</div>
		<div class="card-body">
			<form class="register-form" action="register" method="POST" >
				<h3 class="register-title">Create an Account</h3>
				<div class="form-group">
					<label for="fullName">Full Name</label>
					<input type="text" class="form-control" id="fullName" name="fullName" required>
				</div>
				<div class="form-group">
					<label for="email">Email</label>
					<input type="text" class="form-control" id="email" name="email" required>
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" class="form-control" id="password" name="password" required>
				</div>
<%--				<div class="form-group">--%>
<%--					<label for="profilePicture" class="custom-file-label">Choose Profile Picture</label>--%>
<%--					<input type="file" class="custom-file-input" id="profilePicture" name="profilePicture" accept="image/*">--%>
<%--				</div>--%>
				<button type="submit" class="btn btn-primary">Register</button>
				<div class="login-link">
					<p>
						Already have an account? <a href="/blog/login">Login</a>
					</p>
					<%if (error != null) {
					%>
					<div class="alert alert-danger">
						<p><%=error%></p>
					</div>
					<%
						}
					if (success != null) { %>
					<div class="alert alert-danger">
						<p> Successfully Registered <a href="/blog/login">Login Here</a></p>
					</div>
					<% } %>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- Bootstrap JS and Popper.js (if needed) -->
<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>




















