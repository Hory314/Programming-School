<%@ page import="pl.coderslab.Entity.User" %>
<%@ page import="pl.coderslab.Entity.UserGroup" %>
<%@ page import="pl.coderslab.Dao.UserGroupDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="./../template/header.jsp"/>
<ol class="breadcrumb">
    <li class="breadcrumb-item active">Lista grup</li>
</ol>
<div class="card mb-3">
    <div class="card-header">
        <i class="fas fa-table"></i>
        Lista grup
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Nazwa</th>
                    <th>Liczba członków</th>
                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th>Nazwa</th>
                    <th>Liczba członków</th>
                </tr>
                </tfoot>

                <tbody>
                <c:forEach items="${userGroups}" var="userGroup">
                    <tr>
                        <td>
                            <a href="<c:url value="/group/${userGroup.name}?id=${userGroup.id}" />">${userGroup.name}</a>
                        </td>
                        <c:set var="group" value="${userGroup}" scope="request"/>
                        <%
                            UserGroup userGroup = (UserGroup) request.getAttribute("group");
                            UserGroupDao ugDao = new UserGroupDao();
                            int members = ugDao.getMembersCount(userGroup);
                        %>
                        <td><%=members%></td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </div>
</div>
<jsp:include page="./../template/footer.jsp"/>