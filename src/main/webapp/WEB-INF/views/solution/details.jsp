<%@ page import="pl.coderslab.Entity.Solution" %>
<%@ page import="pl.coderslab.Entity.UserGroup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Solution solution = (Solution) request.getAttribute("solution");
    if (solution != null)
    {
        String exerciseTitle = solution.getExercise().getTitle();
        request.setAttribute("page_title", "Rozwiązania zadania " + exerciseTitle);
        request.setAttribute("exercise_name", exerciseTitle);
    }
    else
    {
        request.setAttribute("page_title", "Nie znaleziono rozwiązania");
        request.setAttribute("exercise_name", "Nie znaleziono rozwiązania");
    }
%>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/exercises" />">Lista zadań</a>
    </li>
    <%
        if (solution != null)
        {
    %>
    <li class="breadcrumb-item">
        <a href="<c:url value="/exercise/${solution.exercise.title}?id=${solution.exercise.id}" />">${solution.exercise.title}</a>
    </li>
    <%
        }
    %>
    <li class="breadcrumb-item active">
        <%
            if (solution != null)
            {
        %>
        rozwiązanie użytkownika <a
            href="<c:url value="/user/${solution.user.username}?id=${solution.user.id}&limit=3"/>">${solution.user.username}</a>
        <%
        }
        else
        {
        %>
        ${exercise_name}
        <%
            }
        %>
    </li>
</ol>

<!-- Page Content -->
<!--<p>This is a great starting point for new custom pages.</p>-->
<div>
    <h1>${exercise_name}</h1>
    <p style="width: 70%; position: relative; float: left;">${solution.exercise.description}</p>
    <!-- terść zadania -->
    <%
        if (solution != null)
        {
    %>
    <table style="float: right; position: relative;">
        <tr>
            <td>Dodano:</td>
            <td>${solution.created}</td>
        </tr>
        <%
            if (solution.getUpdated() != null)
            {
        %>
        <tr>
            <td>Zmodyfikowano:</td>
            <td>${solution.updated}</td>
        </tr>
        <%
            }
        %>
        <tr>
            <td>Dodał:</td>
            <td>
                <a href="<c:url value="/user/${solution.user.username}?id=${solution.user.id}&limit=3"/>">${solution.user.username}</a>
            </td>
        </tr>
    </table>
</div>
<hr style="clear: both;">
<h5>Rozwiązanie:</h5>
<p class="text">${solution.description}</p>
<p style="float:right;"><a href="<c:url value="/exercise/${solution.exercise.title}?id=${solution.exercise.id}"/>">wszystkie
    rozwiązania tego zadania »</a></p>
<%
    }
%>


<jsp:include page="../template/footer.jsp"/>