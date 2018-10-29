<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item active">Edycja danych</li>
</ol>

<!-- Page Content -->
<h1>Edycja ${session_user.username}</h1>
<hr>
<!--<p>This is a great starting point for new custom pages.</p>-->


<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Edytuj dane</div>

        <div class="card-body">${login_info}
            <form action="<c:url value="/settings/edit" />" method="post">
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="inputUsername" name="username" class="form-control"
                               placeholder="Nazwa użytkownika"
                               required="required" autofocus="autofocus" value="${session_user.username}">
                        <label for="inputUsername">Nazwa użytkownika</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Adres e-mail"
                               required="required" value="${session_user.email}">
                        <label for="inputEmail">Adres e-mail</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="inputUsergroup" class="form-control from-disabled"
                               placeholder="Grupa"
                               value="${session_user.userGroup.name}" disabled>
                        <label for="inputUsergroup">Grupa</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" name="new-password" id="inputPassword" class="form-control"
                               placeholder="Nowe hasło">
                        <label for="inputPassword">Nowe hasło</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" name="new-password2" id="inputPasswordAgain" class="form-control"
                               placeholder="Powtórz nowe hasło">
                        <label for="inputPasswordAgain">Powtórz nowe hasło</label>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" name="current-password" id="inputCurrentPassword" class="form-control"
                               placeholder="Obecne hasło" required="required">
                        <label for="inputCurrentPassword">Obecne hasło</label>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary btn-block" value="Zapisz">
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