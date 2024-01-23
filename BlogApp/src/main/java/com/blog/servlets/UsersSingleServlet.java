package com.blog.servlets;


import com.blog.models.Blog;
import com.blog.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/users/*")
public class UsersSingleServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=========User Single Servlet==============");
        int pageNumber = request.getParameter("pageNumber") != null ? Integer.parseInt(request.getParameter("pageNumber")) : 1;
        int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : 15;
        try {
            int userId = Integer.parseInt(request.getPathInfo().substring(1));
            request.setAttribute("people", User.getById(userId));
            request.setAttribute("blogs", Blog.getByUserId(userId));
            request.setAttribute("success", "Successfully Loaded User");
        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
