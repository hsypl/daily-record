<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/user/add" var="addUrl"/>
<s:url value="/dailys/user/edit" var="editUrl"/>
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
                <a href="${editUrl}" class="btn btn-success" type="button" style="margin-left: 10px">
                    新增
                </a>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header" data-background-color="blue">
                                <h4 class="title">用户列表</h4>
                            </div>
                            <div class="card-content table-responsive">
                                <table class="table">
                                    <thead class="text-primary">
                                    <th>用戶id</th>
                                    <th>账号</th>
                                    <th>用戶姓名</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${userInfoList}" varStatus="stauts">
                                        <c:set var="userInfo" value="${stauts.current}"/>
                                        <tr>
                                            <td>${userInfo.uid}</td>
                                            <td>${userInfo.username}</td>
                                            <td>${userInfo.name}</td>
                                            <td>
                                                <a href="${editUrl}?id=${userInfo.uid}" rel="tooltip" title="Edit symbol" class="btn btn-primary btn-simple btn-xs">
                                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                </a>
                                            </td>
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
        var active = $("#user");
        active.addClass("active");
    });
</script>
</html>