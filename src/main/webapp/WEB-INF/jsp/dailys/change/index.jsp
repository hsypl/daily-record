<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/change/edit" var="editUrl"/>
<s:url value="/dailys/change/delete" var="deleteURL"/>
<html>

<head>
    <title>Material Dashboard by Creative Tim</title>
    <%@include file="/WEB-INF/jsp/includes/linkOfHead.jsp" %>
</head>

<body>
<div class="wrapper">
    <%@include file="/WEB-INF/jsp/includes/sidebar.jsp" %>
    <div class="main-panel">
        <%@include file="/WEB-INF/jsp/includes/title.jsp" %>
        <div class="content">
            <div class="container-fluid">
                <a href="${editUrl}" class="btn btn-success" type="button" style="margin-left: 10px">
                    新增
                </a>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header" data-background-color="blue">
                                <h4 class="title">操作记录</h4>
                                <p class="category">Here is a subtitle for this table</p>
                            </div>
                            <div class="card-content table-responsive">
                                <table class="table">
                                    <thead class="text-primary">
                                    <th>代币符号</th>
                                    <th>价格</th>
                                    <th>数量</th>
                                    <th>总额</th>
                                    <th>交易类型</th>
                                    <th>交易日期</th>
                                    <th>备注</th>
                                    <th>操作</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${changeLogList}" varStatus="status">
                                        <c:set var="changeLog" value="${status.current}"/>
                                        <tr>
                                            <td>${changeLog.symbol}</td>
                                            <td>${changeLog.price}</td>
                                            <td>${changeLog.number}</td>
                                            <td>${changeLog.amount}</td>
                                            <td>${changeLog.typeEnum}</td>
                                            <td>${changeLog.formatCreateTime}</td>
                                            <td>${changeLog.remark}</td>
                                            <td>
                                                <a href="${editUrl}?id=${changeLog.id}" rel="tooltip" title="Edit symbol" class="btn btn-primary btn-simple btn-xs">
                                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                </a>
                                                <a href="${deleteURL}?id=${changeLog.id}" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
                                                    <i class="fa fa-times" aria-hidden="true"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/WEB-INF/jsp/includes/scriptOfBase.jsp" %>
<script>
    $(function () {
        var active = $("#changeUrl");
        active.addClass("active");
    });
</script>
</html>