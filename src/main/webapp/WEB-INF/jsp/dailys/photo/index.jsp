<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <title>Material Dashboard by Creative Tim</title>
    <%@include file="/WEB-INF/jsp/includes/linkOfHead.jsp" %>
    <style>
        .photoContent{
            width: 500px;
            height: 640px;
            border: solid 10px;
        }
        .photoChild{
            float: left;
            width: 160px;
            height: 210px;
            border: solid 2px;
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
                    <div class="photoContent" id="photoContent">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/WEB-INF/jsp/includes/scriptOfBase.jsp" %>
<script>
    var empty = 9;
    var width_size = 3;
    var height_size = 3;

    $(function () {
        var active = $("#photo");
        active.addClass("active");
        $("#photoContent").append('<div class="photoChild" id="child1" onclick="change(this)" style="background: #0d47a1"></div>');
        $("#photoContent").append('<div class="photoChild" id="child2" onclick="change(this)" style="background: #ac2925"></div>');
        $("#photoContent").append('<div class="photoChild" id="child3" onclick="change(this)" style="background: #2b542c"></div>');
        $("#photoContent").append('<div class="photoChild" id="child4" onclick="change(this)" style="background: #1a237e"></div>');
        $("#photoContent").append('<div class="photoChild" id="child5" onclick="change(this)" style="background: #7c4dff"></div>');
        $("#photoContent").append('<div class="photoChild" id="child6" onclick="change(this)" style="background: #00acc1"></div>');
        $("#photoContent").append('<div class="photoChild" id="child7" onclick="change(this)" style="background: #000000"></div>');
        $("#photoContent").append('<div class="photoChild" id="child8" onclick="change(this)" style="background: #5d4037"></div>');
        $("#photoContent").append('<div class="photoChild" id="child9" onclick="change(this)" style="background: #e8f5e9"></div>');
    });

    function change(child) {
        var childId = child.id;
        var childTarget = $("#"+child.id);
        var childTargetBackGround = $("#"+child.id).css("background");
        var id = childId.substring(5,childId.length);
        var target = $("#child"+empty);
        console.log ( (Number(empty) + Number(height_size)));
        console.log ( id);
        if(((Number(empty) + Number(height_size))) == id ){
            childTarget.css("background", target.css("background"));
            target.css("background",childTargetBackGround);
            empty = id;
        }else if (((Number(empty) - Number(height_size))) == id){
            childTarget.css("background", target.css("background"));
            target.css("background",childTargetBackGround);
            empty = id;
        }else if ((empty - 1) == id){
            childTarget.css("background", target.css("background"));
            target.css("background",childTargetBackGround);
            empty = id;
        }else if (((Number(empty) + Number(1))) == id){
            childTarget.css("background", target.css("background"));
            target.css("background",childTargetBackGround);
            empty = id;
        }
    }

</script>
</html>