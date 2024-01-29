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

@WebServlet("/library/details")
@MultipartConfig
public class SaveSingleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int readingId = Integer.parseInt(request.getParameter("Id"));
            ArrayList<Blog> library = Save.getBlogs(readingId);
            request.setAttribute("blogs", library);
        } catch (Exception throwables) {
            request.setAttribute("blogs", new ArrayList<Blog>());
            request.setAttribute("error", throwables.getMessage());
        }
        request.getRequestDispatcher("../blogs.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        try {
            ArrayList<ReadingList> library = ReadingList.getMyReadingList(userId);
            for (ReadingList rl: library) {
                Save save = new Save();
                save.UserId = userId;
                save.BlogId = blogId;
                save.ReadingListId = rl.Id;
                String  marked = request.getParameter("readinglist" + rl.Id);
                boolean saved = Save.isSaved(userId, blogId, rl.Id);
                if (marked != null && !saved){
                    int rowsAffected = save.create();
                    if (rowsAffected > 0) {
                        request.setAttribute("success", "Successfully added to Reading List");
                    } else {
                        request.setAttribute("error", "Failed to update Reading List");
                    }

                }
                else if (marked == null && saved){
                    int rowsAffected = save.delete();
                    if (rowsAffected > 0) {
                        request.setAttribute("success", "Successfully removed from Reading List");
                    } else {
                         request.setAttribute("error", "Failed to update Reading List");
                    }

                }

            }

        } catch (Exception throwables) {
            request.setAttribute("error", throwables.getMessage());
        }
        response.sendRedirect("/blog/blog/details?id=" + blogId);
    }
}
