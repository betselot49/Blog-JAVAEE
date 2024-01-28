<%@ page import="com.blog.models.User" %>
<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/4/2024
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3 class="mb-3" style="margin-top: 50px">Your Blog Posts</h3>

<div class="row">
    <%
        User user = (User) request.getAttribute("user");
        ArrayList<Blog> userBlogs = (ArrayList<Blog>) request.getAttribute("blogs");
        if (userBlogs != null && !userBlogs.isEmpty()) {
            for (Blog blog : userBlogs) {
    %>
    <div class="col-md-4">
        <div class="card blog-card mb-4" style="height: 500px">
            <img src="data:image/png;base64,<%= Base64.getEncoder().encodeToString(blog.BlogPicture) %>" class="card-img-top" alt="Blog Image">
            <div class="card-body">
                <h5 class="card-title"><%= blog.Title %></h5>
                <p class="card-text align-text-top"><%= truncateText(blog.Content, 150) %></p>
                <a href="blog/details?id=<%= blog.Id %>" class="btn btn-primary">Read More</a>
            </div>
        </div>
    </div>
    <%!
        public static String truncateText(String text, int maxLength) {
            if (text.length() > maxLength) {
                return text.substring(0, maxLength - 3) + "...";
            } else {
                return text;
            }
        }
    %>


    <%
        }
    } else {
    %>
    <div class="col-12">
        <p class="text-muted">No blogs available.</p>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
