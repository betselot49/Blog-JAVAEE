package com.blog.models;

import com.blog.config.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Like {
    public int UserId;
    public int BlogId;

    public Date CreatedAt;


    private static Connection connection = DBManager.getInstance();
    public static String schema(){
        String query = "CREATE TABLE IF NOT EXIST likes(" +
                "UserId INT," +
                "BlogId INT," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY (UserId, BlogId)," +
                "FOREIGN KEY (UserId) REFERENCES users(UserId) ON DELETE CASCADE," +
                "FOREIGN KEY (BlogId) REFERENCES blogs(BlogId) ON DELETE CASCADE" +
                ");";
        return  query;
    }

    public void create() throws SQLException, SQLException {
        String query = "INSERT INTO likes(UserId, BlogId) VALUES(?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setInt(2, this.BlogId);
        stmt.executeUpdate();
        String query2 = "UPDATE blogs SET LikeCount = LikeCount + 1 WHERE BlogId = ?";
        PreparedStatement stmt2 = connection.prepareStatement(query2);
        stmt2.setInt(1, this.BlogId);
        stmt2.executeUpdate();
        stmt.close();
        stmt2.close();
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM likes WHERE UserId = ? AND BlogId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setInt(2, this.BlogId);
        stmt.executeUpdate();
        String query2 = "UPDATE blogs SET LikeCount = LikeCount - 1 WHERE BlogId = ?";
        PreparedStatement stmt2 = connection.prepareStatement(query2);
        stmt2.setInt(1, this.BlogId);
        stmt2.executeUpdate();
        stmt2.close();
        stmt.close();
    }
    public static boolean isLiked(int userId, int blogId) throws SQLException {
        String query = "SELECT * FROM likes WHERE UserId = ? AND BlogId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setInt(2, blogId);
        return stmt.executeQuery().next();
    }

    public static ArrayList<User> getLikers(int blogId) throws SQLException{
        String query = "SELECT * FROM likes WHERE BlogId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, blogId);
        ArrayList<User> users = new ArrayList<>();
        var result = stmt.executeQuery();
        while (result.next()){
            users.add(User.findById(result.getInt("UserId")));
        }
        return users;
    }

    public static ArrayList<Blog> getLikedBlogs(int userId) throws Exception {
        String query = "SELECT * FROM likes WHERE UserId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        ArrayList<Blog> blogs = new ArrayList<>();
        var result = stmt.executeQuery();
        while (result.next()){
            blogs.add(Blog.getById(result.getInt("BlogId")));
        }
        return blogs;
    }
}
