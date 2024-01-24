package com.blog.models;

import com.blog.config.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Comment {
    public int Id;
    public int UserId;
    public int BlogId;
    public String Content;
    public Date CreatedAt;
    public User Commenter = null;
    private static final Connection connection = DBManager.getConnection();
    public  static String schema(){
        String query = "CREATE TABLE IF NOT EXISTS comments (" +
                "Id INT AUTO_INCREMENT PRIMARY KEY," +
                "UserId INT NOT NULL," +
                "BlogId INT NOT NULL," +
                "Content TEXT NOT NULL," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (UserId) REFERENCES users(Id) ON DELETE CASCADE," +
                "FOREIGN KEY (BlogId) REFERENCES blogs(Id) ON DELETE CASCADE" +
                ");";
        return  query;
    }

    public static Comment build(ResultSet result) throws SQLException {
        Comment comment = new Comment();
        comment.Id = result.getInt("Id");
        comment.UserId = result.getInt("UserId");
        comment.BlogId = result.getInt("BlogId");
        comment.Content = result.getString("Content");
        comment.CreatedAt = result.getDate("CreatedAt");
        comment.Commenter = User.getById(comment.UserId);
        return comment;
    }
    public int create() throws SQLException {
        String query = "INSERT INTO comments(UserId, BlogId, Content) VALUES(?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setInt(2, this.BlogId);
        stmt.setString(3, this.Content);
        stmt.executeUpdate();
        String query2 = "UPDATE blogs SET CommentCount = CommentCount + 1 WHERE BlogId = ?";
        PreparedStatement stmt2 = connection.prepareStatement(query2);
        stmt2.setInt(1, this.BlogId);
        return  stmt2.executeUpdate();
    }

    public void update() throws SQLException {
        String query = "UPDATE comments SET Content=? WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.Content);
        stmt.setInt(2, this.Id);
        stmt.executeUpdate();
        stmt.close();
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM comments WHERE Id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.Id);
        stmt.executeUpdate();
        String query2 = "UPDATE blogs SET CommentCount = CommentCount - 1 WHERE BlogId = ?";
        PreparedStatement stmt2 = connection.prepareStatement(query2);
        stmt2.setInt(1, this.BlogId);
        stmt2.executeUpdate();
        stmt2.close();
        stmt.close();
    }

    public static ArrayList<Comment> getComments(int blogId) throws SQLException {
        String query = "SELECT * FROM comments WHERE BlogId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, blogId);
        ArrayList<Comment> comments = new ArrayList<>();
        ResultSet result = stmt.executeQuery();
        while (result.next()){
            comments.add(build(result));
        }
        return comments;
    }


}
