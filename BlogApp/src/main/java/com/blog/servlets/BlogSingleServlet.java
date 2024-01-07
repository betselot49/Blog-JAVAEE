package com.blog.servlets;

import com.blog.models.Blog;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/blog/:id")
public class BlogSingleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryParam = request.getParameter("search");
        if (queryParam == null)  {
            queryParam = "";
        }
        try {
            ArrayList<Blog> blogs = Blog.search(queryParam);
            request.setAttribute("blogs", Blog.search(queryParam));
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
        request.getRequestDispatcher("blog.jsp").forward(request, response);
    }
}
