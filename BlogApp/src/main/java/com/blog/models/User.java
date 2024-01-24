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
    public byte[] ProfilePicture;
    public String Role;

    public int BlogCount = 0;
    public Date CreatedAt;


    private static final Connection connection = DBManager.getConnection();

    public static  String schema(){
        String query = "CREATE TABLE IF NOT EXISTS users(" +
                "Id INT AUTO_INCREMENT PRIMARY KEY," +
                "Fullname VARCHAR(255) NOT NULL," +
                "Email VARCHAR(255) NOT NULL UNIQUE," +
                "ProfilePicture LONGBLOB," +
                "Password VARCHAR(255) NOT NULL," +
                "Role ENUM('admin', 'user') DEFAULT 'user'," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "BlogCount INT DEFAULT 0" +
                ");";
        return  query;
    }

    public static User build(ResultSet result) throws SQLException {
        User user = new User();
        user.Id = result.getInt("Id");
        user.FullName = result.getString("Fullname");
        user.Email = result.getString("Email");
        user.Password = result.getString("Password");
        if(result.getBytes("ProfilePicture") != null)  user.ProfilePicture = result.getBytes("ProfilePicture");
        user.Role = result.getString("Role");
        user.BlogCount = result.getInt("BlogCount");
        user.CreatedAt = result.getDate("CreatedAt");

        return user;
    }


    public int create() throws SQLException {
        String query = "INSERT INTO users(Fullname, Email, Password, Role, ProfilePicture) VALUES(?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.FullName);
        stmt.setString(2, this.Email);
        stmt.setString(3, this.Password);
        stmt.setString(4, this.Role);
        stmt.setBytes(5, this.ProfilePicture);
        return stmt.executeUpdate();
    }

    public int update() throws SQLException {
        String query = "UPDATE users SET Fullname=?, Email=?, Password=?, ProfilePicture=?  WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.FullName);
        stmt.setString(2, this.Email);
        stmt.setString(3, this.Password);
        stmt.setBytes(4, this.ProfilePicture);
        stmt.setInt(5, this.Id);
        return  stmt.executeUpdate();
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM users WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.Id);
        stmt.executeUpdate();
    }

    public  static ArrayList<User> search(String search, int pageNumber, int pageSize) throws SQLException {
        String query = "SELECT * FROM users WHERE Fullname LIKE ? OR Email LIKE ? LIMIT ? OFFSET ? ORDER BY BlogCount DESC";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, "%" + search + "%");
        stmt.setString(2, "%" + search + "%");
        ResultSet result = stmt.executeQuery();
        ArrayList<User> users = new ArrayList<User>();
        while (result.next()) {
            users.add(User.build(result));
        }
        System.out.print(users.size() + "=====================");
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
