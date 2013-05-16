package org.blue.taskflow.rest.freemarker.template;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import org.blue.taskflow.domain.entity.task.TaskState;
import org.blue.taskflow.rest.codetable.StoreCodeTableTemplate;
import org.springside.modules.utils.spring.SpringContextHolder;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-14
 * Time: 16:28:19
 */
public class CodeConvertToName implements TemplateMethodModel {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments.size() != 2) {
            throw new TemplateModelException("参数个数错误！");
        }
        return getNameByCode((String) arguments.get(0), (String) arguments.get(1));
    }

    private String getNameByCode(String codeTableName, String code) {
        String result;
        if (code == null || code.equals("")) {
            result = "";
        } else if (codeTableName.equals("taskState")) {
            result = TaskState.getTaskStateBySign(code).getName();
        } else {
            StoreCodeTableTemplate codeTable = SpringContextHolder.getBean(codeTableName);
            result = codeTable.getNameByCode(code);
        }
        return result;
    }
}
