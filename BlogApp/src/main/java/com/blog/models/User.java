package com.blog.models;

import com.blog.config.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class User {
    public int Id;
    public String FullName;
    public  String Email;
    public String Password;
    public String Role;
    public Date CreatedAt;


    private static final Connection connection = DBManager.getConnection();

    public static  String schema(){
        String query = "CREATE TABLE IF NOT EXISTS users(" +
                "Id INT AUTO_INCREMENT PRIMARY KEY," +
                "Fullname VARCHAR(255)," +
                "Email VARCHAR(255) UNIQUE," +
                "Password VARCHAR(255)," +
                "Role Enum('admin', 'user')," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        return  query;
    }

    public static User build(ResultSet result) throws SQLException {
        User user = new User();
        user.Id = result.getInt("Id");
        user.FullName = result.getString("Fullname");
        user.Email = result.getString("Email");
        user.Password = result.getString("Password");
        user.Role = result.getString("Role");
        user.CreatedAt = result.getDate("CreatedAt");
        return user;
    }


    public int create() throws SQLException {
        String query = "INSERT INTO users(Fullname, Username, Email, Password, Role) VALUES(?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.FullName);
        stmt.setString(2, this.Email);
        stmt.setString(3, this.Password);
        stmt.setString(4, this.Role);
        return stmt.executeUpdate();
    }

    public void update() throws SQLException {
        String query = "UPDATE users SET Fullname=?, Username=?, Email=?, Password=?, Role=? WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.FullName);
        stmt.setString(2, this.Email);
        stmt.setString(3, this.Password);
        stmt.setString(4, this.Role);
        stmt.setInt(5, this.Id);
        stmt.executeUpdate();
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM users WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.Id);
        stmt.executeUpdate();
    }

    public  static ArrayList<User> search(String param) throws SQLException {
        String query = "SELECT * FROM users WHERE Fullname LIKE ? OR Username LIKE ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, "%" + param + "%");
        stmt.setString(2, "%" + param + "%");
        ResultSet result = stmt.executeQuery();
        ArrayList<User> users = new ArrayList<User>();
        while (result.next()) {
            users.add(User.build(result));
        }
        return users;
    }
    public static User getByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE Email=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            return User.build(result);
        }
        return null;
    }

public static User getById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            return User.build(result);
        }
        return null;
    }

}
