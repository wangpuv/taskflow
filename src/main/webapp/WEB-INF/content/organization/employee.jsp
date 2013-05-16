<%--
  Created by IntelliJ IDEA.
  User: blue
  Date: 2010-9-1
  Time: 16:55:13
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%-- spring security --%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%-- 自定义标签 --%>
<%@ taglib prefix="tf" uri="/taskflow-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>机构管理页面</title>
    <link href="${ctx}/css/table.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="GBK">
        $(document).ready(function() {
            var oTable = $('#employeeTable').dataTable();
        });
    </script>
</head>
<body>
<form id="mainForm" action="employee!input.action">
    <input type="hidden" name="organizationId" value="${organizationId}"/>
    <div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
    <div id="container" class="ex_highlight_row">
        <div class="title"><h2>职员列表</h2></div>
        <div class="grid">
            <table id="employeeTable" class="display">
                <thead title="职员列表">
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>证件类型</th>
                    <th>证件号码</th>
                    <th>手机号码</th>
                    <th>电子邮箱</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="employeeList" status="stat">
                    <tr>
                        <td><s:property value="#stat.index + 1"/></td>
                        <td>${name}</td>
                        <td>${sex}</td>
                        <td><tf:codeConvertToName codeTableName="certificateTypeCodeTable" code="${certificateTypeCode}" theme="custom"/></td>
                        <td>${certificateNo}</td>
                        <td>${phoneNo}</td>
                        <td>${email}</td>
                        <td><fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd"/></td>
                        <td>&nbsp;
                            <security:authorize ifAnyGranted="ROLE_浏览机构管理">
                                <security:authorize ifNotGranted="ROLE_修改机构管理">
                                    <a href="employee!input.action?id=${id}">查看</a>&nbsp;
                                </security:authorize>
                            </security:authorize>

                            <security:authorize ifAnyGranted="ROLE_修改机构管理">
                                <a href="employee!input.action?id=${id}">修改</a>&nbsp;
                                <a href="employee!delete.action?id=${id}">删除</a>&nbsp;
                            </security:authorize>

                            <security:authorize ifAnyGranted="ROLE_浏览账号管理">
                                <a href="${ctx}/account/user!input.action?employeeId=${id}&id=<s:iterator value="users" begin="0" end="0">${id}</s:iterator>">账号</a>
                            </security:authorize>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
                <tfoot>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>证件类型</th>
                    <th>证件号码</th>
                    <th>手机号码</th>
                    <th>电子邮箱</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </tfoot>
            </table>
        </div>
        <div class="button">
            <security:authorize ifAnyGranted="ROLE_修改机构管理">
                <label><input class="button" type="submit" value="增加职员"/></label>
            </security:authorize>
            <label><input class="button" type="button" value="返回" onclick="gotoOrganization()"/></label>
            <script type="text/javascript" charset="GBK">
                function gotoOrganization() {
                    $('#mainForm').attr("action", "organization");
                    $('#mainForm').submit();
                }
            </script>
        </div>
    </div>
</form>
</body>
</html>