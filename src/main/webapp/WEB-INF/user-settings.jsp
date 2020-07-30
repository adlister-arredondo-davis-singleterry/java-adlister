<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Account Settings" />
    </jsp:include>
</head>
<body>
<jsp:include page="partials/navbar.jsp" />
<div class="container">
    <h1>Change Settings for <c:out value="${sessionScope.user.username}"/>:</h1>

    <form action="/user-settings" method="post">
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" name="email" class="form-control" type="text" maxlength="60" autofocus required value="${sessionScope.user.email}">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" name="password" class="form-control" type="password" maxlength="60" required>
        </div>
        <div class="form-group">
            <label for="confirm_password">Confirm Password</label>
            <input id="confirm_password" name="confirm_password" class="form-control" type="password" maxlength="60" required>
        </div>
        <input type="submit" name="editButton" class="btn btn-primary btn-block">
    </form>
    <div>
        <c:if test="${invalidUsername}">
            <small>Sorry, that username is already taken.</small>
        </c:if>
        <c:if test="${inputHasErrors}">
            <small>Please make sure your password and password confirmation are the same.</small>
        </c:if>
    </div>
</div>
<footer>
    <form action="/user-settings" method="post">
        <button type="submit"
                name="deleteButton"
                class="btn btn-primary ml-auto"
                value="${sessionScope.user.id}"
        >Delete My Account</button>
    </form>
</footer>
</body>
</html>