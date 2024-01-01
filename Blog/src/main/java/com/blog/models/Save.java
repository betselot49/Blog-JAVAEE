package com.blog.models;

import com.blog.config.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Save {
    public int ReadingListId;
    public int UserId;
    public int BlogId;
    public Date CreatedAt;

    private static Connection connection = DBManager.instance;

    public static String schema(){
        String query = "CREATE TABLE IF NOT EXISTS saves(" +
                "ReadingListId INT," +
                "UserId INT," +
                "BlogId INT," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY (ReadingListId, UserId, BlogId)," +
                "FOREIGN KEY (ReadingListId) REFERENCES readinglists(Id) ON DELETE CASCADE," +
                "FOREIGN KEY (UserId) REFERENCES users(Id) ON DELETE CASCADE," +
                "FOREIGN KEY (BlogId) REFERENCES blogs(Id) ON DELETE CASCADE" +
                ");";
        return  query;
    }

    public void create() throws SQLException {
        String query = "INSERT INTO saves(ReadingListId, UserId, BlogId) VALUES(?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.ReadingListId);
        stmt.setInt(2, this.UserId);
        stmt.setInt(3, this.BlogId);
        stmt.executeUpdate();
        stmt.close();
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM saves WHERE ReadingListId = ? AND UserId = ? AND BlogId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.ReadingListId);
        stmt.setInt(2, this.UserId);
        stmt.setInt(3, this.BlogId);
        stmt.executeUpdate();
        stmt.close();
    }

    public static boolean isSaved(int readingListId, int userId, int blogId) throws SQLException {
        String query = "SELECT * FROM saves WHERE ReadingListId = ? AND UserId = ? AND BlogId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, readingListId);
        stmt.setInt(2, userId);
        stmt.setInt(3, blogId);
        return stmt.executeQuery().next();
    }
}
