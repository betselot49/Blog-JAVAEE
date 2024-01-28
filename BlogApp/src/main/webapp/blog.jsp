<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.blog.models.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Details</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
        }

        .card {
            margin-top: 20px;
        }

        .blog-image {
            height: 400px;
            object-fit: cover;
            width: 100%;
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

        form {
            margin-top: 20px;
        }

        textarea {
            width: 100%;
            margin-top: 10px;
            resize: vertical;
        }

        button[type="submit"] {
            margin-top: 10px;
        }

        .comment, .like {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<%@ include file="components/header.jsp" %>

<% if (request.getAttribute(("error")) != null) { %>
<div class="container" style="margin-top: 70px">
    <p class="alert alert-danger"><%= request.getAttribute("error") %></p>
</div>
<% } else{
    Blog blog = (Blog) request.getAttribute("blog");
    boolean isLiked = (boolean) request.getAttribute("isLiked");
    ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
    ArrayList<Like> likes = (ArrayList<Like>) request.getAttribute("likes");
%>
<div class="container" style="margin-top: 70px">
    <div class="card">
        <div class="card-body">
            <div href="blog/<%=blog.Id%>">
                <h2 class="card-title"><%= blog.Title %></h2>
                <img src="data:image/png;base64,<%= Base64.getEncoder().encodeToString(blog.BlogPicture) %>"
                     class="card-img-top blog-image" alt="Blog Image">
                <p class="card-text"><%= blog.Content %></p>
            </div>
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
    </div>


    <% if (request.getAttribute("user") != null) { %>
    <div class="row">
        <form method="POST" action="/blog/like" class="like-form mx-3">
            <input name="blogId" type="hidden" value=<%=blog.Id%>>
            <input name="userId" type="hidden" value=<%=user.Id%>>
            <% String msg = isLiked ? "Dislike" : "Like"; %>
            <button type="submit" class="btn btn-primary"><%=msg%></button>
        </form>
<%--        <form method="POST" action="/blog/library" class="library-form mx-3">--%>
<%--            <input name="blogId" type="hidden" value="<%=blog.Id%>">--%>
<%--            <input name="userId" type="hidden" value=<%=user.Id%>>--%>
<%--            --%>
<%--        </form>--%>

        <% if (user != null && (user.Id == blog.UserId || user.Role.equals("admin"))) { %>
        <form method="POST" action="/blog/blog/details" class="delete-blog-form mx-3">
            <input name="blogId" type="hidden" value="<%=blog.Id%>">
            <input name="method" type="hidden" value="delete">
            <button type="submit" class="btn btn-danger">Delete</button>
        </form>
        <% } %>

    </div>


    <br/>


    <form method="POST" action="/blog/library/details" class="library-form mx-3">
        <input name="blogId" type="hidden" value="<%= blog.Id %>">
        <input name="userId" type="hidden" value="<%= user.Id %>">
        <%
            ArrayList<ReadingList> readinglists = (ArrayList<ReadingList>) request.getAttribute("readinglists");
            if (readinglists != null && readinglists.size() > 0){
                for (ReadingList readinglist : readinglists) { %>
        <br/>
        <input type="checkbox" name="readinglist<%=readinglist.Id%>" value="<%=readinglist.Id%>" <% if ( (boolean)request.getAttribute("readinglist" + readinglist.Id)) { %>
               checked
            <% } %>> <%=readinglist.Name%></input>
        <% } %>

                <br/>
        <button type="submit" class="btn btn-primary">Save to Library</button>
        <% } %>

    </form>
    <form method="POST" action="/blog/comment" class="comment-form">
        <input name="blogId" type="hidden" value=<%=blog.Id%>>
        <input name="userId" type="hidden" value=<%=user.Id%>>
        <input name="method" type="hidden" value="post">
        <textarea name="comment" placeholder="Add a comment" rows="4"></textarea>
        <button type="submit" class="btn btn-primary">Add Comment</button>
    </form>
    <% } %>

    <div class="comment">
        <% for (Comment comment : comments) { %>
        <div class="card">
            <div class="card-body">
                <p><strong><%= comment.Commenter.FullName%>:</strong> <%= comment.Content%></p>

                <!-- Add delete button if conditions are met -->
                <% if (user != null && (user.Id == comment.UserId || user.Role.equals("admin"))) { %>
                <form method="POST" action="/blog/comment" class="delete-comment-form">
                    <input name="commentId" type="hidden" value=<%=comment.Id%>>
                    <input name="blogId" type="hidden" value=<%=comment.BlogId%>>
                    <input name="method" type="hidden" value="delete">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
                <% } %>
            </div>
        </div>
        <% } %>
    </div>

<%--    <div class="like">--%>
<%--        <% for (Like like : likes) { %>--%>
<%--        <div class="card">--%>
<%--            <div class="card-body">--%>
<%--                <p><strong><%= like.Liker.FullName%></strong> liked this.</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <% } %>--%>
<%--    </div>--%>
</div>
<% } %>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-4r1pNDzrLTU8KqShofj6SMgfWt0sF9bu8QdNsKoqu5tm2MW0s0zEkGJlI99lEVW3"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyiflAZG5gH4Mxn3OyK02s2al29pi1wk2"
        crossorigin="anonymous"></script>
<script>
    var blogId = "<%=((Blog) request.getAttribute("blog")).Id%>";
    var newUrl = "/blog/blog/details?id=" + blogId;
    window.history.pushState({}, '', newUrl);
</script>
</body>
</html>
