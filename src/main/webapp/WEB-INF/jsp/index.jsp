<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="/media/login/css/style.css" />
    <!-- Custom Theme files -->
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords"
          content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <!--Google Fonts-->
    <%--<link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400'--%>
          <%--rel='stylesheet' type='text/css'>--%>
    <%--<link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet'--%>
          <%--type='text/css'>--%>
    <!--Google Fonts-->
</head>
<body>
<div class="login">
    <h2>Acced Form</h2>
    <div class="login-top" >
        <h1>LOGIN FORM</h1>
        <form action="<c:url value='/login'/>" method="post">
            <input type="text" name="username" >
            <input type="password" name="password" >
        <div class="forgot">
            <a href="#">forgot Password</a>
            <input type="submit" value="Login">
        </div>
        </form>
        ${param.error }
    </div>
    <div class="login-bottom">
        <h3>New User &nbsp;<a href="#">Register</a>&nbsp Here</h3>
    </div>
</div>


</body>
</html>