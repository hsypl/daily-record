<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/monitor/save" var="saveURL"/>
<s:url value="/dailys/monitor/delete" var="deleteURL"/>
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
                    <div class="col-md-8">
                        <div class="card">
                            <form:form commandName="monitorSymbol" id="ico" action="${saveURL}" class="form-horizontal">
                            <div class="card-header" data-background-color="purple">
                                <h4 class="title">${monitorSymbol.symbol}</h4>
                                <p class="category">修改数据</p>
                            </div>
                            <div class="card-content">
                                    <form:hidden path="id" />
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group label-floating">
                                                <label class="control-label">代币符号</label>
                                                <form:input path="symbol" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group label-floating">
                                                <label class="control-label">交易所</label>
                                                <form:input path="exchange" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group label-floating">
                                                <label class="control-label">卖价</label>
                                                <form:input path="sellPrice" class="form-control"  />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group label-floating">
                                                <label class="control-label">买价</label>
                                                <form:input path="buyPrice" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group label-floating">
                                                <label class="control-label">交易类型</label>
                                                <form:input path="type" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary pull-right">Update</button>
                                    <div class="clearfix"></div>
                                </form:form>
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
        var active = $("#Monitor");
        active.addClass("active");
    });
</script>
</html>