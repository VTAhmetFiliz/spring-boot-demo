<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 15.03.2018
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Copy input diagram</title>
</head>
<body>
<div>
    <h1>Copy diagram</h1>
    <div>
        <form name="copy" action="/copy/copyDiagram" method="POST">
            <input type="hidden" name="diagramId" value="${diagramId}">
            <table>
                <tr>
                    <td>Title</td>
                    <td><input type="text" name="title" value="diagram title"></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><input type="text" name="description"></td>
                </tr>
            </table>
            <input type="submit" value="SUBMIT">
        </form>
    </div>
    <div>
        <a href="<%=request.getContextPath()%>/">&lt;&lt; Back to folders.</a>
    </div>
</div>
</body>
</html>
