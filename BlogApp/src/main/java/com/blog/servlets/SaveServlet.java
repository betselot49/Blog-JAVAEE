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
        try {
            ArrayList<ReadingList> library = ReadingList.getMyReadingList(user.Id);
            request.setAttribute("readinglists", library);
        } catch (Exception throwables) {
            request.setAttribute("error", throwables.getMessage());
        }
        request.getRequestDispatcher("librarys.jsp").forward(request, response);
    }

}
