<%@ page import="com.blog.models.Blog" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.blog.utils.Helpers" %>
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
<body >
<%@ include file="components/header.jsp" %>

<style>
    /* Add custom styles for the sticky button */
    .sticky-add-button {
        position: fixed;
        bottom: 20px;
        left: 20px;
        z-index: 1000;
    }
</style>

<!-- Add Post Button -->


<% if (request.getAttribute("error") != null) { %>
<div class="alert alert-danger" role="alert">
    <%= request.getAttribute("error")  %>
</div>
<% } %>

<div style="height: 500px"></div>

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

<% if (user != null) { %> <!-- If user is logged in -->
    <button type="button" class="btn btn-primary sticky-add-button px-4 py-2" data-toggle="modal" data-target="#addPostModal" style="font-size: 20px">
        Add Post
    </button>
<% }
%>
<% if (Helpers.isNotificationRequest(request)) { %>
<%@ include file="components/toastify.jsp" %>

<% } %>

<!-- Add Post Modal -->

<div class="modal fade" id="addPostModal" tabindex="-1" role="dialog" aria-labelledby="addPostModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addPostModalLabel">Add Blog Post</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%-- Include your addblog.jsp content here --%>
                <%@ include file="components/addblog.jsp" %>
            </div>
        </div>
    </div>
</div>

</body>
</html>
