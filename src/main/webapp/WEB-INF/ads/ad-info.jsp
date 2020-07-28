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
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<h1>${selectedAd.title}</h1>
<p>${selectedAd.description}</p>
</body>
</html>
