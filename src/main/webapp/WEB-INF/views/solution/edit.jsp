<%@ page import="pl.coderslab.Entity.Solution" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/settings" />">Ustawienia</a>
    </li>
    <li class="breadcrumb-item active">Edytuj rozwiązanie</li>
</ol>

<!-- Page Content -->
<h1>Edytuj rozwiązanie</h1>
<hr>
<!--<p>This is a great starting point for new custom pages.</p>-->
<%
    Solution solution = (Solution) request.getAttribute("solution");

    if (solution != null)
    {
%>

<div class="container">
    <div class="card card-login mx-auto mt-5" style="max-width: 100%;">
        <div class="card-header">Edytuj rozwiązanie</div>

        <div class="card-body">${form_info}
            <form action="<c:url value="/solutions/edit" />" method="post">
                <div class="form-group">
                    <div class="form-label-group">
                        <label for="selectExercise">Zadanie</label>
                        <select style="background-color: rgba(0,0,0,0.15);" id="selectExercise"
                                class="form-control" disabled>
                            <option>${solution.exercise.title}</option>
                        </select> <!-- dummy select -->
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <label for="solutionField">Rozwiązanie</label>
                        <textarea id="solutionField" name="solution" class="form-control"
                                  required="required" value="">${solution.description}</textarea>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary btn-block" value="Zatwierdź modyfikację">
                <%--<input type="hidden" name="solution_id" value="${solution.id}">--%>
                <div class="text-center">
                    <span class="d-block small mt-3">Utworzono: ${solution.created}</span>
                    <span class="d-block small mt-3">Zmodyfikowano: <c:out value="${solution.updated}"
                                                                           default="nie"/></span>
                </div>
            </form>
        </div>
    </div>
</div>
<%
}
else
{
%>
<p>Rozwiązanie nie istnieje lub nie masz uprawnień do jego edycji.</p>
<%
    }
%>
<!-- simple form -->
<%--<form action="<c:url value="/login" />" method="post">
    <p><input type="email" name="email" placeholder="E-mail"/></p>
    <p><input type="password" name="password" placeholder="Hasło"/></p>
    <p><label><input type="checkbox" name="remember" checked> Zapamiętaj mnie</label></p>
    <p><input type="submit" value="Zaloguj"></p>
</form>--%>
<jsp:include page="../template/footer.jsp"/>