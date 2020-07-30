<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <div class="jumbotron jumbotron-fluid mt-3 bg-white text-primary border border-primary">
        <div class="container">
            <h1 class="display-4 text-center">Here are all the ads!</h1>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-md-3">
        <c:forEach var="ad" items="${ads}">
            <div class="col mb-4">
            <div class="card h-100" style="width: 21.5rem;">
                <div class="card-body">
                    <h2 class="card-title"><a href="/ad-info/show?id=${ad.id}"><c:out value="${ad.title}"/></a></h2>
                    <h4 class="card-subtitle mb-2 text-muted"><strong>Category:</strong> ${ad.category}</h4>
                    <p class="card-text"><c:out value="${ad.description}"/></p>
                </div>
            </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
