<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<body>
<div>
    <div>
        <c:if test="${diagrams != null}">
            <h1>Folder ${diagrams[0].folderName}</h1>
            <table class="bordered">
                <tr>
                    <th style="width:200px;">Diagram Title</th>
                    <th style="width:200px;">Description</th>
                    <th style="width:200px;">Action</th>
                </tr>
                <c:forEach items="${diagrams}" var="diagram" varStatus="status">
                    <div>
                        <tr>
                            <td>
                                <a href="<c:url value='/diagram/${diagrams[status.index].diagramId}'/>">${diagram.title}</a>
                            </td>
                            <td>${diagram.description}</td>
                            <td><a href="<c:url value='/copy/${diagrams[status.index].diagramId}'/>">Copy</a>
                                <a href="<c:url value='/delete/${diagrams[status.index].diagramId}'/>">Delete</a></td>
                        </tr>
                    </div>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${diagrams == null}">
            <h1>No diagrams</h1>
        </c:if>
    </div>
</div>
</body>
</html>
