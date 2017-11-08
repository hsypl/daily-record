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
    <link rel="stylesheet" href="/media/css/responsive-nav.css">
    <link rel="stylesheet" href="/media/css/bootstrap.min.css">

    <%@ include file="/WEB-INF/jsp/include/header.jsp" %>
    <script type="text/javascript">
        $(function () {
            var active = $("#currencyIndex");
            active.addClass("active");

            $("#add").click(function () {
                var name = $('#coin_name').val();
                $("#ul").append("<li><h3><span class='label label-primary' >"+name + "<span class='glyphicon glyphicon-remove'></span></span></h3></li>")
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
</head>
<body>
<div class="main" style="width: 600px;height: 50px" >
    <div class="col-lg-1" style="width: 200px">
        <div class="input-group">
            <input type="text" id="coin_name" class="form-control">
            <span class="input-group-btn">
						<button id="add" class="btn btn-default" type="button">
							添加
						</button>
					</span>
        </div><!-- /input-group -->
    </div>
    <div class="main-left">
        <button id="select" class="btn btn-default" type="button" >
            查询
        </button>
    </div>
</div>
    <div class="content" style="width: 600px">
        <ul id="ul" class="list-inline" style="margin-left: 5px">
        <li>
        <h3><span class="label label-primary" >dash <span class="glyphicon glyphicon-remove"></span></span></h3>
        </li>
        <li><h3><span class="label label-primary" >lisk <span class="glyphicon glyphicon-remove"></span></span></h3></li>
        </ul>
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
                <td>${info.USAPrice}</td>
                <td>${info.CNYPrice}</td>
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
