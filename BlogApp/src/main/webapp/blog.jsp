<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.ArrayList" %><%--
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
<%@ include file="components/addblog.jsp" %>

<%
    Blog blog = (Blog) request.getAttribute("blogs");
    request.setAttribute("blog", blog);
    request.getRequestDispatcher("components/viewblog.jsp").include(request, response);
%>
</body>
</html>
