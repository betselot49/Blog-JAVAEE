package com.blog.servlets;

import com.blog.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=========Register Servlet==============");
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password = request.getParameter("password");
        Part filePart = request.getPart("profilePicture");
        InputStream fileContent = filePart.getInputStream();
        blog.BlogPicture = fileContent.readAllBytes();
        String role = "user";

        try {
            User user = new User();

            user.FullName = fullName;
            user.Email = email;
            user.Password = password;
            user.Role = role;

            int rowsAffected = user.create();
            System.out.println("==============="+rowsAffected);
            if (rowsAffected > 0) {
                request.setAttribute("success", "Successfully registered. Please login to continue.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Something went wrong. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("==============="+ "Exception Throwed");
            request.setAttribute("error", "User already exists. Please Use another email.");
            e.printStackTrace();
        }

    }

}
