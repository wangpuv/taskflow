<%--
  Created by IntelliJ IDEA.
  User: blue
  Date: 2010-9-8
  Time: 9:53:18
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
    <link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx}/js/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/messages_cn.js"></script>
    <script type="text/javascript" charset="GBK">
        $(document).ready(function() {
            //聚焦第一个输入框
            $("#name").focus();
            //为inputForm注册validate函数
            $("#inputForm").validate({
                rules: {
                    name: {
                        required: true,
                        maxlength: 100
                    }
                }
            });
        });
    </script>
</head>
<body>
<div id="container">
    <div class="title">
        <h2><s:if test="id == null">创建</s:if><s:else>修改</s:else>角色</h2>
    </div>
    <form id="inputForm" action="role!save.action" method="post" class="inputForm">
        <input type="hidden" name="id" value="${id}"/>
        <table class="noborder">
            <tr>
                <td>角色名:</td>
                <td colspan="3">
                    <label><input type="text" name="name" size="90" id="name" value="${name}"/></label>
                </td>
            </tr>
            <tr>
                <td>授权:</td>
                <td colspan="3">
                    <s:checkboxlist name="checkedAuthIds" list="allAuthorityList" listKey="id" listValue="name" theme="custom"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div class="button">
                        <security:authorize ifAnyGranted="ROLE_修改账号管理">
                            <label><input class="button" type="submit" value="提交"/>&nbsp;</label>
                        </security:authorize>
                        <label><input class="button" type="button" value="返回" onclick="history.back()"/></label>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>