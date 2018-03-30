<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<s:url value="/dailys/assets/index" var="assetsUrl"/>
<s:url value="/dailys/token/index" var="tokenUrl"/>
<s:url value="/dailys/stats/index" var="statsUrl"/>
<s:url value="/dailys/query/index" var="queryUrl"/>
<s:url value="/dailys/user/index" var="userUrl"/>
<s:url value="/dailys/change/index" var="changeUrl"/>
<s:url value="/dailys/util/index" var="utilUrl"/>
<s:url value="/dailys/monitor/index" var="monitorUrl"/>
<div class="sidebar" data-color="blue" data-image="/media/assets/img/sidebar-1.jpg">
    <div class="logo">
        <a href="http://www.creative-tim.com" class="simple-text">
            Creative Tim
        </a>
    </div>
    <div class="sidebar-wrapper">
        <div class="nav">
            <c:forEach items="${moduleTree.rootMenuList}" varStatus="menuStatus">
                <c:set var="menuInfo" value="${menuStatus.current}"/>
                <c:forEach items="${menuInfo.moduleList}" varStatus="moduleList">
                    <c:set var="module" value="${moduleList.current}"/>
                    <li id="${module.value}">
                        <a href="${module.inletUri}">
                            <i class="${module.icon}" aria-hidden="true"></i>
                            <p>${module.value}</p>
                        </a>
                    </li>
                </c:forEach>
            </c:forEach>
            <%--<li id="assets">--%>
                <%--<a href="${assetsUrl}">--%>
                    <%--<i class="fa fa-home" aria-hidden="true"></i>--%>
                    <%--<p>Assets</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li id="user">--%>
                <%--<a href="${userUrl}">--%>
                    <%--<i class="fa fa-user-o" aria-hidden="true"></i>--%>
                    <%--<p>User Profile</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li  id="token">--%>
                <%--<a href="${tokenUrl}">--%>
                    <%--<i class="fa fa-file-text" aria-hidden="true"></i>--%>
                    <%--<p>ico token</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li id="stats">--%>
                <%--<a href="${statsUrl}">--%>
                    <%--<i class="fa fa-line-chart" aria-hidden="true"></i>--%>
                    <%--<p>stats</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li id="query">--%>
                <%--<a href="${queryUrl}">--%>
                    <%--<i class="fa fa-usd" aria-hidden="true"></i>--%>
                    <%--<p>Icons</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li id="changeUrl">--%>
                <%--<a href="${changeUrl}">--%>
                    <%--<i class="fa fa-exchange" aria-hidden="true"></i>--%>
                    <%--<p>exchange</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li id="utilUrl">--%>
                <%--<a href="${utilUrl}">--%>
                    <%--<i class="fa fa-exchange" aria-hidden="true"></i>--%>
                    <%--<p>util</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li id="monitorUrl">--%>
                <%--<a href="${monitorUrl}">--%>
                    <%--<i class="fa fa-exchange" aria-hidden="true"></i>--%>
                    <%--<p>monitor</p>--%>
                <%--</a>--%>
            <%--</li>--%>
        </div>
    </div>
</div>
