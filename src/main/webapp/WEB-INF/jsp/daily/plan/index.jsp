<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/daily/plan/edit" var="editUrl"/>
<s:url value="/daily/plan/delete" var="deleteUrl"/>
<s:url value="/daily/plan/index" var="listUrl"/>

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
            var active = $("#planIndex");
            active.addClass("active");
        });
    </script>
</head>
<body>
<c:set var="showEdit" value="true"/>
<c:set var="showSave" value="true"/>
<%@ include file="/WEB-INF/jsp/include/buttonHead.jsp" %>
<form:form commandName="queryFilter" id="listForm" action="${listUrl}">
    <form:input path="filter[id]" value="123"/>
    <form:input path="filter[beginTime]" value="456789"/>
    <input type="submit" value="提交"/>
    <table class="table">
        <h3>每日任务</h3>
        <thead>
        <tr>
            <th>id</th>
            <th>内容描述</th>
            <th>开始时间</th>
            <th>完成时间</th>
            <th>是否完成</th>
            <th>操作</th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <c:set var="columnCount" value="6"/>
            <td colspan="${columnCount}">
                <%@include file="/WEB-INF/jsp/include/pagination.jsp" %>
            </td>
        </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${planInfoList}" varStatus="status">
            <c:set var="planInfo" value="${status.current}"/>
            <tr>
                <td>${planInfo.id}</td>
                <td>${planInfo.description}</td>
                <td>${planInfo.startTime}</td>
                <td>${planInfo.completeTime}</td>
                <td>${planInfo.status}</td>
                <td>
                    <sec:authorize access="hasRole('ROLE_PLATFORMADMIN')">  你 不可以访问a.jsp </sec:authorize>
                    <a class="btn btn-primary" href="${editUrl}?id=${planInfo.id}" role="button">编辑</a>
                    <a class="btn btn-primary" href="${deleteUrl}?id=${planInfo.id}" role="button">删除</a>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form:form>
</body>
</html>
