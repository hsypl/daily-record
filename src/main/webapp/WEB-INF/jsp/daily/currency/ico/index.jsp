<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<s:url value="/daily/currency/ico/edit" var="editUrl"/>
<s:url value="/daily/currency/ico/update" var="updateUrl"/>
<s:url value="/daily/currency/ico/sync" var="syncUrl"/>
<s:url value="/daily/currency/ico/delete" var="deleteUrl"/>
<s:url value="/daily/currency/ico/index" var="listUrl"/>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
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
        <h3>ICO项目记录    ---- 投入总额 ${inSum}  ---  现总额 ${count}</h3>
        <a href="${updateUrl}" class="btn btn-default" >
            更新
        </a>
        <a href="${syncUrl}" class="btn btn-default" style="margin-left: 10px">
            一键同步
        </a>
        <thead>
        <tr>
            <th>项目名称</th>
            <th>投入金额</th>
            <th>代币符号</th>
            <th>数量</th>
            <th>现价</th>
            <th>总额</th>
            <th>收益</th>
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
                <td>${icoProjectInfo.name}</td>
                <td>${icoProjectInfo.price}</td>
                <td>${icoProjectInfo.symbol}</td>
                <td>${icoProjectInfo.number}</td>
                <td><fmt:formatNumber value="${icoProjectInfo.nowPrice}" minFractionDigits="3"/></td>
                <td>${icoProjectInfo.count}</td>
                <td><fmt:formatNumber value="${icoProjectInfo.percent}" minFractionDigits="0"/></td>
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
