<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.blog.models.User" %>
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

        .author-info {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
            border-radius: 10px;
        }

        .author-image {
            width: 60px; /* Adjust the width of the profile picture */
            height: 60px; /* Adjust the height of the profile picture */
        }

        .see-more-content {
            overflow: hidden;
            position: relative;
            max-height: 600px; /* Adjust the initial height to show */
        }

        .see-more-btn {
            position: absolute;
            bottom: 0;
            right: 0;
            background-color: white;
            padding: 5px;
            cursor: pointer;
            text-decoration: underline;
            color: blue;
        }
    </style>
</head>
<body>
<div class="container mx-auto" style="margin-top: 25px">
    <%
        Blog blog = (Blog) request.getAttribute("blog");
        String img = Base64.getEncoder().encodeToString(blog.BlogPicture);
        String uniqueId = "blog" + blog.Id; // Create a unique identifier for each blog
    %>

    <div class="card-body" style="max-width: 1000px; background-color: #f5f5f5; border: #ececec solid 2px; border-radius: 10px">
        <div class="row">
            <div class="text-center">
                <!-- Author Information Section -->
                <div class="row author-info">
                    <% if (blog.Poster != null && blog.Poster.ProfilePicture != null) { %>
                    <img src="data:image/png;base64,<%= Base64.getEncoder().encodeToString(blog.Poster.ProfilePicture) %>" class="rounded-circle author-image" alt="Author Image">
                    <% } else { %>
                    <!-- Provide a default image or handle the case when ProfilePicture is null -->
                    <img src="" class="rounded-circle author-image" alt="Default Image">
                    <% } %>
                    <h6 class="mt-2" style="margin-left: 20px"><%= blog.Poster != null ? blog.Poster.FullName : "Unknown Author" %></h6>
                </div>
            </div>
            <div class="col-md-10">
                <!-- Blog Content Section -->
                <div class="see-more-content" id="<%= uniqueId %>">
                    <a style="text-decoration: none; color: #212121" href="blog/details?id=<%= blog.Id %>">
                        <h4 class="card-title"><%= blog.Title %></h4>
                        <img src="data:image/png;base64,<%= img %>" class="card-img-top blog-image" alt="Blog Image">
                        <pre style="margin-top: 15px; white-space: pre-line" class="card-text"><%= blog.Content %></pre>
                    </a>
                    <div class="see-more-btn" onclick="toggleContent('<%= uniqueId %>')">See More</div>
                </div>
                <div class="like-comment-row">
                    <!-- Like Count Section -->
                    <div class="like-comment-icons">
                        <i class="far fa-thumbs-up like-comment-icon"></i>
                        <span class="like-comment-count"><%= blog.LikeCount %> Likes</span>
                    </div>
                    <!-- Comment Count Section -->
                    <div class="like-comment-icons">
                        <i class="far fa-comment-alt like-comment-icon"></i>
                        <span class="like-comment-count"><%= blog.CommentCount %> Comments</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function toggleContent(uniqueId) {
        var contentDiv = document.getElementById(uniqueId);
        var btn = contentDiv.querySelector('.see-more-btn');

        if (contentDiv.style.maxHeight) {
            contentDiv.style.maxHeight = '600px';
            btn.innerText = 'See More';
        } else {
            contentDiv.style.maxHeight = contentDiv.scrollHeight + 'px';
            btn.innerText = 'See Less';
        }
    }
</script>
</body>
</html>
