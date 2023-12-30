package com.blog.models;

import com.blog.config.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class User {
    public int Id;
    public String Fullname;
    public String Username;
    public  String Email;
    public String Password;
    public String Role;
    public Date CreatedAt;


    private static Connection connection = DBManager.getInstance();

    public static  String schema(){
        String query = "CREATE TABLE IF NOT EXIST users(" +
                "Id INT AUTO_INCREMENT PRIMARY KEY," +
                "Fullname VARCHAR(255)," +
                "Username VARCHAR(255)," +
                "Email VARCHAR(255)," +
                "Password VARCHAR(255)," +
                "Role Enum('admin', 'user')," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        return  query;
    }

    public static User build(ResultSet result) throws SQLException {
        User user = new User();
        user.Id = result.getInt("Id");
        user.Fullname = result.getString("Fullname");
        user.Username = result.getString("Username");
        user.Email = result.getString("Email");
        user.Password = result.getString("Password");
        user.Role = result.getString("Role");
        user.CreatedAt = result.getDate("CreatedAt");
        return user;
    }


    public void create() throws SQLException {
        String query = "INSERT INTO users(Fullname, Username, Email, Password, Role) VALUES(?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.Fullname);
        stmt.setString(2, this.Username);
        stmt.setString(3, this.Email);
        stmt.setString(4, this.Password);
        stmt.setString(5, this.Role);
        stmt.executeUpdate();
    }

    public void update() throws SQLException {
        String query = "UPDATE users SET Fullname=?, Username=?, Email=?, Password=?, Role=? WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.Fullname);
        stmt.setString(2, this.Username);
        stmt.setString(3, this.Email);
        stmt.setString(4, this.Password);
        stmt.setString(5, this.Role);
        stmt.setInt(6, this.Id);
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
        var result = stmt.executeQuery();
        ArrayList<User> users = new ArrayList<User>();
        while (result.next()) {
            users.add(User.build(result));
        }
        return users;
    }
    public static User findByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE Email=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, email);
        var result = stmt.executeQuery();
        if (result.next()) {
            return User.build(result);
        }
        return null;
    }

    public static User findByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE Username=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        var result = stmt.executeQuery();
        if (result.next()) {
            return User.build(result);
        }
        return null;
    }


public static User findById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        var result = stmt.executeQuery();
        if (result.next()) {
            return User.build(result);
        }
        return null;
    }

}
