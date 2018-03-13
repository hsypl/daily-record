<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/monitor/edit" var="editUrl"/>
<s:url value="/dailys/monitor/delete" var="deleteUrl"/>
<s:url value="/dailys/monitor/save" var="saveURL"/>
<s:url value="/dailys/monitor/index" var="listUrl"/>
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
                                <h4 class="title">币种监控</h4>
                            </div>
                            <div class="card-content table-responsive">
                                <table class="table">
                                    <thead class="text-primary">
                                    <th>名称</th>
                                    <th>交易所</th>
                                    <th>卖价</th>
                                    <th>买价</th>
                                    <th>类型</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${monitorSymbolList}" varStatus="status">
                                        <c:set var="monitor" value="${status.current}"/>
                                        <tr>
                                            <td>${monitor.symbol}</td>
                                            <td>${monitor.exchange}</td>
                                            <td>${monitor.sellPrice}</td>
                                            <td>${monitor.buyPrice}</td>
                                            <td>${monitor.type}</td>
                                            <td>
                                                <a href="${editUrl}?id=${monitor.id}" rel="tooltip" title="Edit symbol" class="btn btn-primary btn-simple btn-xs">
                                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                </a>
                                                <a href="${deleteUrl}?id=${monitor.id}" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
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
</body>
<%@include file="/WEB-INF/jsp/includes/scriptOfBase.jsp" %>
<script>
    $(function () {
        var active = $("#user");
        active.addClass("active");
    });
</script>
</html>