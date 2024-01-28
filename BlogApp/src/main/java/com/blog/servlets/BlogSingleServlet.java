package com.blog.servlets;

import com.blog.models.Blog;
import com.blog.models.Comment;
import com.blog.models.Like;
import com.blog.models.User;
import com.blog.utils.Helpers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/blog/details")
public class BlogSingleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=========Blog Single Servlet==============");
        try {
            int blogId = Integer.parseInt(request.getParameter("id"));
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
        System.out.println("=========Blog Single Servlet==============");
        try {
            int blogId = Integer.parseInt(request.getParameter("id"));
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
                response.getWriter().write("<script>window.history.back(); window.history.back();</script>");
                return;
            }

        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
    }

}
