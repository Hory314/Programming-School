<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="doc_header.jsp"/>

<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="<c:url value="/" />">Szkoła programowania</a>

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
    </button>
    <%
        HttpSession userSession = request.getSession(false);

        if (session != null)
        {
            if (userSession.getAttribute("session_user") != null)
            {
    %>
    <!-- Navbar Search -->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0" style="visibility: hidden;">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for..." aria-label="Search"
                   aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-primary" type="button">
                    <i class="fas fa-search"></i>
                </button>
            </div>
        </div>
    </form>

    <!-- Navbar -->
    <ul class="navbar-nav ml-auto ml-md-0">
        <!--<li class="nav-item dropdown no-arrow mx-1">
            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <span class="badge badge-danger">9+</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </li>-->
        <li class="nav-item no-arrow mx-1">
            <a class="nav-link" href="<c:url value="/add" />" role="button">
                Dodaj nowe rozwiązanie <i class="fas fa-plus-circle fa-fw"></i>
            </a>
        </li>
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <strong>${session_user.username}</strong> <i class="fas fa-user-circle fa-fw"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="<c:url value="/settings" />">Ustawienia</a>
                <a class="dropdown-item" href="<c:url value="/user/${session_user.username}?id=${session_user.id}" />">Mój
                    profil</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="<c:url value="/logout" />" data-toggle="modal"
                   data-target="#logoutModal">Wyloguj się</a>
            </div>
        </li>
    </ul>
    <%
            }
        }
    %>
</nav>

<div id="wrapper">
    <!-- Sidebar -->
    <jsp:include page="nav.jsp"/>

    <div id="content-wrapper">

        <div class="container-fluid">