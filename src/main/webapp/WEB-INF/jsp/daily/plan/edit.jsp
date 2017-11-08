<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<s:url value="/daily/plan/save" var="saveURL"/>
<s:url value="/daily/plan/index" var="indexURL"/>
<c:set var="model" value="planInfo"/>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <script src="/media/js/responsive-nav.js"></script>
    <script src="/media/js/jquery-3.2.1.min.js"></script>
    <script src="/media/js/bootstrap.min.js"></script>
    <script src="/media/js/calendar.js"></script>
    <link rel="stylesheet" href="/media/css/responsive-nav.css">
    <link rel="stylesheet" href="/media/css/bootstrap.min.css">
    <link rel="stylesheet" href="/media/css/calendar-blue.css">
    <script type="application/javascript">
        jQuery(document).ready(function ($) {
            Calendar.setup({
                // Id of the input field
                inputField: "plan-startTime",
                // Format of the input field
                ifFormat: "%Y-%m-%d",
                // Trigger for the calendar (button ID)
                button: "jform_effectiveDate_img",
                // Alignment (defaults to "Bl")
                align: "Tl",
                singleClick: true,
                firstDay: 0
            });
        });

        function changeDate() {
            var date = jQuery("#plan-startTime").val();
            var reg = new RegExp("-", "g");//g,表示全部替换。
            date = date.replace(reg, "/");
            var dateLong = Date.parse(new Date(date)) / 1000;
            jQuery("#startTime").val(dateLong);
        };

        $("#btn-save").click(function () {
            alert("123.");
        });
    </script>
</head>
<body>
<c:set var="showSave" value="true"/>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@include file="/WEB-INF/jsp/include/buttonHead.jsp"%>
<form:form commandName="planInfo" id="planInfo" action="${saveURL}" class="form-horizontal">
    <div class="form-group">
        <label for="plan-id" class="col-sm-1 control-label">任务Id</label>
        <div class="col-sm-10">
            <form:input path="id" id="plan-id" class="form-control" style="width: 10%" readonly="true" /></div>
    </div>
    <div class="form-group">
        <label for="plan-description" class="col-sm-1 control-label">描述</label>
        <div class="col-sm-10">
            <form:input path="description" class="form-control" id="plan-description"  placeholder="输入任务描述内容"/>
        </div>
    </div>
    <div class="form-group">
        <label for="plan-startTime" class="col-sm-1 control-label">开始时间</label>
        <div class="col-sm-10">
            <input type="text" id="plan-startTime"
                   style="width: 10%"
                   cssClass="input-medium hasTooltip"
                   readonly="true"
                   onchange="changeDate()"/>
            <button type="button" class="btn" id="jform_effectiveDate_img">
                <span class="icon-calendar"></span>
            </button>
            <form:input path="startTime" type="text" id="startTime" name="startTime" style="display:none"/>
        </div>
    </div>
    <div class="form-group">
        <label for="plan-completeTime" class="col-sm-1 control-label">结束时间</label>
        <div class="col-sm-10">
            <form:input path="completeTime" class="form-control" style="width: 10%" id="plan-completeTime" readonly="true" />
        </div>
    </div>
    <div class="form-group">
        <label for="plan-status" class="col-sm-1 control-label">结束时间</label>
        <div class="col-sm-10">
            <form:input path="status" class="form-control" style="width: 10%" id="plan-status" readonly="true" />
        </div>
    </div>
</form:form>
</body>
</html>
