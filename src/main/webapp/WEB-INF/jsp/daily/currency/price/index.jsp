<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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

            $("#add").click(function () {
                var name = $('#selectValue').val();
                $("#ul").append("<li><h3><span class='label label-primary' >"+name + " <span class='glyphicon glyphicon-remove'></span></span></h3></li>")
                $(".glyphicon").click(function () {
                    $(this).parent().parent().remove();
                });
            });
            $(".glyphicon").click(function () {
                $(this).parent().parent().remove();
            });

            $("#select").click(function () {
                var icon_arr = $(".label").text();
                $("#coin_names").val(icon_arr);
                $("#submit").submit();
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
                <c:forEach items="${allList}"  varStatus="status">
                    <c:set var="all" value="${status.current}"/>
                    <option value="${all.name}"> ${all.name}</option>
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
    <div class="content" style="width: 600px">
        <ul id="ul" class="list-inline" style="margin-left: 5px">
            <c:forEach items="${currentList}" varStatus="status">
                <c:set var="current" value="${status.current}"/>
                <li>
                    <h3><span class="label label-primary" >${current.name} <span class="glyphicon glyphicon-remove"></span></span></h3>
                </li>
            </c:forEach>
    </div>
    <form id ="submit" action="${indexUrl}">
        <input type="text" name="coin_names" id="coin_names" style="display: none">
    </form>
<div class="price-content" style="width: 600px">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>名称</th>
            <th>美元</th>
            <th>人民币</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${infoList}" varStatus="status">
            <c:set var="info" value="${status.current}"/>
            <tr>
                <td>${info.name}</td>
                <td>${info.usdPrice}</td>
                <td>${info.cnyPrice}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<style>
    .glyphicon {
        left: 4px;top: 3px
    }
    span.glyphicon:hover{
        color: red;
    }
</style>
</html>
