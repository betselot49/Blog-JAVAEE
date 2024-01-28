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

    private static final Connection connection = DBManager.getConnection();

    public static String schema(){
        String query = "CREATE TABLE IF NOT EXISTS readinglists(" +
                "Id INT AUTO_INCREMENT PRIMARY KEY," +
                "UserId INT NOT NULL," +
                "BlogCount INT DEFAULT 0," +
                "Name VARCHAR(255) NOT NULL," +
                "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (UserId) REFERENCES users(Id) ON DELETE CASCADE" +
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

    public int create() throws Exception {
        String query = "INSERT INTO readinglists(UserId, Name) VALUES(?,?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.UserId);
        stmt.setString(2, this.Name);
        return stmt.executeUpdate();
    }

    public int update() throws Exception {
        String query = "UPDATE readinglists SET Name=? WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, this.Name);
        stmt.setInt(2, this.Id);
        return  stmt.executeUpdate();
    }

    public int delete() throws Exception {
        String query = "DELETE FROM readinglists WHERE Id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, this.Id);
        return  stmt.executeUpdate();
    }


    public static ArrayList<ReadingList> getMyReadingList(int userId) throws Exception {
        String query = "SELECT * FROM readinglists WHERE UserId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet result2 = stmt.executeQuery();
        ArrayList<ReadingList> readingLists = new ArrayList<>();
        while (result2.next()) {
            readingLists.add(ReadingList.build(result2));
        }
        stmt.close();
        return readingLists;
    }

}
