<%--
  Created by IntelliJ IDEA.
  User: betse
  Date: 1/29/2024
  Time: 8:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- editblog.jsp -->

    <div class="form-group">
        <label for="editTitle">Title:</label>
        <input type="text" class="form-control" id="editTitle" name="editTitle" value="<%= "blog.Title" %>">
    </div>

    <div class="form-group">
        <label for="editContent">Content:</label>
        <textarea class="form-control" id="editContent" name="editContent" rows="4"><%= "blog.Content" %></textarea>
    </div>

</body>
</html>
