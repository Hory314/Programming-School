<%@ page import="pl.coderslab.Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    User user = (User) request.getAttribute("user");
    if (user != null)
    {
        request.setAttribute("page_title", "Profil użytkownika " + user.getUsername());
        request.setAttribute("username", user.getUsername());
    }
    else
    {
        request.setAttribute("page_title", "Nie znaleziono użytkownika");
        request.setAttribute("username", "Nie znaleziono użytkownika");
    }
%>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/user/list" />">Użytkownicy</a>
    </li>
    <li class="breadcrumb-item active">${username}<%--${user.username}--%></li>
</ol>
<%
    if (user != null)
    {
        String email = user.getEmail();

        String starredEmail = "";
        starredEmail += email.charAt(0);
        int atIndex = email.indexOf("@");
        for (int i = 0; i < atIndex - 2; i++)
        {
            starredEmail += "*";
        }
        starredEmail += email.charAt(atIndex - 1);
        starredEmail += email.substring(atIndex);


%>


<!-- Page Content --> <%-- jesli user nie jest null --%>
<h1>${username}</h1>
<hr>
<!--<p>This is a great starting point for new custom pages.</p>-->
<table>
    <tr>
        <td><b>E-mail:</b></td>
        <td><%=starredEmail%>
        </td>
    </tr>
    <tr>
        <td><b>Grupa:</b></td>
        <td>${user.userGroup.name}</td>
    </tr>
</table>
<hr>


<div class="card mb-3">
    <div class="card-header">
        <i class="fas fa-table"></i>

        <%
            String limit = request.getParameter("limit");
            try
            {
                int intLimit = Integer.parseInt(limit);
        %>
        Ostatnie <%=intLimit%> rozwiązania użytkownika (<a href="<c:url value="?id=${user.id}" />">Pokaż wszystkie</a>)
        <%
        }
        catch (NumberFormatException e)
        {
        %>
        Wszystkie rozwiązania użytkownika
        <%
            }
        %>

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
                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th>Utworzono</th>
                    <th>Zaktualizowano</th>
                    <th>Rozwiązanie</th>
                    <th>Zadanie</th>
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
                        <td>${shortDesc}... <a style="float: right;" href="/solution?id=${solution.id}">przejdź do
                            rozwiązania »</a></td>
                        <td>
                            <a href="<c:url value="/exercise/${solution.exercise.title}?id=${solution.exercise.id}" />">${solution.exercise.title}</a>
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
<!-- Page Content -->
<h1>${username}</h1> <%-- jesli user jest null (do $username przypisalismy wczesniej wartosc) --%>
<hr>

<%
    }
%>


<jsp:include page="../template/footer.jsp"/>