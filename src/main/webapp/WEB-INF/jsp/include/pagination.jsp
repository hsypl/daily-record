<style>
    .pagination li a {
        font-size: 20px;
    }
</style>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div>
    <ul class="pagination">
        <li><a id="pagination-first"><<</a></li>
        <c:forEach var="pageNumber" begin="1" end="${queryFilter.pagination.totalPage}" step="1">
            <li><a id="pagination-${pageNumber}">${pageNumber}</a></li>
        </c:forEach>
        <li><a id="pagination-last">>></a></li>
    </ul>
</div>
<form:hidden path="pagination.pageNumber" id="pageNumber" value="1"/>