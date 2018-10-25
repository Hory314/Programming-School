<%@ page import="pl.coderslab.Entity.User" %>
<%@ page import="pl.coderslab.Entity.Exercise" %>
<%@ page import="pl.coderslab.Entity.UserGroup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    UserGroup userGroup = (UserGroup) request.getAttribute("userGroup");
    if (userGroup != null)
    {
        String groupName = ((UserGroup) request.getAttribute("userGroup")).getName();
        request.setAttribute("page_title", "Grupa " + groupName);
        request.setAttribute("group_name", groupName);
    }
    else
    {
        request.setAttribute("page_title", "Nie znaleziono grupy");
        request.setAttribute("group_name", "Nie znaleziono grupy");
    }
%>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/group/list" />">Grupy</a>
    </li>
    <li class="breadcrumb-item active">${group_name}</li>
</ol>

<%
    if (userGroup != null)
    {
%>
<!-- Page Content --> <%-- jesli exercise nie jest null --%>
<h1>${group_name}</h1>
<hr>
<!--<p>This is a great starting point for new custom pages.</p>-->

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
                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th>Nazwa</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>
                            <a href="<c:url value="/user/${user.username}?id=${user.id}&limit=3" />">${user.username}</a>
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
<h1>${group_name}</h1>
<hr>
<%
    }
%>

<jsp:include page="../template/footer.jsp"/>