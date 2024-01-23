package com.blog.servlets;

import java.io.IOException;
import java.io.InputStream;

import com.blog.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

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
        Part filePart = request.getPart("profilePicture");
        InputStream fileContent = filePart.getInputStream();
        byte[] profilePicture = fileContent.readAllBytes();
        String role = "user";

        System.out.println("=========Register Servlet==============" + fullName + " " + email + " " + password);

        try {
            User user = new User();
            user.FullName = fullName;
            user.Email = email;
            user.Password = password;
            user.Role = role;
            user.ProfilePicture = profilePicture;
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
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
            e.printStackTrace();
        }

    }

}
