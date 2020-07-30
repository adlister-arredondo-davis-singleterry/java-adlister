<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jayarredondo
  Date: 7/28/20
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Search Results"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h1>Results for: <c:out value="${keywords}"/></h1>
    <c:forEach var="ad" items="${foundAds}">
        <div class="col-md-6">
            <a href="/ad-info/show?id=${ad.id}"><h2><c:out value="${ad.title}"/></h2></a>
            <p><c:out value="${ad.description}"/></p>
        </div>
    </c:forEach>
</div>
</body>
</html>
