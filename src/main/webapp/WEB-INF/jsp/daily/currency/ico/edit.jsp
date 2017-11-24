<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<s:url value="/daily/currency/ico/save" var="saveURL"/>
<s:url value="/daily/currency/ico/index" var="indexURL"/>
<c:set var="model" value="ico"/>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <script src="/media/js/responsive-nav.js"></script>
    <script src="/media/js/jquery-3.2.1.min.js"></script>
    <script src="/media/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/media/css/responsive-nav.css">
    <link rel="stylesheet" href="/media/css/bootstrap.min.css">
</head>
<body>
<c:set var="showSave" value="true"/>
<c:set var="showCancel" value="true"/>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@include file="/WEB-INF/jsp/include/buttonHead.jsp"%>
<form:form commandName="icoProjectInfo" id="ico" action="${saveURL}" class="form-horizontal">
    <div class="form-group">
        <label for="ico-id" class="col-sm-1 control-label">项目Id</label>
        <div class="col-sm-10">
            <form:input path="id" id="ico-id" class="form-control" style="width: 10%" readonly="true" /></div>
    </div>
    <div class="form-group">
        <label for="ico-name" class="col-sm-1 control-label">项目名称</label>
        <div class="col-sm-10">
            <form:input path="name" class="form-control" id="ico-name"  placeholder="输入项目名称"/>
        </div>
    </div>
    <div class="form-group">
        <label for="ico-name" class="col-sm-1 control-label">网站地址</label>
        <div class="col-sm-10">
            <form:input path="website" type="text" class="form-control" id="website" name="website" placeholder="输入项目网址"/>
        </div>
    </div>
    <div class="form-group">
        <label for="ico-price" class="col-sm-1 control-label">投入金额</label>
        <div class="col-sm-10">
            <form:input path="price" class="form-control" style="width: 10%" id="ico-price" placeholder="投资金额" />
        </div>
    </div>
    <div class="form-group">
        <label for="ico-symbol" class="col-sm-1 control-label">代币符号</label>
        <div class="col-sm-10">
            <form:input path="symbol" class="form-control" style="width: 10%" id="ico-symbol" placeholder="代币符号" />
        </div>
    </div>
    <div class="form-group">
        <label for="ico-number" class="col-sm-1 control-label">代币数量</label>
        <div class="col-sm-10">
            <form:input path="number" class="form-control" style="width: 10%" id="ico-number" placeholder="代币数量" />
        </div>
    </div>
    <div class="form-group">
        <label for="ico-remark" class="col-sm-1 control-label">备注</label>
        <div class="col-sm-10">
            <form:input path="remark" class="form-control" style="width: 10%" id="ico-remark" placeholder="备注" />
        </div>
        <form:hidden path="uid" value="${userInfo.uid}"/>
    </div>

</form:form>
</body>
</html>
