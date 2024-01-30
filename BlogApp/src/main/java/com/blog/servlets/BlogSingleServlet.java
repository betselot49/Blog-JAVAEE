package com.blog.servlets;

import com.blog.models.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@WebServlet("/blog/details")
@MultipartConfig
public class BlogSingleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int blogId = Integer.parseInt(request.getParameter("id"));
            User user = (User) request.getAttribute("user");
            boolean isLiked = user != null ? Like.isLiked(user.Id, blogId) : false;
            request.setAttribute("blog", Blog.getById(blogId));
            request.setAttribute("isLiked", isLiked);
            request.setAttribute("comments", Comment.getComments(blogId));
            request.setAttribute("likes", Like.getLikers(blogId));

            if (request.getAttribute("user") == null) {
                request.getRequestDispatcher("../blog.jsp").forward(request, response);
                return;
            }

            ArrayList<ReadingList> rls = ReadingList.getMyReadingList(user.Id);
            request.setAttribute("readinglists", rls);

            for (ReadingList rl : rls) {
                request.setAttribute("readinglist" + rl.Id, Save.isSaved(user.Id, blogId, rl.Id));
            }

        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
        request.getRequestDispatcher("../blog.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method.equals("delete")){
            doDelete(request, response);
        }
        else if (method.equals("put")){
            doPut(request, response);
        }
    }


    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("id"));
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
        try {
            Blog blog = Blog.getById(blogId);
            if (request.getParameter("title") != null) blog.Title = request.getParameter("title");
            if (request.getParameter("content") != null) blog.Content = request.getParameter("content");
            if (request.getParameter("tags") != null) blog.Tags = request.getParameter("tags");
            if (buffer.size() > 0) blog.BlogPicture = buffer.toByteArray();
            int rowsAffected = blog.update();
            if (rowsAffected > 0) {
                request.setAttribute("success", "Successfully updated Blog");
            } else {
                request.setAttribute("error", "Something went wrong. Please try again.");
            }
            User user = (User) request.getSession().getAttribute("user");
            boolean isLiked = user != null ? Like.isLiked(user.Id, blogId) : false;
            request.setAttribute("blog", Blog.getById(blogId));
            request.setAttribute("isLiked", isLiked);
            request.setAttribute("comments", Comment.getComments(blogId));
            request.setAttribute("likes", Like.getLikers(blogId));
        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
        request.getRequestDispatcher("../blog.jsp").forward(request, response);
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        Blog blog = new Blog();
        blog.Id = blogId;
        try {
            int affected = blog.delete();
            if (affected == 0){
                request.setAttribute("error", "Something Went Wrong");
            }
            else {
                request.setAttribute("success", "Successfully Deleted the Blog");
                response.sendRedirect("/blog/blog");
                return;
            }

        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
    }

}
