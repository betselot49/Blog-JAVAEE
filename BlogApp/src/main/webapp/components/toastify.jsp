<%--
  Created by IntelliJ IDEA.
  User: fikre
  Date: 1/9/2024
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <style>
        #toast-container {
            position: fixed;
            top: 20px;
            right: 20px;
            max-width: 300px;
            z-index: 9999;
            opacity: 0;
        }
        .toast-success {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border-radius: 5px;
        }
        .toast-error {
            background-color: #f44336;
            color: white;
            padding: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<%
    boolean isSuccess = (request.getAttribute("success") != null);
    String message = isSuccess ? request.getAttribute("success").toString() : request.getAttribute("error").toString();
%>

<div id="toast-container">
    <h2 id="toast" class="<%= isSuccess ? "toast-success" : "toast-error" %>"><%= message %></h2>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        showToast();
    });

    function showToast() {
        let toastContainer = document.getElementById("toast-container");
        setTimeout(function () {
            // Fix: Use 'toastContainer' instead of 'toast' for the element
            toastContainer.style.opacity = 1;

            setTimeout(function () {
                // Fix: Use 'toastContainer' instead of 'toast' for the element
                toastContainer.style.opacity = 0;
            }, 2000);
        }, 2000);
    }
</script>
</body>
</html>