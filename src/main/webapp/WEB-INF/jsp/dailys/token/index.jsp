<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/token/edit" var="editUrl"/>
<s:url value="/dailys/token/save" var="saveURL"/>
<s:url value="/dailys/token/index" var="listUrl"/>
<s:url value="/dailys/token/delete" var="deleteURL"/>
<s:url value="/dailys/token/sync" var="syncURL"/>
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
                <a href="${syncURL}" class="btn btn-success" type="button" style="margin-left: 10px">
                    同步
                </a>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header" data-background-color="blue">
                                <h4 class="title">ico 项目记录</h4>
                                <%--<p class="category">Here is a subtitle for this table</p>--%>
                            </div>
                            <div class="card-content table-responsive">
                                <form:form commandName="queryFilter"  id ="listForm"  action="${listUrl}">
                                <table class="table">
                                    <thead class="text-primary">
                                    <th>项目名称</th>
                                    <th>投入金额</th>
                                    <th>代币符号</th>
                                    <th>数量</th>
                                    <th>现价</th>
                                    <th>总额</th>
                                    <th>收益</th>
                                    <th>备注</th>
                                    <th>操作</th>
                                    </thead>
                                    <tfoot>
                                    <tr>
                                        <c:set var="columnCount" value="6"/>
                                        <td colspan="${columnCount}">
                                            <%@include file="/WEB-INF/jsp/include/pagination.jsp"%>
                                        </td>
                                    </tr>
                                    </tfoot>
                                    <tbody>
                                    <c:forEach items="${icoProjectInfoList}" varStatus="status">
                                        <c:set var="icoProjectInfo" value="${status.current}"/>
                                        <tr>
                                            <td>${icoProjectInfo.name}</td>
                                            <td>${icoProjectInfo.price}</td>
                                            <td>${icoProjectInfo.symbol}</td>
                                            <td>${icoProjectInfo.number}</td>
                                            <td><fmt:formatNumber value="${icoProjectInfo.nowPrice}" minFractionDigits="3"/></td>
                                            <td>${icoProjectInfo.count}</td>
                                            <td><fmt:formatNumber value="${icoProjectInfo.percent}" minFractionDigits="0"/></td>
                                            <td>${icoProjectInfo.remark}</td>
                                            <td>
                                                <a href="${editUrl}?id=${icoProjectInfo.id}" rel="tooltip" title="Edit symbol" class="btn btn-primary btn-simple btn-xs">
                                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                </a>
                                                <a href="${deleteUrl}?id=${icoProjectInfo.id}" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
                                                    <i class="fa fa-times" aria-hidden="true"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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
        var active = $("#Token");
        active.addClass("active");

        $("[id^='pagination-']").bind("click", (function () {
            var paginationId = $(this).attr("id");
            var number = paginationId.substring(11, paginationId.length);
            if(number == "last"){
                number = 10000;
            }
            if(number == "first"){
                number = 1;
            }
            $("#pageNumber").val(number);
            $("#listForm").submit();
        }))
    });
</script>
</html>