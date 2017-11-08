<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<s:url value="/daily/reader/index" var="readerIndex"/>
<s:url value="/daily/currency/ico/index" var="icoIndex"/>
<s:url value="/daily/currency/price/index" var="priceIndex"/>
<s:url value="/daily/plan/index" var="planIndex"/>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">日常</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li id="readerIndex"><a href=${readerIndex}>阅读</a></li>
                <li id="planIndex"> <a href="${planIndex}">任务</a></li>
                <li class="dropdown" id="currencyIndex">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        数字货币
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${icoIndex}">Ico join</a></li>
                        <li class="divider"></li>
                        <li><a href="#">currency</a></li>
                        <li class="divider"></li>
                        <li><a href="${priceIndex}">price</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Ico href</a></li>
                        <li class="divider"></li>
                        <li><a href="#">另一个分离的链接</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
