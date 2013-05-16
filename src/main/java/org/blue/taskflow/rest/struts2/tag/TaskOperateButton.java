package org.blue.taskflow.rest.struts2.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.UIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-11-6
 * Time: 13:34:02
 */
@StrutsTag(name = "taskOperateButton", tldBodyContent = "empty", tldTagClass = "org.blue.taskflow.rest.struts2.tag.TaskOperateButtonTag", description = "任务操作按钮")
public class TaskOperateButton extends UIBean {
    private static final String TEMPLATE = "taskOperateButton";
    
    private String state;
    private String createEmployeeId;
    private String belongEmployeeId;
    private String currentEmployeeId;

    public TaskOperateButton(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    @Override
    protected void evaluateExtraParams() {
        super.evaluateExtraParams();

        if (null != state) {
            addParameter("state", findString(state));
        }

        if (null != createEmployeeId) {
            addParameter("createEmployeeId", findString(createEmployeeId));
        }

        if (null != belongEmployeeId) {
            addParameter("belongEmployeeId", findString(belongEmployeeId));
        }

        if (null != currentEmployeeId) {
            addParameter("currentEmployeeId", findString(currentEmployeeId));
        }
    }

    @StrutsTagAttribute(description = "任务状态", type = "String")
    public void setState(String state) {
        this.state = state;
    }

    @StrutsTagAttribute(description = "任务创建人ID", type = "String")
    public void setCreateEmployeeId(String createEmployeeId) {
        this.createEmployeeId = createEmployeeId;
    }

    @StrutsTagAttribute(description = "任务所属人ID", type = "String")
    public void setBelongEmployeeId(String belongEmployeeId) {
        this.belongEmployeeId = belongEmployeeId;
    }

    @StrutsTagAttribute(description = "当前操作人ID", type = "String")
    public void setCurrentEmployeeId(String currentEmployeeId) {
        this.currentEmployeeId = currentEmployeeId;
    }
}
