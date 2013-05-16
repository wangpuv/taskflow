package org.blue.taskflow.rest.struts2.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-14
 * Time: 16:48:03
 */
public class CodeTableListTag extends ComponentTagSupport {

    protected String codeTableName;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new CodeTableList(stack);
    }

    protected void populateParams() {
        super.populateParams();

        ((CodeTableList) component).setCodeTableName(codeTableName);
    }

    public void setCodeTableName(String codeTableName) {
        this.codeTableName = codeTableName;
    }
}
