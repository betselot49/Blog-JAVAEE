<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.blog.models.Comment" %>
<%@ page import="com.blog.models.Like" %><%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/1/2024
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="components/header.jsp" %>
<%@ include file="components/toastify.jsp" %>
Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii

<%--<% if (request.getAttribute(("error")) != null) { %>--%>
<%--    <div>--%>
<%--        <p><%= request.getAttribute("error") %></p>--%>
<%--    </div>--%>
<%--<% } else{--%>
<%--    Blog blog = (Blog) request.getAttribute("blog");--%>
<%--    boolean isLiked = (boolean) request.getAttribute("isLiked");--%>
<%--    ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");--%>
<%--    ArrayList<Like> likes = (ArrayList<Like>) request.getAttribute("likes");--%>
<%--    %>--%>
<%--        <div class="container">--%>
<%--            <div class="card" href="blog/<%=blog.Id%>">--%>
<%--                <div class="card-body">--%>

<%--                    <h2 class="card-title"><%= blog.Title %></h2>--%>
<%--                    <img src="data:image/png;base64,<%= Base64.getEncoder().encodeToString(blog.BlogPicture) %>" class="card-img-top blog-image" alt="Blog Image">--%>
<%--                    <p class="card-text"><%= blog.Content %></p>--%>
<%--                    <div class="like-comment-row">--%>
<%--                        <div class="like-comment-icons">--%>
<%--                            <i class="far fa-thumbs-up like-comment-icon"></i>--%>
<%--                            <span class="like-comment-count"><%= blog.LikeCount %> Likes</span>--%>
<%--                        </div>--%>
<%--                        <div class="like-comment-icons">--%>
<%--                            <i class="far fa-comment-alt like-comment-icon"></i>--%>
<%--                            <span class="like-comment-count"><%= blog.CommentCount %> Comments</span>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <form method="POST" action="/like/<%=blog.Id%>">--%>
<%--            <input type="text" name="comment">--%>
<%--            <% String msg = isLiked ? "Unlike" : "Like"; %>--%>
<%--            <button type="submit"><%=msg%></button>--%>
<%--        </form>--%>
<%--        <form method="POST" action="/comment/<%=blog.Id%>">--%>
<%--            <input type="text" name="comment">--%>
<%--            <button type="submit">Add Comment</button>--%>
<%--        </form>--%>
<%--        <div class="comment">--%>
<%--            <%--%>
<%--                for (Comment comment : comments) {--%>
<%--                    request.setAttribute("comment", comment);--%>
<%--                    request.getRequestDispatcher("components/viewcomment.jsp").include(request, response);--%>
<%--                }--%>
<%--            %>--%>
<%--        </div>--%>
<%--        <div class="like">--%>
<%--            <%--%>
<%--                for (Like like : likes) {--%>
<%--                    request.setAttribute("like", like);--%>
<%--                    request.getRequestDispatcher("components/viewlike.jsp").include(request, response);--%>
<%--                }--%>
<%--            %>--%>
<%--        </div>--%>
<%--<% } %>--%>
</body>
</html>
