<%@ page import="java.util.ArrayList" %>
<%@ page import="com.blog.models.User" %>
<%@ page import="java.util.Base64" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>User List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 50px;
            text-align: center;
        }

        .user-list {
            display: block;
            justify-content: center;
            align-items: center;
        }

        .user-box {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 2px 20px 2px 20px;
            margin: 10px;
            background-color: #fff;
            text-align: left;
            max-width: 500px
        }

        .user-profile {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            object-fit: cover;
        }

        .placeholder {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background-color: #ccc;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px;
        }

        .placeholder-text {
            color: #fff;
            font-weight: bold;
        }

        .user-details {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: flex-start;

        }

        .user-name {
            margin-top: 10px;
            font-weight: bold;
        }

        .blog-count {
            color: #888;
        }

        .view-profile-button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<%@include file="components/header.jsp"%>

<div style="margin-top: 50px" class="user-list container">
    <h2>User List</h2>
    <%
        ArrayList<User> peoples = (ArrayList<User>) request.getAttribute("peoples");
        User currentUser = (User) request.getSession().getAttribute("user");
        for (User people : peoples) {
    %>
    <a href="/blog/profile?Id=<%=people.Id%>" style="color: black; text-decoration: none">
        <div class="user-box">
            <% if (people.ProfilePicture != null) { %>
            <img class="user-profile" src="data:image/jpeg;base64,<%= new String(Base64.getEncoder().encode(people.ProfilePicture)) %>" alt="Profile Picture">
            <% } else { %>
            <div class="placeholder">
                <div class="placeholder-text">Placeholder</div>
            </div>
            <% } %>
            <div class="user-details">
                <p class="user-name"><strong><%= people.FullName %></strong></p>
                <p class="blog-count"><%= people.BlogCount %> Blogs Posted</p>
                <% if(currentUser != null && currentUser.Id == people.Id) { %>
                <!-- Only show the "View Profile" button if the user ID matches -->
                <form action="ViewProfileServlet" method="get">
                    <input type="hidden" name="userId" value="<%= people.Id %>">
                    <button class="view-profile-button" type="submit">View Profile</button>
                </form>
                <% } %>
            </div>
        </div>
    </a>

    <% } %>
</div>
</body>
</html>
