<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/user/add" var="saveURL"/>
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
                            <form:form commandName="userInfo" id="ico" action="${saveURL}" class="form-horizontal">
                            <div class="card-header" data-background-color="purple">
                                <%--<h4 class="title">${icoProjectInfo.symbol}</h4>--%>
                                <p class="category">修改数据</p>
                            </div>
                            <div class="card-content">
                                <div class="row">
                                    <form:hidden path="uid"/>
                                    <div class="col-md-5">
                                        <div class="form-group label-floating">
                                            <label class="control-label">用户名</label>
                                            <form:input path="username" class="form-control"  />
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group label-floating">
                                            <label class="control-label">姓名</label>
                                            <form:input path="name" class="form-control" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group label-floating">
                                            <label class="control-label">密码</label>
                                            <form:input type="password" path="password" class="form-control"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <form:select path="admin"  class="required" required="required">
                                            <form:option value="" label="- 是否管理员 -"/>
                                            <form:options items="${judgeEnumList}" itemValue="value" itemLabel="description"/>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="row" >
                                    <c:forEach items="${moduleList}" var="module">
                                        <div class="class_top">
                                            <span class="inside">
                                                <form:checkbox path="moduleIdList" value="${module.id}"/>
                                                ${module.value}
                                            </span>
                                        </div>
                                    </c:forEach>
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
        var active = $("#User");
        active.addClass("active");
    });
</script>
</html>