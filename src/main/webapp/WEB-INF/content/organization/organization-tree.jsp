<%--
  Created by IntelliJ IDEA.
  User: blue
  Date: 2010-9-8
  Time: 15:46:38
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>机构职员树</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/js/treeview/jquery.treeview.css"/>
    <script type="text/javascript" src="${ctx}/js/treeview/jquery.treeview.js"></script>
    <script type="text/javascript" src="${ctx}/js/treeview/jquery.treeview.async.js"></script>
    <script type="text/javascript" charset="GBK">
        $(document).ready(function() {
            $("#organizationTree").treeview({
                url: "organization!treeData.action"
            })
        });
    </script>
</head>
<body>
<div id="container">
    <div class="title"><h2>机构职员树</h2></div>
    <div class="treeView">
        <ul id="organizationTree" class="organtree"></ul>
    </div>
</div>
</body>
</html>