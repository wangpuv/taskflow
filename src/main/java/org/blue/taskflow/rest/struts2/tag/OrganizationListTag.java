package org.blue.taskflow.rest.struts2.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-11-3
 * Time: 10:27:45
 */
public class OrganizationListTag extends ComponentTagSupport {
    protected String excludeOrganizationId;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new OrganizationList(stack);
    }

    protected void populateParams() {
        super.populateParams();

        ((OrganizationList) component).setExcludeOrganizationId(excludeOrganizationId);
    }

    public void setExcludeOrganizationId(String excludeOrganizationId) {
        this.excludeOrganizationId = excludeOrganizationId;
    }
}
