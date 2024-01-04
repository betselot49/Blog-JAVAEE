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

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=========Users Servlet==============");
        String queryParam = request.getParameter("search");

        if (queryParam == null)  {
            queryParam = "";
        }
        try {
            request.setAttribute("users", User.search(queryParam));
            request.setAttribute("success", "Successfully fetched Users");
        }catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
