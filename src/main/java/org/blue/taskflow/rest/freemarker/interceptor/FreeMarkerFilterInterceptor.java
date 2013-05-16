package org.blue.taskflow.rest.freemarker.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;
import org.blue.taskflow.rest.freemarker.template.CodeConvertToName;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-14
 * Time: 14:04:57
 */
public class FreeMarkerFilterInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        CodeConvertToName codeConvertToName = new CodeConvertToName();

        ValueStack stack = ServletActionContext.getContext().getValueStack();
        stack.set("codeConvertToName", codeConvertToName);

        return invocation.invoke();
    }
}
