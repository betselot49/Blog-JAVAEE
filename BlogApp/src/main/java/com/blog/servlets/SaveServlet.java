package com.blog.servlets;

import com.blog.models.*;
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
        String queryParam = request.getParameter("search") == null ? "" : request.getParameter("search");
        try {
            ArrayList<ReadingList> library = ReadingList.search(user.Id, queryParam);
            request.setAttribute("readinglists", library);
        } catch (Exception throwables) {
            request.setAttribute("readinglists", new ArrayList<ReadingList>());
            request.setAttribute("error", throwables.getMessage());
        }
        request.getRequestDispatcher("librarys.jsp").forward(request, response);
    }

}
