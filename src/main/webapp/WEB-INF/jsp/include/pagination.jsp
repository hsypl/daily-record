<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div>
    <ul class="pagination">
        <li><a href="#" id="pagination-first">&laquo;</a></li>
        <c:forEach var="pageNumber" begin="1" end="${queryFilter.pagination.totalPage}" step="1">
            <li><a href="#" id="pagination-${pageNumber}">${pageNumber}</a></li>
        </c:forEach>
        <li><a href="#" id="pagination-last">&raquo;</a></li>
    </ul>
</div>
<form:hidden path="pagination.pageNumber" id="pageNumber" value="1"/>

<script>
    $(function () {
        $("[id^='pagination-']").bind("click", (function () {
            var paginationId = $(this).attr("id");
            var number = paginationId.substring(11, paginationId.length);
            if(number == "last"){
                number = 10000;
            }
            if(number == "first"){
                number = 1;
            }
            $("#pageNumber").val(number);
            $("#listForm").submit();
        }))
    })

</script>