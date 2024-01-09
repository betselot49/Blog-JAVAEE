<%@ page import="com.blog.models.User" %><%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/9/2024
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <%
    User user = (User) request.getAttribute("user");
    %>
    <img src="data:image/png;base64,<%=user.ProfilePicture%>"/>
    <div>
        <h1><%=user.FullName%></h1>
        <h2>Posted: <%=user.BlogCount%> blogs</h2>
    </div>
</div>

