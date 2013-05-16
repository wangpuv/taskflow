package org.blue.taskflow.rest.struts2.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-14
 * Time: 10:11:42
 */
public class CodeConvertToNameTag extends AbstractUITag {
    private String codeTableName;

    private String code;

    @Override
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new CodeConvertToName(stack, req, res);
    }

    @Override
    protected void populateParams() {
        super.populateParams();

        CodeConvertToName codeConvertToName = (CodeConvertToName) component;
        codeConvertToName.setCodeTableName(codeTableName);
        codeConvertToName.setCode(code);
    }

    public void setCodeTableName(String codeTableName) {
        this.codeTableName = codeTableName;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
