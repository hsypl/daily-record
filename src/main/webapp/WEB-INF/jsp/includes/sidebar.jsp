<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/assets/index" var="assetsUrl"/>
<s:url value="/dailys/token/index" var="tokenUrl"/>
<s:url value="/dailys/stats/index" var="statsUrl"/>
<s:url value="/dailys/query/index" var="queryUrl"/>
<s:url value="/dailys/user/index" var="userUrl"/>
<div class="sidebar" data-color="blue" data-image="/media/assets/img/sidebar-1.jpg">
    <div class="logo">
        <a href="http://www.creative-tim.com" class="simple-text">
            Creative Tim
        </a>
    </div>
    <div class="sidebar-wrapper">
        <ul class="nav">
            <li id="assets">
                <a href="${assetsUrl}">
                    <i class="material-icons">dashboard</i>
                    <p>Assets</p>
                </a>
            </li>
            <li id="user">
                <a href="${userUrl}">
                    <i class="material-icons">person</i>
                    <p>User Profile</p>
                </a>
            </li>
            <li  id="token">
                <a href="${tokenUrl}">
                    <i class="material-icons">content_paste</i>
                    <p>ico token</p>
                </a>
            </li>
            <li id="stats">
                <a href="${statsUrl}">
                    <i class="material-icons">library_books</i>
                    <p>stats</p>
                </a>
            </li>
            <li id="query">
                <a href="${queryUrl}">
                    <i class="material-icons">bubble_chart</i>
                    <p>Icons</p>
                </a>
            </li>
            <li >
                <a href="${userUrl}">
                    <i class="material-icons">location_on</i>
                    <p>Maps</p>
                </a>
            </li>
            <li>
                <a href="./notifications.html">
                    <i class="material-icons text-gray">notifications</i>
                    <p>Notifications</p>
                </a>
            </li>
            <li class="active-pro">
                <a href="upgrade.html">
                    <i class="material-icons">unarchive</i>
                    <p>Upgrade to PRO</p>
                </a>
            </li>
        </ul>
    </div>
</div>
