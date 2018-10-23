<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="./../template/header.jsp" />

    <h1>${book.title}</h1>
    <span>${book.author.name} ${book.author.surname}</span>

<jsp:include page="./../template/footer.jsp" />