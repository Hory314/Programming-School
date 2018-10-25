<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="./../template/header.jsp" />

<div class="card mb-3">
    <div class="card-header">
        <i class="fas fa-table"></i>
        Rozwiązania
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
                    <th>Dodał</th>
                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th>Utworzono</th>
                    <th>Zaktualizowano</th>
                    <th>Rozwiązanie</th>
                    <th>Zadanie</th>
                    <th>Dodał</th>
                </tr>
                </tfoot>

                <tbody>
                <c:forEach items="${solutions}" var="solution">
                    <tr>
                        <td>${solution.created}</td>
                        <td>${solution.updated}</td>
                        <td>${solution.description}</td>
                        <td>${solution.exercise.title}</td>
                        <td><a href="<c:url value="/user/${solution.user.username}?id=${solution.user.id}&limit=3" />">${solution.user.username}</a></td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </div>
</div>
<jsp:include page="./../template/footer.jsp" />

<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item active">Rozwiązania zadań</li>
</ol>

<!-- Page Content -->
<h1>Rozwiązania zadań</h1>
<hr>
<!--<p>This is a great starting point for new custom pages.</p>-->${login_info}


<jsp:include page="../template/footer.jsp"/>--%>