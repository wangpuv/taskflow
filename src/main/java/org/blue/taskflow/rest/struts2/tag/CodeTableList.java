package org.blue.taskflow.rest.struts2.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;
import org.blue.taskflow.rest.codetable.StoreCodeTableTemplate;
import org.blue.taskflow.rest.struts2.bean.ValueObject;
import org.springside.modules.utils.spring.SpringContextHolder;

import java.io.Writer;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-14
 * Time: 16:51:47
 */
@StrutsTag(name = "codeTableList", tldTagClass = "org.blue.taskflow.rest.struts2.tag.CodeTableListTag", description = "获取代码表并设置到ValueStack中.")
public class CodeTableList extends Component {
    protected String codeTableName;
    protected boolean pushed;

    public CodeTableList(ValueStack stack) {
        super(stack);
    }

    public boolean start(Writer writer) {
        boolean result = super.start(writer);

        ValueStack stack = getStack();

        if (stack != null) {
            ValueObject vo = new ValueObject();
            StoreCodeTableTemplate codeTable = SpringContextHolder.getBean(codeTableName);
            vo.setCodeTableList(codeTable.getAllChoosedCode());
            stack.push(vo);
            pushed = true;
        } else {
            pushed = false; // need to ensure push is assigned, otherwise we may have a leftover value
        }

        return result;
    }

    public boolean end(Writer writer, String body) {
        ValueStack stack = getStack();

        if (pushed && (stack != null)) {
            stack.pop();
        }

        return super.end(writer, body);
    }

    @StrutsTagAttribute(description = "代码表名", required = true)
    public void setCodeTableName(String codeTableName) {
        this.codeTableName = codeTableName;
    }
}
