package com.blog.servlets;

import com.blog.models.Blog;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/library")
public class LibraryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=========Blog Servlet==============");
        String queryParam = request.getParameter("search");
        if (queryParam == null)  {
            queryParam = "";
        }
        try {
            ArrayList<Blog> blogs = Blog.search(queryParam);
            request.setAttribute("success", Blog.search(queryParam));
        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
        request.getRequestDispatcher("blog.jsp").forward(request, response);
    }
}
