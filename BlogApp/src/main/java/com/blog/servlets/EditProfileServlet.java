package com.blog.servlets;

import com.blog.models.User;
import com.blog.config.DBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=========EditProfile Servlet==============");

        // Retrieve form data
        String userIdParam = request.getParameter("userId");
        int userId = userIdParam != null ? Integer.parseInt(userIdParam) : 0;

        String newName = request.getParameter("newName");
        String newPassword = request.getParameter("newPassword");
        String currentEmail = request.getParameter("currentEmail");

        User user = new User();
        user.Id = userId;
        user.FullName = newName;
        user.Email = currentEmail; // Use the current email to uniquely identify the user
        user.Password = newPassword;

        System.out.println("User ID: " + user.Id);
        System.out.println("New Name: " + user.FullName + newName);
        System.out.println("New Password: " + user.Password + newPassword);

        // Database update logic
        try (Connection connection = DBManager.getConnection()) {
            String sql = "UPDATE users SET Fullname = ?, Password = ? WHERE Id = ?";
            System.out.println("SQL Query: " + sql); // Print the SQL query to the console
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.FullName);
                preparedStatement.setString(2, user.Password);
                preparedStatement.setInt(3, user.Id);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    response.sendRedirect("profile.jsp");
                } else {
                    // Handle the case where the update in the database fails
                    System.out.println("No rows affected!");
                    response.sendRedirect("error.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the database connection or query execution exception
            response.sendRedirect("error.jsp");
        }
    }
}
