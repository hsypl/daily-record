<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/media/js/responsive-nav.js"></script>
    <script src="/media/js/jquery-3.2.1.min.js"></script>
    <script src="/media/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/media/css/responsive-nav.css">
    <link rel="stylesheet" href="/media/css/bootstrap.min.css">
    <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
    <script type="application/javascript">
        $(function () {
            var active = $("#readerIndex");
            active.addClass("active");
        })
    </script>
    <style>
        .book{
            width : 256px;
            height: 500px;
            border: 1px sienna;
            background-color: #1b6d85;
            float: left;
            margin: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="book">

    </div>
    <div class="book">

    </div>
    <div class="book">

    </div>
</div>
</body>
</html>
