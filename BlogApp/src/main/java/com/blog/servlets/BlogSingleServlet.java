package com.blog.servlets;

import com.blog.models.Blog;
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
        int blogId = 0;
        try {
            request.setAttribute("blog", Blog.getById(blogId));

        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
        request.getRequestDispatcher("blogs.jsp").forward(request, response);
    }
}
