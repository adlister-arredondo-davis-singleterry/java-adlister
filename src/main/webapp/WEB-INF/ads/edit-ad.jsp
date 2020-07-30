
<%--
  Created by IntelliJ IDEA.
  User: jayarredondo
  Date: 7/29/20
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="View ${adToEdit.title} Details"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <h3>Make your changes below:</h3>
    <form action="/ads" method="post">
        <div class="form-group">
            <label for="title">Title</label>
            <input id="title" name="title" class="form-control" type="text" value="${adToEdit.title}" required>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" class="form-control" required>${adToEdit.description}</textarea>
        </div>
        <div class="form-group">
            <label for="category">Category</label>
            <select name="category" id="category">
                <c:forEach var="category" items="${categories}">
                <option id="${category.id}" ${category.name == adToEdit.category ? 'selected' : ''}>${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" name="editId" value="${adToEdit.id}" class="btn btn-block btn-primary">Submit Changes</button>
    </form>
    <div>
        <c:if test="${inputIsNull}">
            <small>Input fields cannot be left blank.</small>
        </c:if>
    </div>
</div>

</body>
</html>
