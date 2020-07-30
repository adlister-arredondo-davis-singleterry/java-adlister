<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
    <div class="container">
        <h1>Create a new Ad</h1>
        <form action="/ads/create" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text" maxlength="60" autofocus required>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text" required></textarea>
            </div>
            <div class="form-group">
                <label for="category">Category</label>
                <select name="category" id="category">
                    <c:forEach var="category" items="${categories}" >
                        <option id="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
        <div>
            <c:if test="${inputIsNull}">
                <small>Input fields cannot be left blank.</small>
            </c:if>
        </div>
    </div>
</body>
</html>
