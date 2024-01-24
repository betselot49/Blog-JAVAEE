package com.blog.servlets;

import com.blog.models.Like;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/like")
@MultipartConfig
public class LikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        Like like = new Like();
        like.BlogId = blogId;
        like.UserId = userId;
        try {
            int rowsAffected;
            if (Like.isLiked(userId, blogId)) {
                rowsAffected = like.delete();
            } else {
                rowsAffected = like.create();
            }
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
}
