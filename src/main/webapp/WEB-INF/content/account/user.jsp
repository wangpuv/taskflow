<%--
  Created by IntelliJ IDEA.
  User: blue
  Date: 2010-9-2
  Time: 18:00:32
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
    <title>账号管理页面</title>

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
                    loginName: {
                        required: true,
                        maxlength: 20,
                        remote: "user!checkLoginName.action?oldLoginName=" + encodeURIComponent('${loginName}')
                    },
                    password: {
                        required: true,
                        minlength:3
                    },
                    passwordConfirm: {
                        equalTo:"#password"
                    },
                    checkedRoleIds:"required"
                },
                messages: {
                    loginName: {
                        remote: "用户登录名已存在"
                    },
                    passwordConfirm: {
                        equalTo: "输入与上面相同的密码"
                    }
                }
            });
        });
    </script>
</head>
<body>
<div id="container">
    <div class="title">
        <h2><s:if test="id == null">创建</s:if><s:else>修改</s:else>用户账号</h2>
    </div>
    <form id="inputForm" action="user!save.action" method="post" class="inputForm">
        <input type="hidden" name="id" value="${id}"/>
        <input type="hidden" name="employeeId" value="${employeeId}"/>
        <table class="noborder">
            <tr>
                <td>登录名:</td>
                <td colspan="3">
                    <label><input type="text" name="loginName" size="90" id="loginName" value="${loginName}"/></label>
                </td>
            </tr>
            <tr>
                <td>密码:</td>
                <td colspan="3">
                    <label><input type="password" name="password" size="90" id="password" value="${password}"/></label>
                </td>
            </tr>
            <tr>
                <td>确认密码:</td>
                <td colspan="3">
                    <label><input type="password" name="passwordConfirm" size="90" id="passwordConfirm"
                                  value="${password}"/></label>
                </td>
            </tr>
            <tr>
                <td>角色:</td>
                <td colspan="3">
                    <s:checkboxlist name="checkedRoleIds" list="allRoleList" listKey="id" listValue="name" theme="custom"/>
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