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

@WebServlet("/readinglist")
@MultipartConfig
public class ReadingListServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method.equals("delete")){
            doDelete(request, response);
        }
        else if (method.equals("put")){
            doPut(request, response);
        }
        else if (method.equals("post")){
            doPost(request, response);
        }
        else if (method.equals("get")){
            doGet(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String readinglist = request.getParameter("readingList");
        ReadingList rl = new ReadingList();
        rl.UserId = userId;
        rl.Name = readinglist;
        try {
            int rowsAffected = rl.create();
            if (rowsAffected > 0) {
                request.setAttribute("success", "Successfully created Reading List");
            } else {
                request.setAttribute("error", "Failed to create Reading List");
            }
        } catch (Exception throwables) {
            request.setAttribute("error", throwables.getMessage());
        }
        response.sendRedirect("/blog/library");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int readinglistId = Integer.parseInt(request.getParameter("readingListId"));
        ReadingList rl = new ReadingList();
        rl.UserId = userId;
        rl.Id = readinglistId;
        try {
            int rowsAffected = rl.delete();
            if (rowsAffected > 0) {
                request.setAttribute("success", "Successfully Deleted Reading List");
            } else {
                request.setAttribute("error", "Something went wrong. Please try again.");
            }
        } catch (Exception throwables) {
            request.setAttribute("error", throwables.getMessage());
        }
        response.sendRedirect("/blog/library");
    }
}
