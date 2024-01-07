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

@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=========Blog Servlet==============");
        String queryParam = request.getParameter("search");
        if (queryParam == null)  {
            queryParam = "";
        }
        try {
            ArrayList<Blog> blogs = Blog.search(queryParam);
            request.setAttribute("blogs", Blog.search(queryParam));
        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
		request.getRequestDispatcher("blog.jsp").forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int userId = Integer.parseInt(request.getParameter("userId"));

        try {
            Blog blog = new Blog();

            blog.Title = title;
            blog.Content = content;
            blog.UserId = userId;

            int rowsAffected = blog.create();
            if (rowsAffected > 0) {
                request.setAttribute("success", "Successfully created blog.");
                request.getRequestDispatcher("blog.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Something went wrong. Please try again.");
                request.getRequestDispatcher("blog.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }

    }
}
