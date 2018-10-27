<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    HttpSession userSession = request.getSession(false);

    if (session != null)
    {
        if (userSession.getAttribute("session_user") != null)
        {
%>
<ul class="sidebar navbar-nav">

    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/solutions"/>">
            <i class="fas fa-fw fa-table"></i>
            <span>Rozwiązania</span>
        </a>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link" href="<c:url value="/exercises"/>">
        <!--<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown"
           aria-haspopup="true" aria-expanded="false">-->
            <i class="fas fa-fw fa-folder"></i>
            <span>Zadania</span>
        </a>
        <!--<div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <%--<h6 class="dropdown-header">Login Screens:</h6>--%>
            <a class="dropdown-item" href="/book/form">Add</a>
            <a class="dropdown-item" href="/book/list">List</a>

            <%--<a class="dropdown-item" href="forgot-password.html">Forgot Password</a>--%>
            <%--<div class="dropdown-divider"></div>--%>
            <%--<h6 class="dropdown-header">Other Pages:</h6>--%>
            <%--<a class="dropdown-item" href="404.html">404 Page</a>--%>
            <%--<a class="dropdown-item active" href="blank.html">Blank Page</a>--%>
        </div>-->
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/groups"/>">
            <i class="fas fa-fw fa-graduation-cap"></i>
            <span>Grupy</span></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/users"/>">
            <i class="fas fa-fw fa-users"></i>
            <span>Użytkownicy</span></a>
    </li>
</ul>
<%
}
else
{
%>
<ul class="sidebar navbar-nav">

    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/login"/>">
            <i class="fas fa-fw fa-sign-in-alt"></i>
            <span>Logowanie</span>
        </a>
    </li>
</ul>
<%
        }
    }
%>