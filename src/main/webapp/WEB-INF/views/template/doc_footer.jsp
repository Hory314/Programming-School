<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Bootstrap core JavaScript-->
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/js/sb-admin.min.js"></script>

<!-- Page level plugin JavaScript-->
<script src="/vendor/datatables/jquery.dataTables.js"></script>
<script src="/vendor/datatables/dataTables.bootstrap4.js"></script>

<script>
    $(document).ready(function () {
        $('#dataTable').DataTable({
            "language": {
                "infoEmpty": "Brak wyników",
                "info": "Wyniki od _START_ do _END_ z _TOTAL_",
                "lengthMenu": "Pokaż _MENU_ wyników",
                "emptyTable": "Brak danych",
                "paginate": {
                    "previous": "Poprzednia strona",
                    "next": "Następna strona",
                },
            },
            "order": [] // sortuje domyslnie tabelke w takiej kolejnosci w jakiej otrzymalo dane (z zapytania)
        });
    });
</script>

</body>

</html>
