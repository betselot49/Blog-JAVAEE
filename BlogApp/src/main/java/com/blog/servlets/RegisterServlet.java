package com.blog.servlets;

import com.blog.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = "user";

        try {
            User user = new User();

            user.FullName = fullName;
            user.Email = email;
            user.Password = password;
            user.Role = role;

            int rowsAffected = user.create();
            if (rowsAffected > 0) {
                request.setAttribute("success", true);
                response.sendRedirect("register.jsp");
            } else {
                request.setAttribute("error", true);
                response.sendRedirect("register.jsp");
            }
        } catch (Exception e) {
            request.setAttribute("error", true);
            e.printStackTrace();
        }

    }

}
