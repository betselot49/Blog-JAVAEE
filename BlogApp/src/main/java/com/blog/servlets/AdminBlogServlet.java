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

@WebServlet("/admin/blog")
public class AdminBlogServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<Blog> blogs = Blog.getAll();
            request.setAttribute("blogs", blogs);
        }
        catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
//        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
