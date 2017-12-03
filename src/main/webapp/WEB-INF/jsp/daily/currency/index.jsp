<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<s:url value="/daily/currency/history/index" var="historyUrl"/>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/media/js/responsive-nav.js"></script>
    <script src="/media/js/jquery-3.2.1.min.js"></script>
    <script src="/media/js/bootstrap.min.js"></script>
    <script src="/media/js/echat/echarts.min.js"></script>
    <script src="/media/js/echat/ecStat.min.js"></script>
    <link rel="stylesheet" href="/media/css/responsive-nav.css">
    <link rel="stylesheet" href="/media/css/bootstrap.min.css">
    <%@ include file="/WEB-INF/jsp/include/header.jsp"%>

    <script type="application/javascript">
    </script>

</head>
<body>
<div>
    <div id="main" style="width: 800px;height:600px;float: left"></div>
    <div style="width: 1000px;float: left">
        <div style="float: left;margin-left: 95px">
            <div class="dropdown">
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
        </div>
    <div id="main1" style="width: 1000px;height:600px;float: left"></div>
    </div>
</div>
<script type="text/javascript">
    var myPoint = echarts.init(document.getElementById('main1'));
    // 使用刚指定的配置项和数据显示图表。
    $(function () {
        var active = $("#currencyIndex");
        active.addClass("active");
        $("a[name='month']").bind("click",function () {
            $.get("/daily/currency/update?month="+$(this).attr("value"),function(data,status){
                if(data.month != undefined){
                    daily.xAxis[0].data = data.month;
                    daily.series[0].data =data.value;
                }else {
                    daily.xAxis[0].data = "0";
                    daily.series[0].data[0] = "0";
                }
                var myPoint = echarts.init(document.getElementById('main1'));
                myPoint.setOption(daily);
            });
        });
    });

    // 基于准备好的dom，初始化echarts实例
    // 指定图表的配置项和数据
    option = {
        title : {
            text: '占比统计',
//            subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: [${nameData}]
        },
        series : [
            {
                name: '资产占比',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
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
    var myChart = echarts.init(document.getElementById('main'));
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);


    var data = [
        <c:forEach items="${assetsMap}" varStatus="status">
        <c:set var="assets" value="${status.current}"/>
        <c:if test="${!status.last}">
        ['${assets.key}', ${assets.value}],
        </c:if>
        <c:if test="${status.last}">
        ['${assets.key}', ${assets.value}]
                </c:if>
                </c:forEach>

    ];

    // See https://github.com/ecomfe/echarts-stat
//    var myRegression = ecStat.regression('exponential', data);
//
//    myRegression.points.sort(function(a, b) {
//        return a[0] - b[0];
//    });



    var daily = {
        title: {
            text: '资产日报',
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
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
    myPoint.setOption(daily);
</script>

<table class="table">
    <h3>代价数据</h3>
    <a href="${updateUrl}" class="btn btn-default" >
        更新
    </a>
    <thead>
    <tr>
        <th>名称</th>
        <th>排名</th>
        <th>价格（CNY）</th>
        <th>价格（USD）</th>
        <th>价格（BTC）</th>
        <th>24小时涨幅</th>
        <th>一星期涨幅</th>
        <th>24小时交易量</th>
        <th>最后更新时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${coinMarketCapList}" varStatus="status">
        <c:set var="coinMarketCap" value="${status.current}"/>
        <tr>
            <td>${coinMarketCap.name}</td>
            <td>${coinMarketCap.rank}</td>
            <td><fmt:formatNumber value="${coinMarketCap.priceCny}" minFractionDigits="4"/></td>
            <td><fmt:formatNumber value="${coinMarketCap.priceUsd}" minFractionDigits="4"/></td>
            <td><fmt:formatNumber value="${coinMarketCap.priceBtc}" minFractionDigits="8"/></td>
            <c:if test="${coinMarketCap.percentChange24H >= 0}">
                <td >
                    <span style="color:  #093;">${coinMarketCap.percentChange24H}%</span>
                </td>
            </c:if>
            <c:if test="${coinMarketCap.percentChange24H < 0}">
                <td>
                    <span style="color: red">${coinMarketCap.percentChange24H}%</span>
                </td>
            </c:if>
            <c:if test="${coinMarketCap.percentChange7D >= 0}">
                <td >
                    <span style="color: #093">${coinMarketCap.percentChange7D}%</span>
                </td>
            </c:if>
            <c:if test="${coinMarketCap.percentChange7D < 0}">
                <td>
                    <span style="color: red">${coinMarketCap.percentChange7D}%</span>
                </td>
            </c:if>
            <td><fmt:formatNumber value="${coinMarketCap.volumeCny24H}" pattern="#,#00.0#"/></td>
            <td>${coinMarketCap.formatCreateTime}</td>
            <td>
                <a class="btn btn-primary" href="${historyUrl}?id=${coinMarketCap.id}" role="button">查看详情</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
