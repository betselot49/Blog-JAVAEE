<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/4/2024
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
