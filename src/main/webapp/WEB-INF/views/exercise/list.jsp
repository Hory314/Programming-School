<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="./../template/header.jsp"/>
<ol class="breadcrumb">
    <li class="breadcrumb-item active">Lista zadań</li>
</ol>
<div class="card mb-3">
    <div class="card-header">
        <i class="fas fa-table"></i>
        Lista zadań
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Tytuł</th>
                    <th>Opis</th>
                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th>Tytuł</th>
                    <th>Opis</th>
                </tr>
                </tfoot>

                <tbody>
                <c:forEach items="${exercises}" var="exercise">
                    <tr>
                        <td>
                            <a href="<c:url value="/exercise/${exercise.title}?id=${exercise.id}" />">${exercise.title}</a>
                        </td>
                        <td>${exercise.description}</td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </div>
</div>
<jsp:include page="./../template/footer.jsp"/>