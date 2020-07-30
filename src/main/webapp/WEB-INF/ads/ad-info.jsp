<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jayarredondo
  Date: 7/27/20
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="View ${selectedAd.title} Details"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <div class="m-auto">
        <div class="card m-auto h-50" style="width: 30rem;">
            <div class="card-body">
                <h2 class="card-title"><c:out value="${selectedAd.title}"/></h2>
                <h4 class="card-subtitle mb-2 text-muted"><strong>Category:</strong> ${selectedAd.category}</h4>
                <p class="card-text"><c:out value="${selectedAd.description}"/></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
