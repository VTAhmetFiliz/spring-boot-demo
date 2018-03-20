<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<body>
<div>
    <div>
        <div>
            <img src="${account.imageUrl}${apiKey}">
            <td>${account.nickname}</td>
        </div>
        <c:if test="${folders != null}">
            <h1>Folders</h1>
            <table class="bordered">
                <tr>
                    <th style="width:200px;">Folder Name</th>
                    <th style="width:200px;">Folder Id</th>
                    <th style="width:200px;">Type</th>
                    <th style="width:200px;">Created</th>
                    <th style="width:200px;">Updated</th>
                </tr>
                <c:forEach items="${folders}" var="folder" varStatus="status">
                    <div>
                        <tr>
                            <td>
                                <a href="<c:url value='/folder/${folders[status.index].folderId}'/>">${folder.folderName}</a>
                            </td>
                            <td>${folder.folderId}</td>
                            <td>${folder.type}</td>
                            <td>${folder.created}</td>
                            <td>${folder.updated}</td>
                        </tr>
                    </div>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${folders == null}">
            <h1>No Folders</h1>
        </c:if>
        <c:if test="${diagrams != null}">
            <h1>Diagrams</h1>
            <table class="bordered">
                <tr>
                    <th style="width:200px;">Diagram Name</th>
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
            <h1>No Diagrams</h1>
        </c:if>
    </div>
</div>
</body>
</html>
