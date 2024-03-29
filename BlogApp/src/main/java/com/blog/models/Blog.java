package com.blog.models;

import com.blog.config.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Blog {
    public int Id;
    public String Content;

    public String Title;

    public byte[] BlogPicture = null;
    public int UserId;

    public String Tags;
    public int LikeCount = 0;
    public int CommentCount = 0;

    public Date CreatedAt;

    public User Poster = null;

    private static Connection connection = DBManager.getConnection();


    public static String schema(){
        String query = "CREATE TABLE IF NOT EXISTS blogs (" +
                "Id INT AUTO_INCREMENT PRIMARY KEY," +
                "Content TEXT NOT NULL," +
                "UserId INT NOT NULL," +
                "Tags VARCHAR(255)," +
                "LikeCount INT DEFAULT 0," +
                "Title VARCHAR(255) NOT NULL," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "CommentCount INT DEFAULT 0," +
                "BlogPicture LONGBLOB," +
                "FOREIGN KEY (UserId) REFERENCES users(Id) ON DELETE CASCADE" +
                ");";
        return query;
    }



    public static Blog build(ResultSet result) throws Exception {
        Blog blog = new Blog();
        blog.Id = result.getInt("Id");
        blog.Title = result.getString("Title");
        blog.Content = result.getString("Content");
        blog.UserId = result.getInt("UserId");
        blog.LikeCount = result.getInt("LikeCount");
        blog.CommentCount = result.getInt("CommentCount");
        blog.CreatedAt = result.getDate("CreatedAt");
        blog.Poster = User.getById(blog.UserId);
        if (result.getString("Tags") != null)  blog.Tags = result.getString("Tags");
        if (result.getBytes("BlogPicture") != null) blog.BlogPicture = result.getBytes("BlogPicture");
        return blog;
    }


    public int create() throws Exception {
        String query = "INSERT INTO blogs(Title, Content, UserId, Tags, LikeCount, CommentCount, BlogPicture) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.Title);
        stmt.setString(2, this.Content);
        stmt.setInt(3, this.UserId);
        stmt.setString(4, this.Tags);
        stmt.setInt(5, this.LikeCount);
        stmt.setInt(6, this.CommentCount);
        stmt.setBytes(7, this.BlogPicture);
        return stmt.executeUpdate();
    }


    public int update() throws Exception {
        String query = "UPDATE blogs SET Title=?, Content=?, Tags=?, BlogPicture=? WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.Title);
        stmt.setString(2, this.Content);
        stmt.setString(3, String.join(",", this.Tags));
        stmt.setBytes(4, this.BlogPicture);
        stmt.setInt(5, this.Id);
        return stmt.executeUpdate();
    }

    public int delete() throws Exception {
        String query = "DELETE FROM blogs WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.Id);
        return  stmt.executeUpdate();
    }

    public static Blog getById(int id) throws Exception {
        String query = "SELECT * FROM blogs WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        Blog blog = null;
        if (result.next()) {
            return Blog.build(result);
        }
        stmt.close();
        return null;
    }

    public static ArrayList<Blog> getByUserId(int userId) throws Exception {
        String query = "SELECT * FROM blogs WHERE UserId=? ORDER BY CreatedAt DESC";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet result = stmt.executeQuery();
        ArrayList<Blog> blogs = new ArrayList<>();
        while (result.next()) {
            blogs.add(Blog.build(result));
        }
        stmt.close();
        return blogs;
    }


    public static ArrayList<Blog> getAll() throws Exception {
        String query = "SELECT * FROM blogs ORDER BY CreatedAt DESC";
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);
        ArrayList<Blog> blogs = new ArrayList<>();
        while (result.next()) {
            blogs.add(Blog.build(result));
        }
        stmt.close();
        return blogs;
    }

    public static ArrayList<Blog> search(String param) throws Exception {
        System.out.println("=========Blog Model Search==============");
        String query = "SELECT * FROM blogs WHERE Content LIKE ? OR Tags LIKE ? ORDER BY CreatedAt DESC";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, "%" + param + "%");
        stmt.setString(2, "%" + param + "%");
        ResultSet result = stmt.executeQuery();
        ArrayList<Blog> blogs = new ArrayList<>();
        while (result.next()) {
            blogs.add(Blog.build(result));
        }
        stmt.close();
        return blogs;
    }

    public static ArrayList<Blog> getByTag(String tag) throws Exception {
        String query = "SELECT * FROM blogs WHERE Tags LIKE ? ORDER BY CreatedAt DESC";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, "%" + tag + "%");
        ResultSet result = stmt.executeQuery();
        ArrayList<Blog> blogs = new ArrayList<>();
        while (result.next()) {
            blogs.add(Blog.build(result));
        }
        stmt.close();
        return blogs;
    }



}
