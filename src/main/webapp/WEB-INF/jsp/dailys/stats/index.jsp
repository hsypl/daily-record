<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/stats/detail" var="detailUrl"/>
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
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header" data-background-color="blue">
                                <h4 class="title">数据详情</h4>
                            </div>
                            <div class="card-content table-responsive">
                                <table class="table">
                                    <thead class="text-primary">
                                    <th>名称</th>
                                    <th>排名</th>
                                    <th>价格（CNY）</th>
                                    <th>价格（USD）</th>
                                    <th>价格（BTC）</th>
                                    <th>24小时涨幅</th>
                                    <th>一星期涨幅</th>
                                    <th>24小时交易量</th>
                                    <th>最后更新时间</th>
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
                                                <a href="${detailUrl}?id=${coinMarketCap.id}" rel="tooltip" title="detail" class="btn btn-danger btn-simple btn-xs">
                                                    <i class="fa fa-bar-chart" aria-hidden="true"></i>
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
        var active = $("#stats");
        active.addClass("active");
    });
</script>
</html>