package com.blog.config;


import com.blog.models.*;
import jakarta.servlet.ServletContextListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DBManager{
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/blogapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "241128";
	public static final Connection instance = getConnection();

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver. Make sure the driver JAR is in the classpath.", e);
        }
    }

	public static void main(String[] args) {
		contextInitialized();
	}

    public static Connection getConnection() {
        try {
        	return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create employee table.", e);
        }
    }
	public static void contextInitialized() {
		try  {
			System.out.println("============Creating tables==============");
			PreparedStatement user = instance.prepareStatement(User.schema());
			PreparedStatement blog = instance.prepareStatement(Blog.schema());
			PreparedStatement readingList = instance.prepareStatement(ReadingList.schema());
			PreparedStatement like = instance.prepareStatement(Like.schema());
			PreparedStatement comment = instance.prepareStatement(Comment.schema());
			PreparedStatement save = instance.prepareStatement(Save.schema());
			user.execute();
			System.out.println("============User table==============");
			blog.execute();
			System.out.println("============Blog table==============");
			comment.execute();
			System.out.println("============Comment table==============");
			like.execute();
			System.out.println("============Like table==============");
			readingList.execute();
			System.out.println("============Reading List table==============");
			save.execute();
			System.out.println("============Save table==============");

			System.out.println("=============Tables created.===============");
		} catch (Exception e) {
			throw new RuntimeException("Failed to create tables.", e);
		}

	}

	public static void contextDestroyed() {
		try {
			instance.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}

