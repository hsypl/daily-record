<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<s:url value="/daily/currency/price/index" var="indexUrl"/>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/media/js/responsive-nav.js"></script>
    <script src="/media/js/jquery-3.2.1.min.js"></script>
    <script src="/media/js/bootstrap.min.js"></script>
    <script src="/media/js/select2.min.js"></script>
    <link rel="stylesheet" href="/media/css/select2.min.css">
    <link rel="stylesheet" href="/media/css/responsive-nav.css">
    <link rel="stylesheet" href="/media/css/bootstrap.min.css">

    <%@ include file="/WEB-INF/jsp/include/header.jsp" %>
    <script type="text/javascript">
        $(function () {
            var active = $("#currencyIndex");
            active.addClass("active");

            $('.js-example-basic-single').select2({
                placeholder: 'Select an option'
            });

            $("#select").click(function () {
                var symbol = $("#selectValue").val();
                window.location.href="/daily/currency/price/index?coinNames="+symbol;
            })
            $("#add").click(function () {
                var symbol = $("#selectValue").val();
                window.location.href="/daily/currency/price/add?coinNames="+symbol;
            })
        });
    </script>
    <style>
        .select2-container--default .select2-selection--single {
            height: 34px;
            font-size: large;
        }
    </style>
</head>
<body>
<div class="main" style="width: 600px;height: 50px" >
    <div class="col-lg-1" style="width: 350px">
        <div class="input-group">
            <select class="js-example-basic-single" id="selectValue">
                <c:forEach items="${idList}"  varStatus="status">
                    <c:set var="id" value="${status.current}"/>
                    <option value="${id}"> ${id}</option>
                </c:forEach>
            </select>
            <span class="input-group-btn">
						<button id="add" class="btn btn-default" type="button">
							添加
						</button>
            </span>
            <div class="main-left" style="margin-left: 5px" >
                <button id="select" class="btn btn-default" type="button" >
                    查询
                </button>
            </div>
        </div><!-- /input-group -->
    </div>
</div>
<!-- div select begin-->
<c:if test="${coinMarketCap != null}">
<div class="price-select" id ="price-select">
    <div class="price-child-title">
        <span style="font-size: 20px;">${currentCoin.symbol}</span>
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
<!-- div select end-->

<div class="content-price">
    <c:forEach items="coinMarketCapList" varStatus="status">
        <c:set var="coinMarket" value="${status.current}"/>
        <div class="price">
            <div class="price-child">
                <div class="price-child-title">
                    <span style="font-size: 20px;">${coinMarketCap.symbol}</span>
                    <span>ss</span>
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
</body>
<style>
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
        margin-left: 10px;
        width: 336px;
        height: 193px;
        border:2px solid #5e5e5e;
        border-radius:10px;
    }
    .price-child-title{
        margin-left: 10px;
        float:left;
        width:67%;
        border: 0 solid #000;
        text-align:left;
        padding:5px 0;
        line-height:25px;
    }
    .glyphicon {
        left: 4px;top: 3px
    }
    span.glyphicon:hover{
        color: red;
    }
</style>
</html>
