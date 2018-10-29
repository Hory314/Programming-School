<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/admin_header.jsp"/>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item active">Logowanie admina</li>
</ol>

<!-- Page Content -->
<h1>Zaloguj się jako administrator</h1>
<hr>
<!--<p>This is a great starting point for new custom pages.</p>-->${login_info}


<form action="<c:url value="/adminpanel" />" method="post">
    <p><input type="text" name="login" placeholder="Login"/></p>
    <p><input type="password" name="password" placeholder="Hasło"/></p>
    <p><label><input type="checkbox" name="remember"> Zapamiętaj mnie</label></p>
    <p><input type="submit" value="Zaloguj"></p>
</form>

<jsp:include page="../template/admin_footer.jsp"/>
