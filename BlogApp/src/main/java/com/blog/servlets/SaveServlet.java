package com.blog.servlets;

import com.blog.models.Blog;
import com.blog.models.Like;
import com.blog.models.Save;
import com.blog.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/library")
@MultipartConfig
public class SaveServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getAttribute("user");
        try {
            ArrayList<Blog> library = Save.getMySavedBlogs(user.Id);
            request.setAttribute("blogs", library);
            request.setAttribute("success", "Successfully Loaded Saved Blogs");
        } catch (Exception throwables) {
            request.setAttribute("error", throwables.getMessage());
        }
        request.getRequestDispatcher("blogs.jsp").forward(request, response);
    }
}
