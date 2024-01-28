package com.blog.servlets;

import com.blog.models.Comment;
import com.blog.models.Like;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comment")
@MultipartConfig
public class CommentServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method.equals("delete")){
            doDelete(request, response);
        }
        else if (method.equals("post")){
            doPost(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String content = request.getParameter("comment");
        Comment comment = new Comment();
        comment.BlogId = blogId;
        comment.UserId = userId;
comment.Content = content;
        try {
            int rowsAffected = comment.create();
            if (rowsAffected > 0) {
                request.setAttribute("success", "Successfully Liked");
            } else {
                request.setAttribute("error", "Something went wrong. Please try again.");
            }
        } catch (Exception throwables) {
            request.setAttribute("error", throwables.getMessage());
        }
        response.sendRedirect("/blog/details?id=" + blogId);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        Comment comment = new Comment();
        comment.Id = commentId;
        try {
            int rowsAffected = comment.delete();
            if (rowsAffected > 0) {
                request.setAttribute("success", "Successfully Deleted the Comment");
            } else {
                request.setAttribute("error", "Something went wrong. Please try again.");
            }
        } catch (Exception throwables) {
            request.setAttribute("error", throwables.getMessage());
        }
        response.sendRedirect("/blog/details?id=" + comment.BlogId);
    }
}
