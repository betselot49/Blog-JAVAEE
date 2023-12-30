package com.blog.config;


import com.blog.models.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.*;


@WebListener
public class DBManager implements ServletContextListener {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/blogapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "241128";

    private static Connection instance = (new DBManager()).getConnection();

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver. Make sure the driver JAR is in the classpath.", e);
        }
    }


    public static Connection getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to Load JDBC", e);
        }
    }
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("============Creating tables==============");
        try  {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement user = connection.prepareStatement(User.schema());
            PreparedStatement blog = connection.prepareStatement(Blog.schema());
            PreparedStatement readingList = connection.prepareStatement(ReadingList.schema());
            PreparedStatement like = connection.prepareStatement(Like.schema());
            PreparedStatement comment = connection.prepareStatement(Comment.schema());
            PreparedStatement save = connection.prepareStatement(Save.schema());
            user.execute();
            blog.execute();
            readingList.execute();
            like.execute();
            comment.execute();
            save.execute();
            connection.close();
            System.out.println("=============Tables created.===============");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create tables.", e);
        }

    }

    public void contextDestroyed() throws SQLException {
        instance.close();
    }
}