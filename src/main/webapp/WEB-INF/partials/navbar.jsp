<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/ads">(m)Adlister</a>
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${sessionScope['user'] != null}">
                <li class="nav-item"><a class="nav-link" href="/ads/create">Create an Ad</a></li>
                <li class="nav-item"><a class="nav-link" href="/profile">View My Profile</a></li>
            </c:if>
            <c:choose>
                <c:when test="${sessionScope['user'] != null}">
                    <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/ads/search" method="POST">
            <input class="form-control mr-sm-2" name="search" id="search" type="search" placeholder="Search"
                   aria-label="Search">
            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
