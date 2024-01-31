<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.blog.models.ReadingList" %>
<%@ page import="com.blog.utils.Helpers" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-4r1pNDzrLTU8KqShofj6SMgfWt0sF9bu8QdNsKoqu5tm2MW0s0zEkGJlI99lEVW3" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyiflAZG5gH4Mxn3OyK02s2al29pi1wk2" crossorigin="anonymous"></script>
</head>
<body>

<%@ include file="components/header.jsp" %>

<%
if (Helpers.isNotificationRequest(request)) { %>
<%@ include file="components/toastify.jsp" %>
<%}%>

<div class="container" style="margin-top: 100px">
    <form class="form-inline mb-4" method="post" action="/blog/readinglist">
        <div class="form-group mr-2">
            <input type="text" class="form-control" name="readingList" placeholder="New Reading List" required/>
        </div>
        <input type="hidden" name="userId" value="<%=user.Id%>"/>
        <input type="hidden" name="method" value="post"/>
        <button type="submit" class="btn btn-primary">Add Reading List</button>
    </form>

    <div>
        <% if (request.getAttribute("success") != null || request.getAttribute("error") != null) { %>
        <%@ include file="components/toastify.jsp" %>
        <% } %>

        <%
            ArrayList<ReadingList> readingLists = (ArrayList<ReadingList>) request.getAttribute("readinglists");
            if (readingLists != null) {
                for (ReadingList readingList : readingLists) {
        %>
        <div class="card mb-3">
            <div class="card-body">
                <a href="/blog/library/details?Id=<%=readingList.Id%>" class="text-decoration-none">
                    <h1 class="card-title"><%=readingList.Name%></h1>
                    <p class="card-text">Blog Count: <%=readingList.BlogCount%></p>
                </a>
                <form method="post" action="/blog/readinglist" class="float-right">
                    <input type="hidden" name="readingList" value=<%=readingList.Id%>>
                    <input type="hidden" name="method" value="delete"/>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>
</div>

</body>
</html>
