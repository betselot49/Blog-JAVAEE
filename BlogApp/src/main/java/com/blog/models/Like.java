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

    public User Liker = null;


    private static final Connection connection = DBManager.getConnection();
    public static String schema(){
        String query = "CREATE TABLE IF NOT EXISTS likes(" +
                "UserId INT NOT NULL," +
                "BlogId INT NOT NULL," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY (UserId, BlogId)," +
                "FOREIGN KEY (UserId) REFERENCES users(Id) ON DELETE CASCADE," +
                "FOREIGN KEY (BlogId) REFERENCES blogs(Id) ON DELETE CASCADE" +
                ");";
        return  query;
    }

    public static Like build(java.sql.ResultSet result) throws SQLException {
        Like like = new Like();
        like.UserId = result.getInt("UserId");
        like.BlogId = result.getInt("BlogId");
        like.CreatedAt = result.getDate("CreatedAt");
        like.Liker = User.getById(like.UserId);
        return like;
    }

    public int create() throws SQLException, SQLException {
        String query = "INSERT INTO likes(UserId, BlogId) VALUES(?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setInt(2, this.BlogId);
        String query2 = "UPDATE blogs SET LikeCount = LikeCount + 1 WHERE Id = ?";
        PreparedStatement stmt2 = connection.prepareStatement(query2);
        stmt2.setInt(1, this.BlogId);
        return  stmt.executeUpdate() + stmt2.executeUpdate();
    }

    public int delete() throws SQLException {
        String query = "DELETE FROM likes WHERE UserId = ? AND BlogId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setInt(2, this.BlogId);
        String query2 = "UPDATE blogs SET LikeCount = LikeCount - 1 WHERE Id = ?";
        PreparedStatement stmt2 = connection.prepareStatement(query2);
        stmt2.setInt(1, this.BlogId);
        return  stmt.executeUpdate() + stmt2.executeUpdate();
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
        java.sql.ResultSet result = stmt.executeQuery();
        while (result.next()){
            users.add(User.getById(result.getInt("UserId")));
        }
        return users;
    }

    public static ArrayList<Blog> getLikedBlogs(int userId) throws Exception {
        String query = "SELECT * FROM likes WHERE UserId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        ArrayList<Blog> blogs = new ArrayList<>();
        java.sql.ResultSet result = stmt.executeQuery();
        while (result.next()){
            blogs.add(Blog.getById(result.getInt("BlogId")));
        }
        return blogs;
    }
}
