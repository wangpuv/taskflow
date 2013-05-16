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
            $('#organizationTable tbody tr').addClass('gradeA');
            var oTable = $('#organizationTable').dataTable();
        });
    </script>
</head>
<body>
<form id="mainForm" action="organization!input.action">
    <div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
    <div id="container" class="ex_highlight_row">
        <div class="title"><h2>机构列表</h2></div>
        <div class="grid">
            <table id="organizationTable" class="display">
                <thead title="机构列表">
                <tr>
                    <th>序号</th>
                    <th>编号</th>
                    <th>名称</th>
                    <th>简称</th>
                    <th>类型</th>
                    <th>级别</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="organizationList" status="stat">
                    <tr>
                        <td><s:property value="#stat.index + 1"/></td>
                        <td>${no}</td>
                        <td>${name}</td>
                        <td>${shortname}</td>
                        <td><tf:codeConvertToName codeTableName="organizationTypeCodeTable" code="${typeCode}" theme="custom"/></td>
                        <td><tf:codeConvertToName codeTableName="organizationGradeCodeTable" code="${gradeCode}" theme="custom"/></td>
                        <td><fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd"/></td>
                        <td>&nbsp;
                            <security:authorize ifAnyGranted="ROLE_浏览机构管理">
                                <security:authorize ifNotGranted="ROLE_修改机构管理">
                                    <a href="organization!input.action?id=${id}">查看</a>&nbsp;
                                    <a href="employee.action?organizationId=${id}">职员</a>
                                </security:authorize>
                            </security:authorize>

                            <security:authorize ifAnyGranted="ROLE_修改机构管理">
                                <a href="organization!input.action?id=${id}">修改</a>&nbsp;
                                <a href="organization!delete.action?id=${id}">删除</a>&nbsp;
                                <a href="employee.action?organizationId=${id}">职员</a>
                            </security:authorize>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
                <tfoot>
                <tr>
                    <th>序号</th>
                    <th>编号</th>
                    <th>名称</th>
                    <th>简称</th>
                    <th>类型</th>
                    <th>级别</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </tfoot>
            </table>
        </div>
        <div class="button">
            <security:authorize ifAnyGranted="ROLE_修改机构管理">
                <label><input class="button" type="submit" value="增加机构"/></label>
            </security:authorize>
        </div>
    </div>
</form>
</body>
</html>