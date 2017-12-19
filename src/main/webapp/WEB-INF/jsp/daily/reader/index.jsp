<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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
            margin: 50px;
        }
        img {
            max-width: 100%; /* This rule is very important, please do not ignore this! */
        }
    </style>
</head>
<body>
<div class="container">
    <img src="/media/image/friend.png">
</div>
<%--<a data-toggle="modal" href="/daily/currency/history/index?id=status" data-target="#modal">Click me</a>--%>
<a data-toggle="modal" href="/daily/currency/history/index?id=status" data-target="#myModal">Click me !</a>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Modal title</h4>

            </div>
            <div class="modal-body"><div class="te"></div></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</body>
<script>
    $('.container > img').cropper({
        aspectRatio: 16 / 9,
        crop: function(data) {
            // Output the result data for cropping image.
        }
    });
</script>
</html>
