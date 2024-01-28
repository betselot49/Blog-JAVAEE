package com.blog.servlets;

import com.blog.models.Blog;
import com.blog.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/")
public class StartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        User user = (User) request.getAttribute("user");
//
//        if (user != null && user.Role.equals("admin")){
//            response.sendRedirect("admin/blog");
//            return;
//        }
        response.sendRedirect("blog");
    }
}
