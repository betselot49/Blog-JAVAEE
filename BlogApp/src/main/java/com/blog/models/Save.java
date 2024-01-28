package com.blog.models;

import com.blog.config.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Save {
    public int UserId;
    public int BlogId;
    public Date CreatedAt;

    public Blog Blog;

    public User Saver;

    private static final Connection connection = DBManager.getConnection();

    public static Save build(java.sql.ResultSet result) throws Exception {
        Save save = new Save();
        save.UserId = result.getInt("UserId");
        save.BlogId = result.getInt("BlogId");
        save.CreatedAt = result.getDate("CreatedAt");
        save.Saver = User.getById(save.UserId);
        save.Blog =  com.blog.models.Blog.getById(save.BlogId);
        return save;
    }
    public static String schema(){
        String query = "CREATE TABLE IF NOT EXISTS saves(" +
                "UserId INT NOT NULL," +
                "BlogId INT NOT NULL," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY (UserId, BlogId)," +
                "FOREIGN KEY (UserId) REFERENCES users(Id) ON DELETE CASCADE," +
                "FOREIGN KEY (BlogId) REFERENCES blogs(Id) ON DELETE CASCADE" +
                ");";
        return  query;
    }

    public static ArrayList<Save> getMySaves(int userId) throws Exception {
        String query = "SELECT * FROM saves WHERE UserId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        ArrayList<Save> saves = new ArrayList<>();
        java.sql.ResultSet result = stmt.executeQuery();
        while (result.next()){
            saves.add(Save.build(result));
        }
        return saves;
    }

    public  static ArrayList<com.blog.models.Blog> getMySavedBlogs(int userId) throws Exception {
        String query = "SELECT * FROM saves WHERE UserId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        ArrayList<Blog> saves = new ArrayList<>();
        java.sql.ResultSet result = stmt.executeQuery();
        while (result.next()){
            saves.add(Save.build(result).Blog);
        }
        return saves;
    }

    public void create() throws SQLException {
        String query = "INSERT INTO saves(UserId, BlogId) VALUES(?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setInt(2, this.BlogId);
        stmt.executeUpdate();
        stmt.close();
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM saves WHERE UserId = ? AND BlogId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setInt(2, this.BlogId);
        stmt.executeUpdate();
        stmt.close();
    }

    public static boolean isSaved(int userId, int blogId) throws SQLException {
        String query = "SELECT * FROM saves WHERE UserId = ? AND BlogId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setInt(2, blogId);
        return stmt.executeQuery().next();
    }
}
