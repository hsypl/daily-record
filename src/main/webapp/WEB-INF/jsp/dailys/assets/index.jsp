<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/assets/update" var="updateUrl"/>
<s:url value="/dailys/assets/history" var="historyUrl"/>
<html>

<head>
    <title>Material Dashboard by Creative Tim</title>
    <%@include file="/WEB-INF/jsp/includes/linkOfHead.jsp" %>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <style>
        a{
            color: #9A9A9A;
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
                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="card card-stats">
                            <div class="card-header" data-background-color="orange">
                                <i class="fa fa-jpy" aria-hidden="true"></i>
                            </div>
                            <div class="card-content">
                                <p class="category">total assets</p>
                                <h3 class="title">${count}</h3>
                            </div>
                            <div class="card-footer">
                                <div class="stats">
                                    <i class="fa fa-refresh fa-2x" aria-hidden="true"></i>
                                    <a href="${updateUrl}" style="font-size: 20px">refresh</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="card card-stats">
                            <div class="card-header" data-background-color="green">
                                <i class="fa fa-btc" aria-hidden="true"></i>
                            </div>
                            <div class="card-content">
                                <p class="category">ico</p>
                                <h3 class="title">${count}</h3>
                            </div>
                            <div class="card-footer">
                                <div class="stats">
                                    <c:if test="${rise >=0}">
                                        <i class="fa fa-level-up fa-2x" style="color: green" aria-hidden="true"></i>
                                        <span style="font-size: 20px;color: green"><fmt:formatNumber value="${rise}" pattern="#,#0.0#"/>%</span>
                                    </c:if>
                                    <c:if test="${rise <0}">
                                        <i class="fa fa-level-down fa-2x" style="color: red" aria-hidden="true"></i>
                                        <span style="font-size: 20px;color: red"><fmt:formatNumber value="${rise}" pattern="#,#0.0#"/>%</span>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="card card-stats">
                            <div class="card-header" data-background-color="red">
                                <i class="fa fa-btc" aria-hidden="true"></i>
                            </div>
                            <div class="card-content">
                                <p class="category">currency</p>
                                <h3 class="title">164898</h3>
                            </div>
                            <div class="card-footer">
                                <div class="stats">
                                    <i class="fa fa-clock-o fa-2x" aria-hidden="true"></i> Last 24 Hours
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header card-chart" data-background-color="green">
                                <div class="ec-chart" style="width: auto;height: 400px;"
                                     id="dailyAssets"></div>
                            </div>
                            <div class="card-content">
                                <h4 class="title">资产统计</h4>
                            </div>
                            <div class="card-footer">
                                <div class="stats">
                                    <i class="fa fa-history fa-2x" aria-hidden="true"></i>
                                    <a href="${historyUrl}">查看历史</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="ec-chart" style="width: 100%;height: 600px" id="assetsPercent"></div>
                            <div class="card-content">
                                <h4 class="title">资产统计</h4>
                            </div>
                            <div class="card-footer">
                                <div class="stats">
                                    <i class="fa fa-clock-o fa-2x" aria-hidden="true"></i> 24小时之前
                                </div>
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
<script src="/media/js/echat/echarts.min.js"></script>
<script src="/media/js/echat/ecStat.min.js"></script>
<script type="text/javascript">
    $(function () {
        var active = $("#Assets");
        active.addClass("active");
    });


    // 基于准备好的dom，初始化echarts实例
    // 指定图表的配置项和数据
    option = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            bottom: '10',
            data: [${nameData}]
        },
        grid: {
            left: '3%',
            right: '15%',
            bottom : '20',
            top : '40',
            containLabel: true
        },
        series : [
            {
                name: '资产占比',
                type: 'pie',
                radius : '55%',
                center: ['50%', '40%'],
                data:[
                    <c:forEach items="${infoList}" varStatus="status">
                    <c:set var="info" value="${status.current}"/>
                    <c:if test="${!status.last}">
                    {value:${info.count}, name:'${info.symbol}'},
                    </c:if>
                    <c:if test="${status.last}">
                    {value:${info.count}, name:'${info.symbol}'}
                    </c:if>
                    </c:forEach>
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    var myChart = echarts.init(document.getElementById('assetsPercent'));
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);



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