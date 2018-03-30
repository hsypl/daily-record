<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/assets/history" var="historyUrl"/>
<s:url value="/dailys/assets/history/month" var="historyMonthUrl"/>
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
                <div class="dropdown" style="display:inline">
                    <button type="button" class="btn dropdown-toggle" id="yearMenu"
                            data-toggle="dropdown">
                        选择年份
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="yearMenu">
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="year" value="2017" >2017年</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="year" value="2018" >2018年</a>
                        </li>
                    </ul>
                </div>
                <div class="dropdown" style="display:inline">
                    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1"
                            data-toggle="dropdown">
                        选择月份
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-01-01" >1月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-02-01" >2月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-03-01" >3月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-04-01" >4月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-05-01" >5月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-06-01" >6月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-07-01" >7月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-08-01" >8月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-09-01" >9月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-10-01" >10月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-11-01" >11月份</a>
                        </li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" name="month" value="-12-01" >12月份</a>
                        </li>
                    </ul>
                </div>
                <div class="ec-chart" style="width: auto;height: 400px;" id="dailyAssets"></div>
            </div>
    </div>
</div>
</body>
<%@include file="/WEB-INF/jsp/includes/scriptOfBase.jsp" %>
<script src="/media/js/echat/echarts.min.js"></script>
<script src="/media/js/echat/ecStat.min.js"></script>
<script>
    $(function () {
        var active = $("#Assets");
        active.addClass("active");
        $("a[name='month']").bind("click",function () {
            var year = $("#yearMenu").text();
            $.get("${historyMonthUrl}?month="+year+$(this).attr("value"),function(data,status){
                if(data.month != undefined){
                    daily.xAxis[0].data = data.month;
                    daily.series[0].data =data.value;
                }else {
                    daily.xAxis[0].data = "0";
                    daily.series[0].data[0] = "0";
                }
                var myPoint = echarts.init(document.getElementById('dailyAssets'));
                myPoint.setOption(daily);
            });
        });
        $("a[name='year']").bind("click",function () {
            $("#yearMenu").text($(this).text());
        });
    });

    var dailyAssets = echarts.init(document.getElementById('dailyAssets'));
    var daily = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            left: '3%',
            right: '15%',
            bottom : '20',
            top : '40',
            containLabel: true
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        xAxis: [
            {
                type: 'category',
                name: '日期',
                axisTick: {
                    alignWithLabel: true
                },
                data: [<c:forEach items="${assetsMap}" varStatus="status">
                    <c:set var="assets" value="${status.current}"/>
                    <c:if test="${!status.last}">
                    ['${assets.key}'],
                    </c:if>
                    <c:if test="${status.last}">
                    ['${assets.key}']
                    </c:if>
                    </c:forEach>]
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '金額',
                position: 'left',
                //            type: 'value',
                axisLabel: {
                    formatter: '{value} ￥'
                }
            },
        ],
        series: [
            {
                name:'金额',
                type:'line',
                data:[<c:forEach items="${assetsMap}" varStatus="status">
                    <c:set var="assets" value="${status.current}"/>
                    <c:if test="${!status.last}">
                    ${assets.value},
                    </c:if>
                    <c:if test="${status.last}">
                    ${assets.value}
                    </c:if>
                    </c:forEach>]
            },
        ]
    };
    dailyAssets.setOption(daily);
    //    window.onresize = myChart.resize;
    window.onresize = dailyAssets.resize;
</script>
</html>