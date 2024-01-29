package com.blog.servlets;


import java.io.IOException;

import com.blog.models.Blog;
import com.blog.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users/details")
public class UsersSingleServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=========User Single Servlet==============");
        int userId = Integer.parseInt(request.getParameter("id"));
//        int pageNumber = request.getParameter("pageNumber") != null ? Integer.parseInt(request.getParameter("pageNumber")) : 0;
//        int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : 15;
        try {
            request.setAttribute("people", User.getById(userId));
            request.setAttribute("blogs", Blog.getByUserId(userId));
        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
