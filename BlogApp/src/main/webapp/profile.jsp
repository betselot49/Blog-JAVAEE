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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>User Profile</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        .center-content {
            display: flex;
            /*align-items: center;*/
            justify-content: center;
            height: 100vh;
            padding-top: 2rem;
        }


        .profile-card {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            background-color: #fff;
            width: 70%;
            margin-left: 15%;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .profile-picture {
            width: 200px;
            height: 200px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 20px;
        }

        .placeholder-profile {
            background-color: #f0f0f0;
            width: 200px;
            height: 200px;
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
            /*position: absolute;*/
            bottom: 10px;
            right: 10px;
        }

        .modal-dialog {
            max-width: 600px;
        }
        .wrapper{
            display: flex;
            align-items: center;
            justify-content: space-around;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        ul li {
            margin-bottom: 10px;
        }

    </style>
</head>
<body class="center-content">
<%
    byte[] userProfilePicture = (byte[]) request.getAttribute("userProfilePicture");
    String img = userProfilePicture != null ? Base64.getEncoder().encodeToString(userProfilePicture) : "";
%>
<div class="container text-center">
    <h2>User Profile</h2>

    <div class="profile-card">
        <div class="wrapper">
            <div class="profile-picture">
                <% if (userProfilePicture != null) { %>
                <img src="data:image/png;base64,<%=img%>" alt="Profile Picture">
                <% } else { %>
                <div class="placeholder-profile">
                    <!-- Placeholder content (e.g., initials or icon) -->
                    <span style="font-size: 24px; color: #555;">Placeholder</span>
                </div>
                <% } %>
            </div>

            <div class="user-details">
                <label>Name: <%=request.getAttribute("userName") %></label><br>
                <label>Email: <%=request.getAttribute("userEmail") %></label><br>
            </div>
        </div>

        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary edit-button" data-toggle="modal" data-target="#editProfileModal">
            Edit Profile
        </button>
    </div>

    <h3 class="mt-3">Your Blog Posts:</h3>
    <ul>
        <%
            User user = (User) request.getAttribute("user");
            ArrayList<Blog> userBlogs = (ArrayList<Blog>) request.getAttribute("blogs");

            if (userBlogs != null) {
                for (Blog blog : userBlogs) {
        %>
        <li><%= blog.Title %></li>
        <%
            }
        } else {
        %>
        <li>No blogs available</li>
        <%
            }
        %>

    </ul>
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
                    <input type="hidden" name="userId" value="<%= user.Id %>">
                    <input type="hidden" name="currentEmail" value="<%= user.Email %>">
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
                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
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







