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
import java.util.Objects;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String method = request.getParameter("method") != null ? request.getParameter("method") : request.getMethod();
//        if (method.equals("delete")){
//            doDelete(request, response);
//        }
//        else if (method.equals("put")){
//            doPut(request, response);
//        }
//        else if (method.equals("post")){
//            doPost(request, response);
//        }
//        else if (method.equals("get")){
//            doGet(request, response);
//        }
//    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("=========Profile Servlet==============");
        // Assuming you have a User class with the necessary fields
        User user = (User) request.getAttribute("user");
        String Id = request.getParameter("Id");


        try {
            if (Id != null){
                user = User.getById(Integer.parseInt(Id));
            }

            // Set user details as request attributes for JSP to access
            request.setAttribute("userName", user.FullName);
            request.setAttribute("userEmail", user.Email);
            request.setAttribute("userProfilePicture", user.ProfilePicture);
            ArrayList<Blog> userBlogs = Blog.getByUserId(user.Id);
            request.setAttribute("blogs", userBlogs);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        // Forward the request to the profile.jsp page
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = new User();
        user.Id = userId;
        try {
            int rowsAffected = user.delete();
            if (rowsAffected > 0) {
                request.setAttribute("success", "Successfully deleted the User");
            } else {
                request.setAttribute("error", "Failed to create Blog");

            }
        } catch (Exception throwables) {
            request.setAttribute("error", throwables.getMessage());
        }
        response.sendRedirect("/blog/blog");
    }
}
