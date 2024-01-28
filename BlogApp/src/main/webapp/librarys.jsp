<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.blog.models.ReadingList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-4r1pNDzrLTU8KqShofj6SMgfWt0sF9bu8QdNsKoqu5tm2MW0s0zEkGJlI99lEVW3" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyiflAZG5gH4Mxn3OyK02s2al29pi1wk2" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="components/header.jsp" %>
<form style="margin-top: 100px" method="post" action="/blog/readinglist">
    <input type="text" name="readingList" placeholder="new Reading List" required/>
    <input type="hidden" name="userId" value="<%=user.Id%>"/>
    <input type="hidden" name="method" value="post"/>
    <button type="submit">Add Reading List</button>
</form>
<div>
    <% if (request.getAttribute("success") != null || request.getAttribute("error") != null) { %>
    <%@ include file="components/toastify.jsp" %>
    <% } %>

    <%
        ArrayList<ReadingList> blogs = (ArrayList<ReadingList>) request.getAttribute("readinglists");
        if (blogs != null) {
            for (ReadingList readingList : blogs) {
    %>
    <a href="/blog/library/details?Id<%=readingList.Id%>">
        <h1><%=readingList.Name%></h1>
        <p>Blog Count: <%=readingList.BlogCount%></p>
        <form method="post" action="/blog/readinglist">
            <input type="hidden" name="readingList" value=<%=readingList.Id%>>
            <input type="hidden" name="method" value="delete"/>
            <button type="button">Delete</button>
        </form>
    </a>
    <%
            }
        }
    %>
</div>

</body>
</html>
