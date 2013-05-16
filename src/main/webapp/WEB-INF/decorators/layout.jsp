<%--
  Created by IntelliJ IDEA.
  User: blue
  Date: 2010-8-25
  Time: 14:30:24
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%-- spring security --%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.blue.taskflow.rest.security.OperatorDetails" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- 从被装饰页面获取title标签内容,并设置默认值-->
    <title><decorator:title default="系统主操作区模板"/></title>
    <style type="text/css" title="currentStyle">
        @import "${ctx}/css/yui/reset.css";
        @import "${ctx}/css/yui/base.css";
        @import "${ctx}/css/yui/fonts.css";
        @import "${ctx}/css/yui/grids.css";
        @import "${ctx}/css/style.css";
        @import "${ctx}/css/menu.css";
    </style>
    <script type="text/javascript" src="${ctx}/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="${ctx}/js/ddaccordion.js"></script>
    <script type="text/javascript" charset="GBK">
        ddaccordion.init({
            headerclass: "menuheaders",
            contentclass: "menucontents",
            revealtype: "clickgo",
            mouseoverdelay: 200,
            collapseprev: true,
            defaultexpanded: [0],
            onemustopen: false,
            animatedefault: false,
            persiststate: true,
            toggleclass: ["unselected", "selected"],
            togglehtml: ["none", "", ""],
            animatespeed: 500,
            oninit:function(expandedindices) {
            },
            onopenclose:function(header, index, state, isuseractivated) {
            }
        })

    </script>
    <%
        String helloName;
        OperatorDetails operatorDetails = SpringSecurityUtils.getCurrentUser();
        if (operatorDetails.getEmployee() != null) {
            helloName = operatorDetails.getEmployee().getName();
        } else {
            helloName = operatorDetails.getUsername();
        }
    %>
    <!-- 从被装饰页面获取head标签内容 -->
    <decorator:head/>
</head>
<body>
<div id="doc3" class="yui-t2">
    <div id="hd">
        <div id="title">
            <h1>BLUE</h1>

            <h2>TaskFlow-任务流 Demo</h2>
        </div>
        <div id="menu">
            <ul>
                <li>你好, <%=helloName%>(<%=SpringSecurityUtils.getCurrentUserIp()%>).</li>
                <li><a href="${ctx}/j_spring_security_logout">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="bd">
        <div id="leftbar" class="yui-b">
            <div class="arrowsidemenu">
                <div><a href="#" title="首页">首页</a></div>
                <security:authorize ifAnyGranted="ROLE_浏览机构管理">
                    <div class="menuheaders"><a href="#" title="机构职员">机构职员</a></div>
                    <ul class="menucontents">
                        <li><a href="${ctx}/organization/organization">机构职员管理</a></li>
                        <li><a href="${ctx}/organization/organization!tree.action">机构职员树</a></li>
                    </ul>
                </security:authorize>
                <security:authorize ifAnyGranted="ROLE_浏览账号管理">
                    <div><a href="${ctx}/account/role" title="账号权限">账号权限</a></div>
                </security:authorize>
                <security:authorize ifAnyGranted="ROLE_浏览任务管理">
                    <div><a href="${ctx}/task/task" title="我的任务">我的任务</a></div>
                    <div class="menuheaders"><a href="#" title="任务报表">任务查询与统计</a></div>
                    <ul class="menucontents">
                        <li><a href="#">任务查询</a></li>
                        <li><a href="${ctx}/report/report.action">任务统计</a></li>
                    </ul>
                </security:authorize>
            </div>
        </div>
        <div id="yui-main">
            <div class="yui-b">
                <!-- 从被装饰页面获取body标签内容 -->
                <decorator:body/>
            </div>
        </div>
    </div>
    <div id="ft">Copyright &copy; 2005-2010 blue</div>
</div>
</body>
</html>