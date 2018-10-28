<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="page_title" value="Lista użytkowników" scope="request"/>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item active">Twoje ustawienia (${session_user.username})</li>
</ol>
<h1>${session_user.username}</h1>
<hr>
edycja danych usera................ <%-- todo :: edycja danych usera --%>
<hr>
<!-- tabelka -->
<div class="card mb-3">
    <div class="card-header">
        <i class="fas fa-table"></i>
        Twoje rozwiązania ${table_info}
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Utworzono</th>
                    <th>Zaktualizowano</th>
                    <th>Rozwiązanie</th>
                    <th>Zadanie</th>
                    <th>Działania</th>
                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th>Utworzono</th>
                    <th>Zaktualizowano</th>
                    <th>Rozwiązanie</th>
                    <th>Zadanie</th>
                    <th>Działania</th>
                </tr>
                </tfoot>

                <tbody>
                <c:forEach items="${solutions}" var="solution">
                    <tr>
                        <td>${solution.created}</td>
                        <td>${solution.updated}</td>
                        <td class="text">${solution.description}</td>
                        <td>
                            <a href="<c:url value="/exercise/${solution.exercise.title}?id=${solution.exercise.id}" />">${solution.exercise.title}</a>
                        </td>
                        <td>
                            <a href="<c:url value="/solutions/edit?id=${solution.id}" />">Edytuj</a> / <a
                                href="<c:url value="/solutions/delete?id=${solution.id}" />">Usuń</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="./../template/footer.jsp"/>