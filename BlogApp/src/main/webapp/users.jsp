<%@ page import="java.util.ArrayList" %>
<%@ page import="com.blog.models.User" %>
<%@ page import="java.util.Base64" %>
<%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/4/2024
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>User List</title>
    <style>
        body {
            padding-top: 50px;
            text-align: center;
        }

        .user-list {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            flex-direction: column;
            align-items: center;
        }

        .user-box {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 10px;
            margin: 10px;
            background-color: #fff;
            text-align: center;
            width: 500px;
            display: flex;
            justify-content: space-around;
            align-items: center;
            gap: 5px;
        }

        .user-profile {
            width: 100px;
            height: 100px;
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
            /*margin-left: 65px;*/
        }

        .placeholder-text {
            color: #fff;
            font-weight: bold;
        }

        .user-details {
            margin-top: 10px;
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: space-around;
        }

        .view-profile-button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
        }

        .user-name,.user-email {
            padding-left: 10px;
        }
    </style>
</head>
<body>
<%@include file="components/header.jsp"%>

<div style="margin-top: 50px" class="user-list">
    <h2>User List</h2>
    <%
        ArrayList<User> peoples = (ArrayList<User>) request.getAttribute("peoples");
        User currentUser = (User) request.getSession().getAttribute("user");
        for (User people : peoples) {
    %>
    <a class="user-box" href="/blog/users/details?id=<%=people.Id%>">
        <% if (people.ProfilePicture != null) { %>
        <img class="user-profile" src="data:image/jpeg;base64,<%= new String(Base64.getEncoder().encode(people.ProfilePicture)) %>" alt="Profile Picture">
        <% } else { %>
        <div class="placeholder">
            <div class="placeholder-text">Placeholder</div>
        </div>
        <% } %>
        <div class="user-details">
            <p class="user-name"><strong>Name:  </strong> <%= people.FullName %></p>
            <p class="user-email" ><strong>Email:  </strong> <%= people.Email %></p>
            <% if(currentUser != null && currentUser.Id == people.Id) { %>
            <!-- Only show the "View Profile" button if the user ID matches -->
            <form action="ViewProfileServlet" method="get">
                <input type="hidden" name="userId" value="<%= people.Id %>">
                <button class="view-profile-button" type="submit">View Profile</button>
            </form>
            <% } %>
        </div>
    </a>
    <% } %>
</div>
</body>
</html>
