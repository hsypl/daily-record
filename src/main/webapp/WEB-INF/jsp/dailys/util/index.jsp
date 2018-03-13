<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<html>

<head>
    <title>Material Dashboard by Creative Tim</title>
    <%@include file="/WEB-INF/jsp/includes/linkOfHead.jsp" %>
    <style>
        .my-input{
            font-size:1.4em;
            height:1.7em;
            border-radius:4px;
            width: 100px;
            border:1px solid #c8cccf;
            color:#6a6f77;
        }
        .selectType{
            background:#fafdfe;
            height:28px;
            width:180px;
            line-height:28px;
            border:1px solid #9bc0dd;
            -moz-border-radius:2px;
            -webkit-border-radius:2px;
            border-radius:2px;
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
                    <div class="col-md-5">
                        <div style="height: 100px">
                            USDT ：<input class="my-input" id="dash-usdt" type="text"/>
                            usdt-DASH ：<input class="my-input" id="dash-usdt-price" type="text"/>
                            btc-DASH ：<input class="my-input" id="dash-btc-price" type="text"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-1">
                        <select id ="selectType" class="selectType">
                            <option value="0">选择卖出类型</option>
                            <option value="1">USDT</option>
                            <option value="2">BTC</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="form-group label-floating">
                            <label class="control-label" style="color: green">卖出数量</label>
                            <input type ="text" id="sell-number" class="form-control"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group label-floating">
                            <label class="control-label" style="color: green">price</label>
                            <input type ="text" id="sell-price" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <span>
                            总价 ：<span id="sell-amount"></span>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            兑换Dash：<span id="sell-amount-dash"></span>
                        </span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="form-group label-floating">
                            <label class="control-label" style="color: green">买入数量</label>
                            <input type ="text" id="buy-number" class="form-control"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group label-floating">
                            <label class="control-label" style="color: green">price</label>
                            <input type ="text" id="buy-price" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                         <span>
                            总价 ：<span id="buy-amount"></span>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            需要Dash：<span id="buy-amount-dash"></span>
                        </span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header" data-background-color="blue">
                                <h4 class="title">买单</h4>
                            </div>
                            <div class="card-content table-responsive">
                                <table class="table">
                                    <thead class="text-primary">
                                    <th>价格</th>
                                    <th>数量</th>
                                    <th>总额</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${buyList}" varStatus="stauts">
                                        <c:set var="buy" value="${stauts.current}"/>
                                        <tr>
                                            <td><fmt:formatNumber type="number" value="${buy['Price']} " maxFractionDigits="8"/></td>
                                            <td>${buy['Volume']}</td>
                                            <td>${buy['Total']} Btc</td>
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
        var active = $("#utilUrl");
        active.addClass("active");
    });

    function getPrice() {
        var type = $("#selectType").val();
        if(type == 0){
            alert("选择卖出类型");
        }else if(type == 1){
            return $("#dash-usdt-price").val();
        }else {
            return $("#dash-btc-price").val();
        }
    }

    $('#sell-number').bind('input propertychange', function() {
        var sellAmount = $(this).val()*$("#sell-price").val();
        $('#sell-amount').html(sellAmount);
        var price = getPrice();
        var dashAmount = sellAmount/price;
        $('#sell-amount-dash').html(dashAmount);
    });
    $('#sell-price').bind('input propertychange', function() {
        var buyAmount = $(this).val()*$("#sell-number").val();
        var price = getPrice();
        $('#sell-amount').html(buyAmount);
        var dashAmount = buyAmount/price;
        $('#sell-amount-dash').html(dashAmount);
    });$

    ('#buy-number').bind('input propertychange', function() {
        var buyAmount = $(this).val()*$("#buy-price").val();
        $('#buy-amount').html(buyAmount);
        var dashAmount = buyAmount/$("#dash-btc-price").val();
        $('#buy-amount-dash').html(dashAmount);
    });
    $('#buy-price').bind('input propertychange', function() {
        var buyAmount = $(this).val()*$("#buy-number").val();
        $('#buy-amount').html(buyAmount);
        var dashAmount = buyAmount/$("#dash-btc-price").val();
        $('#buy-amount-dash').html(dashAmount);
    });
</script>
</html>