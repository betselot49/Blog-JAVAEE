<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="blog" method="POST" enctype="multipart/form-data">
    <label> Title: </label>
    <input type="text" name="title" />
    <br />
    <label> Picture: </label>
    <input type="file" name="picture" accept="image/*"  />
    <br />
    <label> Content: </label>
    <input type="text" name="content" />
    <br />
    <label> Tags: </label>
    <input type="text" name="tags" />
    <button type="submit">Post</button>
</form>
