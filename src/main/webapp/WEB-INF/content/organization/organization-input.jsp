<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%-- spring security --%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%-- 自定义标签 --%>
<%@ taglib prefix="tf" uri="/taskflow-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>机构管理页面</title>
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
                    },
                    shortName: {
                        maxlength: 50
                    }
                }
            });
        });
    </script>
</head>
<body>
<div id="container">
    <div class="title">
        <h2><s:if test="id == null">创建</s:if><s:else>修改</s:else>机构</h2>
    </div>
    <form id="inputForm" action="organization!save.action" method="post" class="inputForm">
        <input type="hidden" name="id" value="${id}"/>
        <table class="noborder">
            <tr>
                <td>名称:</td>
                <td colspan="3">
                    <label><input type="text" name="name" size="90" id="name" value="${name}"/></label>
                </td>
            </tr>
            <tr>
                <td>简称:</td>
                <td>
                    <label><input type="text" id="shortname" name="shortname" size="40" value="${shortname}"/></label>
                </td>
                <td>编号:</td>
                <td>
                    <label><input type="text" id="no" name="no" size="40" value="${no}"/></label>
                </td>
            </tr>
            <tr>
                <td>类型:</td>
                <td>
                    <label>
                        <select id="typeCode" name="typeCode">
                            <option></option>
                            <tf:codeTableList codeTableName="organizationTypeCodeTable">
                                <s:iterator value="codeTableList">
                                    <option value="${code}"
                                            <s:if test="code == [1].typeCode">selected="selected"</s:if>>${name}</option>
                                </s:iterator>
                            </tf:codeTableList>
                        </select>
                    </label>
                </td>
                <td>级别:</td>
                <td>
                    <label>
                        <select id="gradeCode" name="gradeCode">
                            <option></option>
                            <tf:codeTableList codeTableName="organizationGradeCodeTable">
                                <s:iterator value="codeTableList">
                                    <option value="${code}"
                                            <s:if test="code == [1].gradeCode">selected="selected"</s:if>>${name}</option>
                                </s:iterator>
                            </tf:codeTableList>
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td colspan="3">
                    <label><textarea rows="3" cols="90" id="remark" name="remark">${remark}</textarea></label>
                </td>
            </tr>
            <tr>
                <td>父机构:</td>
                <td colspan="3">
                    <label>
                        <select id="parentId" name="parentId">
                            <option value=""></option>
                            <s:iterator value="organizationList">
                                <option value="${id}"
                                        <s:if test="id == [1].parent.id">selected="selected"</s:if>>${name}</option>
                            </s:iterator>
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <td>创建时间:</td>
                <td colspan="3">${createTime}</td>
            </tr>
            <tr>
                <td colspan="4">
                    <div class="button">
                        <security:authorize ifAnyGranted="ROLE_修改机构管理">
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