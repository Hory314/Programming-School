<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item active">Użytkownicy</li>
</ol>
<div class="card mb-3">
    <div class="card-header">
        <i class="fas fa-table"></i>
        Użytkownicy
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Nazwa</th>
                    <th>Grupa</th>
                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th>Nazwa</th>
                    <th>Grupa</th>
                </tr>
                </tfoot>

                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>
                            <a href="<c:url value="/user/${user.username}?id=${user.id}&limit=3" />">${user.username}</a>
                        </td>
                        <td>${user.userGroup.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="./../template/footer.jsp"/>