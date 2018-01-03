<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/change/add" var="saveURL"/>
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
                        <div class="card">
                            <form:form commandName="changeLog" id="ico" action="${saveURL}" class="form-horizontal">
                            <div class="card-header" data-background-color="purple">
                                <%--<h4 class="title">${icoProjectInfo.symbol}</h4>--%>
                                <p class="category">修改数据</p>
                            </div>
                                <form:hidden path="id" />
                            <div class="card-content">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group label-floating">
                                            <label class="control-label">代币</label>
                                            <form:select path="symbol" class="js-example-basic-single"  id="selectValue">
                                                <c:forEach items="${idList}"  varStatus="status">
                                                    <c:set var="id" value="${status.current}"/>
                                                    <c:if test="${id == changeLog.symbol}">
                                                        <option value="${id}" selected="selected"> ${id}</option>
                                                    </c:if>
                                                    <c:if test="${id != changeLog.symbol}">
                                                        <option value="${id}"> ${id}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group label-floating">
                                            <label class="control-label">价格</label>
                                            <form:input path="price" class="form-control" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group label-floating">
                                            <label class="control-label">数量</label>
                                            <form:input path="number" class="form-control" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group label-floating">
                                            <label class="control-label">总额</label>
                                            <form:input path="amount" class="form-control" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group label-floating">
                                            <label class="control-label">类型</label>
                                            <form:select path="type" id="jform_city" class="form-control" required="required">
                                                <form:option value="" label="- 选择操作类型 -"/>
                                                <form:options items="${ChangeTypeEnum}" itemValue="value" itemLabel="description"/>
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group label-floating">
                                            <label class="control-label">备注</label>
                                            <form:input path="remark" class="form-control" />
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
<script src="/media/js/select2.min.js"></script>
<script>
    $(function () {
        var active = $("#changeUrl");
        active.addClass("active");

        $('.js-example-basic-single').select2({
            placeholder: 'Select an option'
        });
    });
</script>
</html>