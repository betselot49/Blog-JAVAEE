<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/8/2024
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog View</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            padding: 20px;
        }

        .blog-image {
            height: 400px;
            object-fit: contain;
            width: 100%;
        }

        .card {
            max-width: 750px;
            margin: 0 auto;
        }

        .like-comment-row {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 10px;
        }

        .like-comment-icons {
            display: flex;
            align-items: center;
        }

        .like-comment-icon {
            margin-right: 8px;
            color: #888;
        }

        .like-comment-count {
            color: #888;
        }
    </style>
</head>
<body>
<div class="container">
    <%
        Blog blog = (Blog) request.getAttribute("blog");
        String img = Base64.getEncoder().encodeToString(blog.BlogPicture);
    %>
    <a class="card" href="blog/<%=blog.Id%>">
        <div class="card-body">

            <h2 class="card-title"><%= blog.Title %></h2>
            <img src="data:image/png;base64,<%= img %>" class="card-img-top blog-image" alt="Blog Image">
            <p class="card-text"><%= blog.Content %></p>
            <div class="like-comment-row">
                <div class="like-comment-icons">
                    <i class="far fa-thumbs-up like-comment-icon"></i>
                    <span class="like-comment-count"><%= blog.LikeCount %> Likes</span>
                </div>
                <div class="like-comment-icons">
                    <i class="far fa-comment-alt like-comment-icon"></i>
                    <span class="like-comment-count"><%= blog.CommentCount %> Comments</span>
                </div>
            </div>
        </div>
    </a>
</div>
