<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/assets/index" var="assetsUrl"/>
<nav class="navbar navbar-transparent navbar-absolute">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${assetsUrl}"> assets home </a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell" aria-hidden="true"></i>
                        <span class="notification">5</span>
                        <p class="hidden-lg hidden-md">Notifications</p>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#">Mike John responded to your email</a>
                        </li>
                        <li>
                            <a href="#">You have 5 new tasks</a>
                        </li>
                        <li>
                            <a href="#">You're now friend with Andrew</a>
                        </li>
                        <li>
                            <a href="#">Another Notification</a>
                        </li>
                        <li>
                            <a href="#">Another One</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#pablo" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-user" aria-hidden="true"></i>
                        ${userInfo.username}
                    </a>
                </li>
                <li>
                    <a href="/logout">
                        <i class="fa fa-sign-in" aria-hidden="true"></i>
                        <p class="hidden-lg hidden-md">exit</p>
                    </a>
                </li>

            </ul>
            <form class="navbar-form navbar-right" role="search">
                <%--<div class="form-group  is-empty">--%>
                    <%--<input type="text" class="form-control" placeholder="Search">--%>
                    <%--<span class="material-input"></span>--%>
                <%--</div>--%>
                <%--<button type="submit" class="btn btn-white btn-round btn-just-icon">--%>
                    <%--<i class="material-icons">search</i>--%>
                    <%--<div class="ripple-container"></div>--%>
                <%--</button>--%>
            </form>
        </div>
    </div>
</nav>
