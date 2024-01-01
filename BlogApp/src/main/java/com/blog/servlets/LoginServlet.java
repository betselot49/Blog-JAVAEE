package com.blog.servlets;

import com.blog.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=========Login Servlet==============");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String newPassword = request.getParameter("password");

		try {
			User loggedInUser = User.getByEmail(email);
			if (loggedInUser == null) {
				request.setAttribute("error", "User not found. Please register first.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}

			String password = loggedInUser.Password;
			if (Objects.equals(password, newPassword)) {
				request.getSession().setAttribute("loggedInUser", loggedInUser);
				response.sendRedirect("blog");
			} else {
				request.setAttribute("error", "Invalid credentials. Please try again.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", true);
			e.printStackTrace();
		}

	}
}
