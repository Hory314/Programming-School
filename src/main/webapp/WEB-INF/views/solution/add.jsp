<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<c:url value="/exercises" />">Lista zadań</a>
    </li>
    <li class="breadcrumb-item active">Dodaj rozwiązanie</li>
</ol>

<!-- Page Content -->
<h1>Dodaj rozwiązanie</h1>
<hr>
<!--<p>This is a great starting point for new custom pages.</p>-->


<div class="container">
    <div class="card card-login mx-auto mt-5" style="max-width: 100%;">
        <div class="card-header">Dodaj rozwiązanie</div>

        <div class="card-body">${form_info}
            <form action="<c:url value="/solutions/add" />" method="post">
                <div class="form-group">
                    <div class="form-label-group">
                        <label for="selectExercise">Zadanie</label>
                        <select id="selectExercise" name="exercise_id" class="form-control" required="required"
                                autofocus="autofocus">
                            <c:forEach var="exercise" items="${exercises}">
                                <option value="${exercise.id}">${exercise.title}</option>
                            </c:forEach>
                            <!--<input type="email" id="inputEmail" name="email" class="form-control" placeholder="Adres e-mail"
                                   required="required" autofocus="autofocus">-->
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <label for="solutionField">Rozwiązanie</label>
                        <textarea id="solutionField" name="solution" class="form-control"
                                  required="required"></textarea>
                    </div>
                </div>
                <!-- <div class="form-group">
                     <div class="form-label-group">
                         <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Adres e-mail"
                                required="required">
                         <label for="inputEmail">Adres e-mail</label>
                     </div>
                 </div>
                 <div class="form-group">
                     <div class="form-label-group">
                         <input type="password" name="password" id="inputPassword" class="form-control"
                                placeholder="Hasło" required="required">
                         <label for="inputPassword">Hasło</label>
                     </div>
                 </div>
                 <div class="form-group">
                     <div class="checkbox">
                         <label>
                             <input type="checkbox" name="remember" checked>
                             Zapamiętaj mnie
                         </label>
                     </div>
                 </div>-->
                <input type="submit" class="btn btn-primary btn-block" value="Dodaj rozwiązanie">
            </form>
        </div>
    </div>
</div>

<!-- simple form -->
<%--<form action="<c:url value="/login" />" method="post">
    <p><input type="email" name="email" placeholder="E-mail"/></p>
    <p><input type="password" name="password" placeholder="Hasło"/></p>
    <p><label><input type="checkbox" name="remember" checked> Zapamiętaj mnie</label></p>
    <p><input type="submit" value="Zaloguj"></p>
</form>--%>
<jsp:include page="../template/footer.jsp"/>