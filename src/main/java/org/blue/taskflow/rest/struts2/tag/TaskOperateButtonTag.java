package org.blue.taskflow.rest.struts2.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-11-6
 * Time: 13:39:02
 */
public class TaskOperateButtonTag extends AbstractUITag {
    private String state;
    private String createEmployeeId;
    private String belongEmployeeId;
    private String currentEmployeeId;

    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new TaskOperateButton(stack, req, res);
    }

    @Override
    protected void populateParams() {
        super.populateParams();

        TaskOperateButton taskOperateButton = (TaskOperateButton) component;
        taskOperateButton.setState(state);
        taskOperateButton.setCreateEmployeeId(createEmployeeId);
        taskOperateButton.setBelongEmployeeId(belongEmployeeId);
        taskOperateButton.setCurrentEmployeeId(currentEmployeeId);
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCreateEmployeeId(String createEmployeeId) {
        this.createEmployeeId = createEmployeeId;
    }

    public void setBelongEmployeeId(String belongEmployeeId) {
        this.belongEmployeeId = belongEmployeeId;
    }

    public void setCurrentEmployeeId(String currentEmployeeId) {
        this.currentEmployeeId = currentEmployeeId;
    }
}
