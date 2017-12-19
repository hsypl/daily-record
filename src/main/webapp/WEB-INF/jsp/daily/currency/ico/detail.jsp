<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<s:url value="/daily/currency/history/index" var="historyUrl"/>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <script src="/media/js/responsive-nav.js"></script>
    <script src="/media/js/jquery-3.2.1.min.js"></script>
    <script src="/media/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/media/css/responsive-nav.css">
    <link rel="stylesheet" href="/media/css/bootstrap.min.css">
    <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
    <script type="application/javascript">
        $(function () {
            var active = $("#currencyIndex");
            active.addClass("active");
        });
    </script>
    <style>
        .table {

        }
    </style>
</head>
<body>

<table class="table">
    <div style="text-align: center"><span style="font-size: 40px">价格详情</span></div>
    <thead>
    <tr>
        <th>名称</th>
        <th>排名</th>
        <th>价格（CNY）</th>
        <th>价格（USD）</th>
        <th>价格（BTC）</th>
        <th>24小时涨幅</th>
        <th>一星期涨幅</th>
        <th>24小时交易量</th>
        <th>最后更新时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${coinMarketCapList}" varStatus="status">
        <c:set var="coinMarketCap" value="${status.current}"/>
        <tr>
            <td>${coinMarketCap.name}</td>
            <td>${coinMarketCap.rank}</td>
            <td><fmt:formatNumber value="${coinMarketCap.priceCny}" minFractionDigits="4"/></td>
            <td><fmt:formatNumber value="${coinMarketCap.priceUsd}" minFractionDigits="4"/></td>
            <td><fmt:formatNumber value="${coinMarketCap.priceBtc}" minFractionDigits="8"/></td>
            <c:if test="${coinMarketCap.percentChange24H >= 0}">
                <td >
                    <span style="color:  #093;">${coinMarketCap.percentChange24H}%</span>
                </td>
            </c:if>
            <c:if test="${coinMarketCap.percentChange24H < 0}">
                <td>
                    <span style="color: red">${coinMarketCap.percentChange24H}%</span>
                </td>
            </c:if>
            <c:if test="${coinMarketCap.percentChange7D >= 0}">
                <td >
                    <span style="color: #093">${coinMarketCap.percentChange7D}%</span>
                </td>
            </c:if>
            <c:if test="${coinMarketCap.percentChange7D < 0}">
                <td>
                    <span style="color: red">${coinMarketCap.percentChange7D}%</span>
                </td>
            </c:if>
            <td><fmt:formatNumber value="${coinMarketCap.volumeCny24H}" pattern="#,#00.0#"/></td>
            <td>${coinMarketCap.formatCreateTime}</td>
            <td>
                <a href="${historyUrl}?id=${coinMarketCap.id}" class="fa fa-eye fa-lg" data-toggle="modal" data-target="#myModal"></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 模态框（Modal） -->
<div class="modal fade in"
     id="myModal"
     tabindex="-1"
     role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-body">
            </div>
        </div>
    </div>
</div>
</body>
</html>
