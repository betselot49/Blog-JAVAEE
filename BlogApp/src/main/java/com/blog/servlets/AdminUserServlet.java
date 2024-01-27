package com.blog.servlets;
import com.blog.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/users")
public class AdminUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String search = request.getParameter("search") != null ? request.getParameter("search") : "";
//        int pageNumber = request.getParameter("pageNumber") != null ? Integer.parseInt(request.getParameter("pageNumber")) : 0;
//        int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize"))
//                : 15;
        try {
            ArrayList<User> users = User.search("", 0, 150);
            request.setAttribute("users", users);
        }
        catch (Exception exception){
            request.setAttribute("error", exception.getMessage());
        }
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
