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
<!-- dane usera -->
<table>
    <tr>
        <td><b>Nazwa: </b></td>
        <td>${session_user.username}</td>
    </tr>
    <tr>
        <td><b>E-mail: </b></td>
        <td>${session_user.email}</td>
    </tr>
    <tr>
        <td><b>Grupa: </b></td>
        <td>
            <a href="<c:url value="/group/${session_user.userGroup.name}?id=${session_user.userGroup.id}"/>">${session_user.userGroup.name}</a>
        </td>
    </tr>
</table>
<a class="btn btn-primary"
   href="<c:url value="/settings/edit" />">Edycja danych i zmiana hasła</a>
${user_edit_info}
<!-- -->
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
                            <a class="btn btn-primary btn-block"
                               href="<c:url value="/solutions/edit?id=${solution.id}" />">Edytuj</a>
                            <a class="btn btn-primary btn-block btn-delete"
                               href="<c:url value="/solutions/delete?id=${solution.id}" />" data-toggle="modal"
                               data-target="#deleteModal-${solution.id}">Usuń</a>
                        </td>
                    </tr>

                    <!-- Delete Modal-->
                    <div class="modal fade" id="deleteModal-${solution.id}" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Usunąć?</h5>
                                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                </div>
                                <div class="modal-body">Kliknij <i>Usuń</i>, aby trwale usunąć wybrane rozwiązanie.
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Anuluj</button>
                                    <a class="btn btn-primary btn-delete"
                                       href="<c:url value="/solutions/delete?id=${solution.id}" />">Usuń</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<jsp:include page="./../template/footer.jsp"/>