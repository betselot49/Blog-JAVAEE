<%@ page import="java.util.ArrayList" %>
<%@ page import="com.blog.models.User" %>
<%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/4/2024
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="components/header.jsp"%>
<%
        ArrayList<User> peoples = (ArrayList<User>) request.getAttribute("peoples");
        for (User people : peoples) {
            request.setAttribute("people", people);
            request.getRequestDispatcher("components/usercard.jsp").include(request, response);
        }
%>
</body>
</html>
