<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/header.jsp"/>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item active">Logowanie</li>
</ol>

<!-- Page Content -->
<h1>Zaloguj się</h1>
<hr>
<!--<p>This is a great starting point for new custom pages.</p>-->


<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Zaloguj się</div>

        <div class="card-body">${login_info}
            <form action="<c:url value="/login" />" method="post">
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Adres e-mail"
                               required="required" autofocus="autofocus">
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
                        <label style="cursor: default !important;">
                            <input type="checkbox" name="remember" checked>
                            Zapamiętaj mnie
                        </label>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary btn-block" value="Login">
            </form>
            <div class="text-center">
                <span class="d-block small mt-3">Nie masz konta lub zapomniałeś hasła?<br>Skontaktuj się ze swoim mentorem.</span>
            </div>
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