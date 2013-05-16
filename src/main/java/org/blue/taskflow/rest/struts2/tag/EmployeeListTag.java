package org.blue.taskflow.rest.struts2.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-15
 * Time: 15:59:19
 */
public class EmployeeListTag extends ComponentTagSupport {
    protected String excludeEmployeeId;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new EmployeeList(stack);
    }

    protected void populateParams() {
        super.populateParams();

        ((EmployeeList) component).setExcludeEmployeeId(excludeEmployeeId);
    }

    public void setExcludeEmployeeId(String excludeEmployeeId) {
        this.excludeEmployeeId = excludeEmployeeId;
    }
}
