<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="blog" method="POST">
    <label> Title: </label>
    <input type="text" name="title" />
    <br />
    <label> Picture: </label>
    <input type="file" name="image" accept="image/*"  />
    <br />
    <label> Content: </label>
    <input type="text" name="content" />
    <br />
    <button type="submit">Post</button>
</form>
</body>
</html>