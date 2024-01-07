package com.blog.servlets;

import com.blog.models.Blog;
import com.blog.models.User;
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
            request.setAttribute("success", "Successfully fetched Blogs");
        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
		request.getRequestDispatcher("blogs.jsp").forward(request, response);
	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("=========Blog Servlet==============");
//        Blog blog = new Blog();
//        blog.Title = request.getParameter("content");
//        blog.Content = request.getParameter("title");
//        blog.UserId = ((User) request.getAttribute("user") ).Id;
        String x = request.getParameter("image");
        System.out.println("======" + request.getParameter("imgae") + "=========");
        request.getRequestDispatcher("blogs.jsp").forward(request, response);
    }
}
