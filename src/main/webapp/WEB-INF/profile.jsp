<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome, <c:out value="${sessionScope.user.username}"/>!</h1>
    </div>
    <footer>
        <form action="/profile" method="post">
            <button type="submit"
                    name="deleteButton"
                    class="btn btn-primary ml-auto"
                    value="${sessionScope.user.id}"
            >Delete My Account</button>
        </form>
    </footer>
</body>
</html>
