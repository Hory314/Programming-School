<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/header.jsp"/>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item active">Logowanie</li>
</ol>

<!-- Page Content -->
<h1>Zaloguj się jako administrator</h1>
<hr>
<!--<p>This is a great starting point for new custom pages.</p>-->${bad_pass}


<form method="post">
    <p><input type="text" name="login" placeholder="Login"/></p>
    <p><input type="password" name="password" placeholder="Hasło"/></p>
    <p><label><input type="checkbox" name="remember" checked> Zapamiętaj mnie</label></p>
    <p><input type="submit" value="Zaloguj"></p>
</form>

<jsp:include page="../template/footer.jsp"/>
