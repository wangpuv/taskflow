<%--
  Created by IntelliJ IDEA.
  User: blue
  Date: 2010-9-8
  Time: 9:43:02
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%-- spring security --%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>权限管理页面</title>

    <link href="${ctx}/css/table.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="GBK">
        $(document).ready(function() {
            $('#roleTable tbody tr').addClass('gradeX');
            var oTable = $('#roleTable').dataTable();
        });
    </script>
</head>
<body>
<form id="mainForm" action="role!input.action">
    <div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
    <div id="container" class="ex_highlight_row">
        <div class="title"><h2>角色列表</h2></div>
        <div class="grid">
            <table id="roleTable" class="display">
                <thead title="角色列表">
                <tr>
                    <th>序号</th>
                    <th>名称</th>
                    <th>授权</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="allRoleList" status="stat">
                    <tr>
                        <td><s:property value="#stat.index + 1"/></td>
                        <td>${name}</td>
                        <td>${authNames}</td>
                        <td>&nbsp;
                            <security:authorize ifAnyGranted="ROLE_浏览账号管理">
                                <security:authorize ifNotGranted="ROLE_修改账号管理">
                                    <a href="role!input.action?id=${id}">查看</a>&nbsp;
                                </security:authorize>
                            </security:authorize>

                            <security:authorize ifAnyGranted="ROLE_修改账号管理">
                                <a href="role!input.action?id=${id}" id="editLink-${name}">修改</a>&nbsp;
                                <a href="role!delete.action?id=${id}" id="deleteLink-${name}">删除</a>
                            </security:authorize>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
                <tfoot>
                <tr>
                    <th>序号</th>
                    <th>名称</th>
                    <th>授权</th>
                    <th>操作</th>
                </tr>
                </tfoot>
            </table>
        </div>
        <div class="button">
            <security:authorize ifAnyGranted="ROLE_修改账号管理">
                <label><input class="button" type="submit" value="增加角色"/></label>
            </security:authorize>
        </div>
    </div>
</form>
</body>
</html>