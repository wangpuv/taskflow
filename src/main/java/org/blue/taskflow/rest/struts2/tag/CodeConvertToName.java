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
 * Date: 2010-9-14
 * Time: 10:38:33
 */
@StrutsTag(name = "codeConvertToName", tldBodyContent = "empty", tldTagClass = "org.blue.taskflow.rest.struts2.tag.CodeConvertToNameTag", description = "根据代码表转换代码为名称")
public class CodeConvertToName extends UIBean {

    private static final String TEMPLATE = "codeConvertToName";

    private String codeTableName;

    private String code;

    public CodeConvertToName(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    @Override
    protected void evaluateExtraParams() {
        super.evaluateExtraParams();

        if (null != codeTableName) {
            addParameter("codeTableName", findString(codeTableName));
        }

        if (null != code) {
            addParameter("code", findString(code));
        }
    }

    @StrutsTagAttribute(description = "代码表名", type = "String")
    public void setCodeTableName(String codeTableName) {
        this.codeTableName = codeTableName;
    }

    @StrutsTagAttribute(description = "代码", type = "String")
    public void setCode(String code) {
        this.code = code;
    }
}
