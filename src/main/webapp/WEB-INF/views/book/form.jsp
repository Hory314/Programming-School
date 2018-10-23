<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../template/header.jsp" />

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="/book/list">Book</a>
    </li>
    <li class="breadcrumb-item active">Form</li>
</ol>

<!-- Page Content -->
<h1>Add new Book</h1>
<hr>
<p>This is a great starting point for new custom pages.</p>

    <form action="#" method="post">
        <input type="text" name="title" placeholder="title" />
        <select name="author_id">
            <c:forEach items="${authors}" var="author">
                <option value="${author.id}">${author.name} ${author.surname}</option>
            </c:forEach>
        </select>

        <input type="text" name="isbn" placeholder="isbn" />
        <input type="submit" value="save">
    </form>

<jsp:include page="../template/footer.jsp" />
