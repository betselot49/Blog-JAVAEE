package com.blog.config;


import com.blog.models.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//@WebListener
public class DBManager implements ServletContextListener {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/blogapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql";
//    private static final String PASSWORD = "241128";

    private static final Connection instance = getConnection();

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
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Failed to Load JDBC", e);
        }
    }
    public void contextInitialized(ServletContextEvent sce) {
        try  {
            System.out.println("============Creating tables==============");
            PreparedStatement user = instance.prepareStatement(User.schema());
            PreparedStatement blog = instance.prepareStatement(Blog.schema());
            PreparedStatement readingList = instance.prepareStatement(ReadingList.schema());
            PreparedStatement like = instance.prepareStatement(Like.schema());
            PreparedStatement comment = instance.prepareStatement(Comment.schema());
            PreparedStatement save = instance.prepareStatement(Save.schema());
            user.execute();
            blog.execute();
            readingList.execute();
            like.execute();
            comment.execute();
            save.execute();
            instance.close();
            System.out.println("=============Tables created.===============");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create tables.", e);
        }

    }

    public void contextDestroyed() throws SQLException {
        instance.close();
    }
}
