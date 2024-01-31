package com.blog.servlets;

import com.blog.models.User;
import com.blog.config.DBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.mindrot.jbcrypt.BCrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/EditProfileServlet")
@MultipartConfig
public class EditProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=========EditProfile Servlet==============");
        Part filePart = request.getPart("profilePicture");
        InputStream fileContent = filePart.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = fileContent.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();

        int userId = Integer.parseInt(request.getParameter("userId")) ;
        String newName = request.getParameter("newName");
        String newPassword = request.getParameter("newPassword");
        String currentEmail = request.getParameter("currentEmail");

        try {
                User old = User.getById(userId);
                if (newName.length() > 0) old.FullName = newName;
                if (newPassword.length() > 0) old.Password = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                if (buffer.size() > 0) old.ProfilePicture = buffer.toByteArray();
                int rowsAffected = old.update();
                if (rowsAffected > 0) {
                    request.setAttribute("success", "Successfully updated profile");
                } else {
                    request.setAttribute("error", "Something went wrong. Please try again.");
                }
        } catch (SQLException e) {
            request.setAttribute("error",e.getMessage());
        }
        response.sendRedirect("/blog/profile");
    }
}
