<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Diagram</title>
</head>
<body>
<h1>Diagram</h1>
<div>
    <table>
        <c:forEach items="${diagram.sheets}" var="sheet" varStatus="status">
            <tr>
                <th>Sheet ${status.index + 1}:</th>
                <td><img src="${sheet.imageUrlForApi}${apiKey}"/></td>
            </tr>
        </c:forEach>
        <tr>
            <th>Title:</th>
            <td>${diagram.title}</td>
        </tr>
        <tr>
            <th>Description:</th>
            <td>${diagram.description}</td>
        </tr>
    </table>
</div>
<table>
    <tr>
        <th style="width:200px;">Comment</th>
        <th style="width:200px;">User</th>
        <th style="width:200px;">Created Date</th>
    </tr>
    <c:forEach items="${diagram.comments}" var="comment" varStatus="status">
        <tr>
            <td>${comment.content}</td>
            <td>${comment.user.nickname}</td>
            <td>${comment.created}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
