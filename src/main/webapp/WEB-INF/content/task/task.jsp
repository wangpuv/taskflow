<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: blue
  Date: 2010-9-10
  Time: 16:14:27
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tf" uri="/taskflow-tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>我的任务</title>
    <link href="${ctx}/css/table.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="GBK">
        $(document).ready(function() {
            $('#myTaskTable tbody tr').addClass('gradeA');
            var oTable = $('#myTaskTable').dataTable();
        });
    </script>
</head>
<body>
<form id="mainForm" action="task!input.action">
    <div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
    <div id="container" class="ex_highlight_row">
        <div class="title"><h2>我的任务</h2></div>
        <div class="grid">
            <table id="myTaskTable" class="display">
                <thead title="任务列表">
                <tr>
                    <th>序号</th>
                    <th>编号</th>
                    <th>名称</th>
                    <th>创建人</th>
                    <th>所属人</th>
                    <th>结束时间</th>
                    <th>类型</th>
                    <th>状态</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="taskList" status="stat">
                    <c:set var="dateNow" value="<%=new Date()%>"/>
                    <tr<c:if test="${endTime < dateNow}"> class="gradeX"</c:if>>
                        <td><s:property value="#stat.index + 1"/></td>
                        <td>${id}</td>
                        <td>${name}</td>
                        <td>${createEmployee.name}</td>
                        <td>${belongEmployee.name}</td>
                        <td><fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>${typeCode}</td>
                        <td><tf:codeConvertToName codeTableName="taskState" code="${state}" theme="custom"/></td>
                        <td><fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>&nbsp;
                            <a href="task!input.action?id=${id}">操作</a>
                            <s:if test="createEmployee.id == [1].currentEmployeeId">&nbsp;<a href="task!delete.action?id=${id}">删除</a></s:if>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
                <tfoot>
                <tr>
                    <th>序号</th>
                    <th>编号</th>
                    <th>名称</th>
                    <th>创建人</th>
                    <th>所属人</th>
                    <th>结束时间</th>
                    <th>类型</th>
                    <th>状态</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </tfoot>
            </table>
        </div>
        <div class="button">
            <label><input class="button" type="submit" value="创建任务"/></label>
        </div>
    </div>
</form>
</body>
</html>