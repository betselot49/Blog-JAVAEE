package com.blog.servlets;

import com.blog.models.Blog;
import com.blog.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("=========Profile Servlet==============");
        // Assuming you have a User class with the necessary fields
        User user = (User) Objects.requireNonNull(request.getAttribute("user"));

        // Set user details as request attributes for JSP to access
        request.setAttribute("userName", user.FullName);
        request.setAttribute("userEmail", user.Email);
        request.setAttribute("userProfilePicture", user.ProfilePicture);

        try {
            ArrayList<Blog> userBlogs = Blog.getByUserId(user.Id);
            request.setAttribute("blogs", userBlogs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Forward the request to the profile.jsp page
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
