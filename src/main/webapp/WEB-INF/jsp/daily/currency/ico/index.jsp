<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/daily/currency/ico/edit" var="editUrl"/>
<s:url value="/daily/currency/ico/delete" var="deleteUrl"/>
<s:url value="/daily/currency/ico/index" var="listUrl"/>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/media/js/responsive-nav.js"></script>
    <script src="/media/js/jquery-3.2.1.min.js"></script>
    <script src="/media/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/media/css/responsive-nav.css">
    <link rel="stylesheet" href="/media/css/bootstrap.min.css">

    <%@ include file="/WEB-INF/jsp/include/header.jsp" %>
    <script type="application/javascript">
        $(function () {
            var active = $("#currencyIndex");
            active.addClass("active");
        });

    </script>
</head>
<body>
<c:set var="showEdit" value="true"/>
<%@ include file="/WEB-INF/jsp/include/buttonHead.jsp" %>
<form:form commandName="queryFilter"  id ="listForm"  action="${listUrl}">
    <table class="table">
        <h3>ICO项目记录</h3>
        <thead>
        <tr>
            <th>id</th>
            <th>项目名称</th>
            <th>网站地址</th>
            <th>投入金额</th>
            <th>代币符号</th>
            <th>备注</th>
            <th>备注</th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <c:set var="columnCount" value="6"/>
            <td colspan="${columnCount}">
                <%@include file="/WEB-INF/jsp/include/pagination.jsp"%>
            </td>
        </tr>
        </tfoot>
        <script>
            $(function () {
                $("[id^='pagination-']").bind("click", (function () {
                    var paginationId = $(this).attr("id");
                    var number = paginationId.substring(11, paginationId.length);
                    $("#pageNumber").val(number);
                    $("#listForm").submit();
                }))
            })
        </script>
        <tbody>
        <c:forEach items="${icoProjectInfoList}" varStatus="status">
            <c:set var="icoProjectInfo" value="${status.current}"/>
            <tr>
                <td>${icoProjectInfo.id}</td>
                <td>${icoProjectInfo.name}</td>
                <td>${icoProjectInfo.website}</td>
                <td>${icoProjectInfo.price}</td>
                <td>${icoProjectInfo.symbol}</td>
                <td>${icoProjectInfo.remark}</td>
                <td>
                    <a class="btn btn-primary" href="${editUrl}?id=${icoProjectInfo.id}" role="button">编辑</a>
                    <a class="btn btn-primary" href="${deleteUrl}?id=${icoProjectInfo.id}" role="button">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>

    </script>
</form:form>

</body>
</html>
