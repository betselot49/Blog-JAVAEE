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

    public int ReadingListId;
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
                "ReadingListId INT NOT NULL," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY (UserId, BlogId, ReadingListId)," +
                "FOREIGN KEY (UserId) REFERENCES users(Id) ON DELETE CASCADE," +
                "FOREIGN KEY (ReadingListId) REFERENCES readinglists(Id) ON DELETE CASCADE," +
                "FOREIGN KEY (BlogId) REFERENCES blogs(Id) ON DELETE CASCADE" +
                ");";
        return  query;
    }

    public static ArrayList<Save> getMySaves(int userId) throws Exception {
        String query = "SELECT * FROM saves WHERE UserId = ? ORDER BY CreatedAt DESC";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        ArrayList<Save> saves = new ArrayList<>();
        java.sql.ResultSet result = stmt.executeQuery();
        while (result.next()){
            saves.add(Save.build(result));
        }
        return saves;
    }

    public  static ArrayList<com.blog.models.Blog> getMySavedBlogs(int userId, int readingListId) throws Exception {
        String query = "SELECT * FROM saves WHERE UserId = ? AND ReadingListId = ? ORDER BY CreatedAt DESC";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setInt(2, readingListId);
        ArrayList<Blog> saves = new ArrayList<>();
        java.sql.ResultSet result = stmt.executeQuery();
        while (result.next()){
            saves.add(Save.build(result).Blog);
        }
        return saves;
    }

    public int create() throws SQLException {
        String query = "INSERT INTO saves(UserId, BlogId, ReadingListId) VALUES(?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setInt(2, this.BlogId);
        stmt.setInt(3, this.ReadingListId);
        String query2 = "UPDATE readinglists SET BlogCount = BlogCount + 1 WHERE Id = ?";
        PreparedStatement stmt2 = connection.prepareStatement(query2);
        stmt2.setInt(1, this.ReadingListId);
        return  stmt.executeUpdate() + stmt2.executeUpdate();
    }

    public int delete() throws SQLException {
        String query = "DELETE FROM saves WHERE UserId = ? AND BlogId = ? AND ReadingListId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setInt(2, this.BlogId);
        stmt.setInt(3, this.ReadingListId);
        String query2 = "UPDATE readinglists SET BlogCount = BlogCount - 1 WHERE Id = ?";
        PreparedStatement stmt2 = connection.prepareStatement(query2);
        stmt2.setInt(1, this.ReadingListId);
        return  stmt.executeUpdate() + stmt2.executeUpdate();
    }

    public static ArrayList<Blog> getBlogs(int readingListId) throws Exception {
        String query = "SELECT * FROM blogs WHERE Id IN (SELECT BlogId FROM saves WHERE ReadingListId=? ORDER BY CreatedAt DESC)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, readingListId);
        ResultSet result2 = stmt.executeQuery();
        ArrayList<Blog> blogs = new ArrayList<>();
        while (result2.next()) {
            blogs.add(com.blog.models.Blog.build(result2));
        }
        stmt.close();
        return blogs;
    }

    public static boolean isSaved(int userId, int blogId, int readingListId) throws SQLException {
        String query = "SELECT * FROM saves WHERE UserId = ? AND BlogId = ? AND ReadingListId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setInt(2, blogId);
        stmt.setInt(3, readingListId);
        return stmt.executeQuery().next();
    }

}
