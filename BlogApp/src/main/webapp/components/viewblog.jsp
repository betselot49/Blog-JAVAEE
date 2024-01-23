<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/8/2024
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <%
        Blog blog = (Blog) request.getAttribute("blog");
        String img = Base64.getEncoder().encodeToString(blog.BlogPicture);
    %>
    <h1><%=blog.Title%></h1>
    <p><%=blog.Content%></p>
    <p><%=blog.CreatedAt%></p>
    <p><%=blog.LikeCount%></p>
    <p><%=blog.CommentCount%></p>
    <img src="data:image/png;base64,<%=img%>" alt="">
</div>
