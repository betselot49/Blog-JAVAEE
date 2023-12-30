package com.blog.models;

import com.blog.config.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class ReadingList {
    public int Id;
    public int UserId;
    public int BlogCount = 0;
    public  String Name;
    public Date CreatedAt;

    private static Connection connection = DBManager.getInstance();

    public static String schema(){
        String query = "CREATE TABLE IF NOT EXIST readinglists(" +
                "Id INT AUTO_INCREMENT PRIMARY KEY," +
                "UserId INT," +
                "BlogCount INT," +
                "Name VARCHAR(255)," +
                "CreatedAt DATE," +
                "FOREIGN KEY (UserId) REFERENCES users(UserId) ON DELETE CASCADE" +
                ");";
        return  query;
    }

    public static ReadingList build(ResultSet result) throws Exception {
        ReadingList readingList = new ReadingList();
        readingList.Id = result.getInt("Id");
        readingList.UserId = result.getInt("UserId");
        readingList.BlogCount = result.getInt("BlogCount");
        readingList.Name = result.getString("Name");
        readingList.CreatedAt = result.getDate("CreatedAt");
        return readingList;
    }

    public void create() throws Exception {
        String query = "INSERT INTO readinglists(UserId, Name) VALUES(?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setString(2, this.Name);
        stmt.executeUpdate();
        stmt.close();
    }

    public void update() throws Exception {
        String query = "UPDATE readinglists SET Name=? WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.Name);
        stmt.setInt(2, this.Id);
        stmt.executeUpdate();
        stmt.close();
    }

    public void delete() throws Exception {
        String query = "DELETE FROM readinglists WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.Id);
        stmt.executeUpdate();
        stmt.close();
    }

    public static ArrayList<Blog> getBlogs(int readingListId) throws Exception {
        String query = "SELECT * FROM blogs WHERE Id IN (SELECT BlogId FROM readinglistblogs WHERE ReadingListId=?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, readingListId);
        var result2 = stmt.executeQuery();
        ArrayList<Blog> blogs = new ArrayList<>();
        while (result2.next()) {
            blogs.add(Blog.build(result2));
        }
        stmt.close();
        return blogs;
    }
}
