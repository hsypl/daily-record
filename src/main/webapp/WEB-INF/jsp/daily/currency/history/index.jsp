<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/daily/currency/ico/edit" var="editUrl"/>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <script src="/media/js/jquery-3.2.1.min.js"></script>
    <script src="/media/js/bootstrap.min.js"></script>
    <script src="/media/js/echat/echarts.min.js"></script>
    <link rel="stylesheet" href="/media/css/responsive-nav.css">
    <link rel="stylesheet" href="/media/css/bootstrap.min.css">
    <script>

        $(function () {

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

            var myChart = echarts.init(document.getElementById('main'));
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        });
    </script>
</head>
<body>
<div style="text-align: center"><span style="font-size: 40px">${id}</span></div>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">菜鸟教程</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">iOS</a></li>
                <li><a href="#">SVN</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Java
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">jmeter</a></li>
                        <li><a href="#">EJB</a></li>
                        <li><a href="#">Jasper Report</a></li>
                        <li class="divider"></li>
                        <li><a href="#">分离的链接</a></li>
                        <li class="divider"></li>
                        <li><a href="#">另一个分离的链接</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div id="main" style="width: 80%;height:600px;margin:0 auto;text-align: center"></div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>
</body>
</html>
