<%@ page import="com.blog.models.Like" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/24/2024
  Time: 9:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <%Like like = (Like) request.getAttribute("comment");%>
    <img src="data:image/png;base64,<%= Base64.getEncoder().encodeToString(like.Liker.ProfilePicture) %>" >
    <h1><%=like.Liker.FullName%></h1>
</div>
