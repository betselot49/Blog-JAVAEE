package com.blog.servlets;

import com.blog.models.Blog;
import com.blog.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.InputStream;

@WebServlet("/blog")
@MultipartConfig
public class BlogServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String getRequest = request.getRequestURI();
        String queryParam = request.getParameter("search") == null ? "" : request.getParameter("search");
        try {
            request.setAttribute("blogs", Blog.search(queryParam));
        }catch (Exception exception){
            request.setAttribute("blogs", new ArrayList<Blog>());
            request.setAttribute("error", exception.getMessage());
        }
		request.getRequestDispatcher("blogs.jsp").forward(request, response);
	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Blog blog = new Blog();
        blog.Content = request.getParameter("content");
        blog.Title = request.getParameter("title");
        blog.UserId = ((User) request.getAttribute("user") ).Id;
        blog.Tags = request.getParameter("tags");
        Part filePart = request.getPart("picture");
        InputStream fileContent = filePart.getInputStream();
        //blog.BlogPicture = fileContent.readAllBytes();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = fileContent.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        blog.BlogPicture = buffer.toByteArray();
        String queryParam = request.getParameter("search") == null ? "" : request.getParameter("search");
        try {
            int rowsAffected = blog.create();
            request.setAttribute("blogs", Blog.search(queryParam));
            if (rowsAffected > 0) {
                request.setAttribute("success", "Successfully created Blog");
            } else {
                request.setAttribute("error", "Something went wrong. Please try again.");
            }
        } catch (Exception throwables) {
            request.setAttribute("blogs", new ArrayList<Blog>());
            request.setAttribute("error", throwables.getMessage());
        }
        request.getRequestDispatcher("blogs.jsp").forward(request, response);
    }

}
