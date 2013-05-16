<#assign create = "create"/>
<#assign assign = "assign"/>
<#assign accept = "accept"/>
<#assign refuse = "refuse"/>
<#assign complete = "complete"/>
<#assign postpone = "postpone"/>
<#assign closed = "closed"/>

<#if parameters.state == "">
<label><input class="button" type="submit" value="保存"/>&nbsp;</label>
    <#elseif parameters.state == create><#--创建状态-->
    <label><input class="button" type="submit" value="保存"/>&nbsp;</label>
    <#elseif parameters.state == assign && parameters.currentEmployeeId == parameters.belongEmployeeId><#--分配状态-->
    <label><input class="button" type="submit" value="分配"/>&nbsp;</label>
    <label><input class="button" type="button" value="接受" onclick="turnToAccept()"/>&nbsp;</label>
    <label><input class="button" type="button" value="拒绝" onclick="turnToRefuse()"/>&nbsp;</label>
    <#elseif parameters.state == accept && parameters.currentEmployeeId == parameters.belongEmployeeId><#--接受状态-->
    <label><input class="button" type="button" value="完成" onclick="turnToComplete()"/>&nbsp;</label>
    <label><input class="button" type="button" value="拒绝" onclick="turnToRefuse()"/>&nbsp;</label>
    <#elseif parameters.state == refuse  && parameters.currentEmployeeId == parameters.belongEmployeeId><#--拒绝状态-->
    <label><input class="button" type="submit" value="分配"/>&nbsp;</label>
    <label><input class="button" type="button" value="接受" onclick="turnToAccept()"/>&nbsp;</label>
    <#elseif parameters.state == complete><#--完成状态-->
        <#if parameters.currentEmployeeId == parameters.belongEmployeeId>
        <label><input class="button" type="button" value="重新打开" onclick="turnToAccept()"/>&nbsp;</label>
            <#elseif parameters.currentEmployeeId == parameters.createEmployeeId>
            <label><input class="button" type="submit" value="重新分配"/>&nbsp;</label>
            <label><input class="button" type="button" value="关闭" onclick="turnToClosed()"/>&nbsp;</label>
        </#if>
    <#elseif parameters.state == postpone><#--延期状态-->
        <#if parameters.currentEmployeeId == parameters.belongEmployeeId>
            <#elseif parameters.currentEmployeeId == parameters.createEmployeeId>
            <label><input class="button" type="submit" value="重新分配"/>&nbsp;</label>
        </#if>
    <#elseif parameters.state == closed><#--关闭状态-->
        <#if parameters.currentEmployeeId == parameters.belongEmployeeId>
            <#elseif parameters.currentEmployeeId == parameters.createEmployeeId>
            <label><input class="button" type="submit" value="重新分配"/>&nbsp;</label>
        </#if>
    <#else>
    <label><input class="button" type="submit" value="保存"/>&nbsp;</label>
    <label><input class="button" type="button" value="延期" onclick="turnToPostpone()"/>&nbsp;</label>
</#if>

<script type="text/javascript" charset="GBK">
    function turnToAccept() {
        $('#inputForm').attr("action", "task!accept.action");
        $('#inputForm').submit();
    }
    function turnToRefuse() {
        $('#inputForm').attr("action", "task!refuse.action");
        $('#inputForm').submit();
    }
    function turnToComplete() {
        $('#inputForm').attr("action", "task!complete.action");
        $('#inputForm').submit();
    }
    function turnToPostpone() {
        $('#inputForm').attr("action", "task!postpone.action");
        $('#inputForm').submit();
    }
    function turnToClosed() {
        $('#inputForm').attr("action", "task!closed.action");
        $('#inputForm').submit();
    }
</script>