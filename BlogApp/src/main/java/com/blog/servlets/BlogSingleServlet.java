package com.blog.servlets;

import com.blog.models.Blog;
import com.blog.utils.Helpers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/blog/*")
public class BlogSingleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=========Blog Single Servlet==============");
        try {
            int blogId = Integer.parseInt(Helpers.getLastPathSegment(request));
            Blog blog = Blog.getById(blogId);
            request.setAttribute("blog", Blog.getById(blogId));
            request.getRequestDispatcher("blog.jsp").forward(request, response);
        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
        request.getRequestDispatcher("blogs.jsp").forward(request, response);
    }
}
