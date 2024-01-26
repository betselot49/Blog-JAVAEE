<%@ page import="com.blog.models.Comment" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/24/2024
  Time: 8:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <%Comment comment = (Comment) request.getAttribute("comment");%>
    <img src="data:image/png;base64,<%= Base64.getEncoder().encodeToString(comment.Commenter.ProfilePicture) %>" >
    <h1><%=comment.Commenter.FullName%></h1>
    <p><%=comment.Content%></p>
</div>
