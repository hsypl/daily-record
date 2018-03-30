<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/query/index" var="indexUrl"/>
<s:url value="/dailys/query/update" var="updateUrl"/>
<s:url value="/dailys/query/detail" var="detailUrl"/>
<html>

<head>
    <title>Material Dashboard by Creative Tim</title>
    <%@include file="/WEB-INF/jsp/includes/linkOfHead.jsp" %>
    <link rel="stylesheet" href="/media/css/select2.min.css">
    <style>
        .select2-container--default .select2-selection--single {
            height: 34px;
            font-size: large;
        }
        .js-example-basic-single {
            width: 130px;
        }
        .price{
            float: left;
            margin-left: 10px;
            width: 350px;
            height: 220px;
            padding: 5px;
        }
        .detail-child{
            text-align: center;
            float: left;
            width: 50%;
            font-size: 15px;
            padding: 24px 0;
            border-right: 1px solid #5e5e5e;
            line-height: 1.25em;
        }
        .price-child-detail{
            border-top: 1px solid #5e5e5e;clear:both;
        }
        .price-child{
            width: 336px;
            height: 193px;
            border:2px solid #5e5e5e;
            border-radius:10px;
        }
        .price-select{
            margin-left: 15px;
            width: 336px;
            height: 193px;
            border:2px solid #5e5e5e;
            border-radius:10px;
        }
        .price-child-title{
            margin-left: 10px;
            float:left;
            width:95%;
            border: 0 solid #000;
            text-align:left;
            padding:5px 0;
            line-height:25px;
        }
        .price-child-title .icon-arr{
            float: right;
        }
        .price-child-title .icon-arr a{
            color: blue;
        }
        .price-child-title .fa-plus{
            float: right;
            color:#178AEB;
        }
        .price-child-title .fa-heart-o{
            color:#ac2925;
        }
        .price-child-title a{
            text-decoration:none;
        }
    </style>
</head>

<body>
<div class="wrapper">
    <%@include file="/WEB-INF/jsp/includes/sidebar.jsp" %>
    <div class="main-panel">
        <%@include file="/WEB-INF/jsp/includes/title.jsp" %>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="input-group">
                            <select class="js-example-basic-single"  id="selectValue">
                                <c:forEach items="${idList}"  varStatus="status">
                                    <c:set var="id" value="${status.current}"/>
                                    <option value="${id}"> ${id}</option>
                                </c:forEach>
                            </select>
                            <span>
                                <button id="select" class="btn btn-default" type="button" >
                                    查询
                                </button>
                                <a href="${updateUrl}" class="btn btn-default" type="button" >
                                    刷新
                                </a>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <c:if test="${currentCoin != null}">
                            <div class="price-select" id ="price-select">
                                <div class="price-child-title">
                                    <span style="font-size: 20px;">${currentCoin.symbol}
                                        <i class="fa fa-plus" onclick="add('${currentCoin.id}')" aria-hidden="true"></i>
                                    </span>
                                    <br>
                                    <span style="font-size: 18px;">
                                        <fmt:formatNumber value="${currentCoin.priceCny}" minFractionDigits="4"/>CNY
                                    </span>
                                    <span style="color: ${currentCoin.percentChange24H >= 0 ? '#093' : 'red'}">
                                        (${currentCoin.percentChange24H}%)
                                    </span><br>
                                    <span style="font-size: 18px;">
                                        <fmt:formatNumber value="${currentCoin.priceBtc}" minFractionDigits="8"/>BTC
                                    </span>
                                </div>
                                <div class="price-child-detail">
                                    <div class="detail-child">排名<br><br><span style="font-size: 17px">${currentCoin.rank}</span></div>
                                    <div class="detail-child" style="border-right: 0;">交易量（24H）<br><br>
                                        <span style="font-size: 17px"><fmt:formatNumber value="${currentCoin.volumeCny24H}" pattern="#,#00.0#"/></span></div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <c:forEach items="${coinMarketCapList}" varStatus="status">
                            <c:set var="coinMarketCap" value="${status.current}"/>
                            <div class="price">
                                <div class="price-child">
                                    <div class="price-child-title">
                                        <span style="font-size: 20px;">${coinMarketCap.symbol}
                                            <div class="icon-arr">
                                                <a href="${detailUrl}?id=${coinMarketCap.id}" class="fa fa-eye"></a>
                                                <i class="fa fa-heart-o" onclick="rise('${coinMarketCap.id}')" aria-hidden="true"></i>
                                                <i class="fa fa-times" onclick="cancel('${coinMarketCap.relationId}')" aria-hidden="true"></i>
                                            </div>
                                        </span>
                                        <br>
                                        <span style="font-size: 18px;">
                                            <fmt:formatNumber value="${coinMarketCap.priceCny}" minFractionDigits="4"/>CNY
                                        </span>
                                        <span style="color: ${coinMarketCap.percentChange24H >= 0 ? '#093' : 'red'}">
                                            (${coinMarketCap.percentChange24H}%)
                                        </span><br>
                                        <span style="font-size: 18px;">
                                            <fmt:formatNumber value="${coinMarketCap.priceBtc}" minFractionDigits="8"/>BTC
                                        </span>
                                    </div>
                                    <div class="price-child-detail">
                                        <div class="detail-child">排名<br><br><span style="font-size: 17px">${coinMarketCap.rank}</span></div>
                                        <div class="detail-child" style="border-right: 0;">交易量（24H）<br><br>
                                            <span style="font-size: 17px">
                                                <fmt:formatNumber value="${coinMarketCap.volumeCny24H}" pattern="#,#00.0#"/>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/WEB-INF/jsp/includes/scriptOfBase.jsp" %>
<script src="/media/js/select2.min.js"></script>
<script>
    $(function () {
        var active = $("#Query");
        active.addClass("active");

        $('.js-example-basic-single').select2({
            placeholder: 'Select an option'
        });

        $("#select").click(function () {
            var symbol = $("#selectValue").val();
            window.location.href="${indexUrl}?coinNames="+symbol;
        });
    });
    function add(symbol) {
        window.location.href="/dailys/query/add?symbol="+symbol;
    }
    function cancel(relationId) {
        window.location.href="/dailys/query/remove?relationId="+relationId;
    }
    function rise(symbol) {
        window.location.href="/dailys/query/rise?symbol="+symbol;
    }
</script>
</html>