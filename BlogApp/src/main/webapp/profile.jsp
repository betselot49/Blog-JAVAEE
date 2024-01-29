<%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/3/2024
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.blog.models.User" %>
<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Base64" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>

    <title>User Profile</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .center-content {
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        .profile-card {
            border: 1px solid #dee2e6;
            border-radius: 10px;
            padding: 20px;
            margin: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .profile-picture {
            width: 200px;
            height: 200px;
            max-width: 100%; /* Ensure the image does not exceed its container */
            max-height: 100%; /* Ensure the image does not exceed its container */
            border-radius: 50%;
            object-fit: cover;
            margin-right: 20px;
        }

        .placeholder-profile {
            background-color: #f0f0f0;
            width: 200px;
            height: 200px;
            max-width: 100%; /* Ensure the image does not exceed its container */
            max-height: 100%; /* Ensure the image does not exceed its container */
            border-radius: 50%;
            object-fit: cover;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 20px;
        }

        .user-details {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        .edit-button {
            margin-top: 20px;
        }

        .blog-list {
            list-style-type: none;
            padding: 0;
        }

        .blog-item {
            background-color: #fff;
            border: 1px solid #dee2e6;
            border-radius: 5px;
            margin-bottom: 10px;
            padding: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .profile-card {
            background-color: #fff;
            border: 1px solid #dee2e6;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin: 20px;
            max-width: 1000px;
            width: 100%;
        }

        .profile-picture-container {
            width: 120px;
            height: 120px;
            overflow: hidden;
            border-radius: 50%;
            margin-right: 20px;
        }

        .profile-picture {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .user-details h4 {
            margin-bottom: 0;
        }

        .edit-button {
            margin-top: 20px;
        }

        .blog-card {
            border: 1px solid #dee2e6;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }

        .blog-card:hover {
            transform: scale(1.05);
        }

    </style>
</head>
<body class="center-content">
<%@ include file="components/header.jsp" %>

    <%
    byte[] userProfilePicture = (byte[]) request.getAttribute("userProfilePicture");
    String img = userProfilePicture != null ? Base64.getEncoder().encodeToString(userProfilePicture) : "";
%>
<div class="container text-center" style="margin-top: 40px">
    <h2 style="margin-top: 50px;">User Profile</h2>

    <div class="profile-card">
        <div class="d-flex align-items-center">
            <div class="profile-picture-container">
                <% if (userProfilePicture != null) { %>
                <img src="data:image/png;base64,<%=img%>" class="profile-picture" alt="Profile Picture">
                <% } else { %>
                <div class="placeholder-profile">
                    <!-- Placeholder content (e.g., initials or icon) -->
                    <span style="font-size: 24px; color: #555;">Placeholder</span>
                </div>
                <% } %>
            </div>

            <div class="user-details ml-4">
                <h4 class="mb-2">Name: <%=request.getAttribute("userName") %></h4>
                <p>Email: <%=request.getAttribute("userEmail") %></p>
            </div>
        </div>

        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary edit-button mt-3" data-toggle="modal" data-target="#editProfileModal">
            Edit Profile
        </button>
        <form method="post">

        </form>
    </div>


    <h3 class="mb-3" style="margin-top: 50px">Blog Posts</h3>

    <div class="row">
        <%
            User curr_user = (User) request.getAttribute("user");
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

</div>

<!-- Modal -->
<div class="modal fade" id="editProfileModal" tabindex="-1" role="dialog" aria-labelledby="editProfileModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editProfileModalLabel">Edit Profile</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Add your input fields for profile picture, name, and password here -->
                <form action="EditProfileServlet" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="userId" value="<%= curr_user.Id %>">
                    <input type="hidden" name="currentEmail" value="<%= curr_user.Email %>">
                    <div class="form-group">
                        <label for="profilePicture">Upload Profile Picture:</label>
                        <input type="file" class="form-control-file" id="profilePicture" name="profilePicture" type="image/*">
                    </div>
                    <div class="form-group">
                        <label for="newName">Change Name:</label>
                        <input type="text" class="form-control" id="newName" name="newName"
                               value="<%=request.getAttribute("userName") %>">
                    </div>
                    <div class="form-group">
                        <label for="newPassword">Change Password:</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword">
                    </div>
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Include Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
