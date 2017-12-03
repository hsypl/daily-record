<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<s:url value="/daily/reader/index" var="readerIndex"/>
<s:url value="/daily/currency/ico/index" var="icoIndex"/>
<s:url value="/daily/currency/price/index" var="priceIndex"/>
<s:url value="/daily/plan/index" var="planIndex"/>
<s:url value="/daily/user/index" var="userIndex"/>
<s:url value="/daily/currency/index" var="currencyIndex"/>
<s:url value="/daily/currency/history/index" var="historyIndex"/>
<link href="<s:url value="/media/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
<style>
    .personal{
        float: left;
        margin-left: 200px;
        color: #2b669a;
    }
</style>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">日常</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li id="readerIndex"><a href=${readerIndex}>阅读</a></li>
                <li id="planIndex"> <a href="${planIndex}">任务</a></li>
                <li id="userIndex"> <a href="${userIndex}">用戶中心</a></li>
                <li class="dropdown" id="currencyIndex">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        数字货币
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${icoIndex}">Ico join</a></li>
                        <li class="divider"></li>
                        <li><a href="${currencyIndex}">currency</a></li>
                        <li class="divider"></li>
                        <li><a href="${priceIndex}">price</a></li>
                        <li class="divider"></li>
                        <li><a href="${historyIndex}">Ico href</a></li>
                        <li class="divider"></li>
                        <li><a href="#">另一个分离的链接</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="personal" style="width: 300px">
                <i class="fa fa-user-circle fa-3x" aria-hidden="true" ></i>
                <span style="font-size: 20px;">${userInfo.name}</span>
            <a href="/logout">
                <i class="fa fa-stop fa-2x" aria-hidden="true" style="margin-left:30px"></i>
                <span style="font-size: 20px;">退出</span>
            </a>
        </div>
    </div>
</nav>
