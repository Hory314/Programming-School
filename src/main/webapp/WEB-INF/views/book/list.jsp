<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="./../template/header.jsp" />

<div class="card mb-3">
    <div class="card-header">
        <i class="fas fa-table"></i>
        Books
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Isbn</th>
                    </tr>
                </thead>

                <tfoot>
                    <tr>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Isbn</th>
                    </tr>
                </tfoot>

                <tbody>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.isbn}</td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </div>
</div>

<jsp:include page="./../template/footer.jsp" />