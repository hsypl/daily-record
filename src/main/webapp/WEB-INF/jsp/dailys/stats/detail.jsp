<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/stats/index" var="indexUrl"/>
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
            <a href="${indexUrl}" class="btn btn-success" type="button" style="margin-left: 10px">
                返回
            </a>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="card">
                                <div class="ec-chart" style="width: 100%;height: 400px" id="historyStats"></div>
                            <div class="card-content">
                                <h4 class="title">交易量</h4>
                            </div>
                            <div class="card-footer">
                                <div class="stats">
                                    <i class="material-icons">access_time</i> 24小时之前
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
<script>
    $(function () {
        var active = $("#stats");
        active.addClass("active");

        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data:['价格','交易量']
            },
            xAxis: [
                {
                    type: 'category',
                    data: ${dayList},
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '价格',
                    axisLabel: {
                        formatter: '{value} $'
                    }
                },
                {
                    type: 'value',
                    left: 'right',
                    name: '交易量',
                    axisLabel: {
                        formatter: '{value} $'
                    }
                }
            ],
            series: [
                {
                    name:'交易量',
                    type:'bar',
                    yAxisIndex: 1,
                    data:${volumeList}
                },
                {
                    name:'价格',
                    type:'line',
                    yAxisIndex: 0,
                    data:${priceList}
                }
            ]
        };
        var myChart = echarts.init(document.getElementById('historyStats'));
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.onresize = myChart.resize;2
    });
</script>
</html>