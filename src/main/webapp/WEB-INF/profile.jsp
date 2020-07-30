<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h1>Welcome, <c:out value="${sessionScope.user.username}"/>!</h1>
    <h3>Here are the ads you have posted so far:</h3>
    <a href="/user-settings"
    <c:forEach var="ad" items="${userAds}">
        <div class="card">
            <div class="col-md-6 card-body">
                <h5 class="card-title"><c:out value="${ad.title}"/></h5>
                <p class="card-text"><c:out value="${ad.description}"/></p>
                <p><strong>Category:</strong> ${ad.category}</p>
                <form action="/edit-ad" method="post">
                    <button type="submit" class="btn btn-primary" name="editAd" value="${ad.id}">Edit</button>
                </form>
                <form action="/profile" method="post">
                    <button type="submit" class="btn btn-primary" name="deleteAd" value="${ad.id}">Delete</button>
                </form>
            </div>
        </div>
    </c:forEach>
</div>

<footer>
    <form action="/profile" method="post">
        <button type="submit"
                name="deleteButton"
                class="btn btn-primary ml-auto"
                value="${sessionScope.user.id}"
        >Delete My Account
        </button>
    </form>
</footer>
</body>
</html>
