package com.blog.config;


import com.blog.models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBManager {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/blogapp";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "241128";
	public static Connection connection;

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver. Make sure the driver JAR is in the classpath.", e);
        }
    }

//	public static void main(String[] args) {
//		contextInitialized();
//	}

    public static Connection getConnection() {
        try {
			if (connection == null || connection.isClosed())
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        	return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create employee table.", e);
        }
    }
	public static void contextInitialized() {
		try  {
			System.out.println("============Creating tables==============");
			PreparedStatement user = connection.prepareStatement(User.schema());
			PreparedStatement blog = connection.prepareStatement(Blog.schema());
			PreparedStatement readingList = connection.prepareStatement(ReadingList.schema());
			PreparedStatement like = connection.prepareStatement(Like.schema());
			PreparedStatement comment = connection.prepareStatement(Comment.schema());
			PreparedStatement save = connection.prepareStatement(Save.schema());
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
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}

