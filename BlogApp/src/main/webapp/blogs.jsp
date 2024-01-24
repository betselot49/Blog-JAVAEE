<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="components/header.jsp" %>
<%--<%@ include file="components/toastify.jsp" %>--%>
<%@ include file="components/addblog.jsp" %>

<% if (request.getAttribute("error") != null) { %>
    <div> class="alert alert-danger" role="alert">
        <%= request.getAttribute("error")  %>
    </div>
<% } %>
<%
    if (request.getAttribute("error") == null) {
        ArrayList<Blog> items = (ArrayList<Blog>) request.getAttribute("blogs");
        if (items != null) {
            for (Blog blog : items) {
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("components/viewblog.jsp").include(request, response);
            }
        }
    }
%>
</body>
</html>