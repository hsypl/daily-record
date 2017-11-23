<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

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
            var active = $("#userIndex");
            active.addClass("active");
        })
    </script>
    <style>
        .col-sm-10{
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <table class="table table-striped">
        <caption> <h2>用戶列表</h2>
            <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                +
            </button>
        </caption>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" >
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            添加用戶
                        </h4>
                    </div>
                    <form class="form-horizontal" role="form" action="/daily/user/add" >
                    <div class="modal-body" style="height:300px;">
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">用户名</label>
                                <div class="col-sm-10">
                                    <input type="text" name="username" class="form-control" id="username" placeholder="请输入名字">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-10">
                                    <input type="password" name ="password" class="form-control" id="password" placeholder="请输入姓">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-10">
                                    <input type="text" name ="name" class="form-control" id="name" placeholder="请输入姓">
                                </div>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="submit" class="btn btn-primary">
                            提交更改
                        </button>
                    </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
        <thead>
        <tr>
            <th>用戶id</th>
            <th>账号</th>
            <th>用戶姓名</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userInfoList}" varStatus="stauts">
            <c:set var="userInfo" value="${stauts.current}"/>
            <tr>
                <td>${userInfo.uid}</td>
                <td>${userInfo.username}</td>
                <td>${userInfo.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

</html>
