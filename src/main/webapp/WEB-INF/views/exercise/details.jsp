<%@ page import="pl.coderslab.Entity.Exercise" %>
<%@ page import="pl.coderslab.Entity.Solution" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Exercise exercise = (Exercise) request.getAttribute("exercise");
    List<Solution> solutionList = (List<Solution>) request.getAttribute("solutions");
    if (exercise != null)
    {
        request.setAttribute("page_title", "Zadanie \"" + exercise.getTitle() + "\"");
        request.setAttribute("exercise_name", exercise.getTitle());
    }
    else
    {
        request.setAttribute("page_title", "Nie znaleziono zadania");
        request.setAttribute("exercise_name", "Nie znaleziono zadania");
    }
%>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/exercises" />">Lista zadań</a>
    </li>
    <li class="breadcrumb-item active">${exercise_name}</li>
</ol>

<%
    if (exercise != null)
    {
%>
<!-- Page Content --> <%-- jesli exercise nie jest null --%>
<h1>${exercise_name}</h1>
<hr>
<!--<p>This is a great starting point for new custom pages.</p>-->
<p><strong>Treść zadania:</strong></p>
<p class="text">${exercise.description}</p>
<hr>
<%-- teraz tabelka --%>
<%
    if (solutionList != null)
    {
%>
<div class="card mb-3">
    <div class="card-header">
        <i class="fas fa-table"></i>
        Rozwiązania tego zadania
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Utworzono</th>
                    <th>Zaktualizowano</th>
                    <th>Rozwiązanie</th>
                    <th>Dodał</th>
                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th>Utworzono</th>
                    <th>Zaktualizowano</th>
                    <th>Rozwiązanie</th>
                    <th>Dodał</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${solutions}" var="solution">
                    <tr>
                        <td>${solution.created}</td>
                        <td>${solution.updated}</td>
                        <c:set var="shortDesc" value="${solution.description}" scope="request"/>
                        <%
                            String str = (String) request.getAttribute("shortDesc");
                            if (str.length() > 256)
                            {
                                str = str.substring(0, 255); // 256 pierwszych znaków
                            }
                            request.setAttribute("shortDesc", str);
                        %>
                        <td>${shortDesc}... <a style="float: right;" href="<c:url value="/solution?id=${solution.id}"/>">przejdź do
                            rozwiązania »</a></td>
                        <td>
                            <a href="<c:url value="/user/${solution.user.username}?id=${solution.user.id}&limit=3" />">${solution.user.username}</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </div>
</div>
<%
}
else
{
%>
<p>Nie ma rozwiązań do tego zadania.</p>
<%
    }
%>
<%
}
else
{
%>
<!-- Page Content -->
<h1>${exercise_name}</h1>
<hr>

<%
    }
%>


<jsp:include page="../template/footer.jsp"/>