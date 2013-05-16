package org.blue.taskflow.rest.struts2.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;
import org.blue.taskflow.domain.entity.organization.Employee;
import org.blue.taskflow.rest.struts2.bean.ValueObject;
import org.blue.taskflow.service.organization.OrganizationService;
import org.springside.modules.utils.spring.SpringContextHolder;

import java.io.Writer;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-15
 * Time: 15:59:34
 */
@StrutsTag(name = "employeeList", tldTagClass = "org.blue.taskflow.rest.struts2.tag.EmployeeListTag", description = "获取职员列表并设置到ValueStack中.")
public class EmployeeList extends Component {
    protected String excludeEmployeeId;
    protected boolean pushed;

    public EmployeeList(ValueStack stack) {
        super(stack);
    }

    public boolean start(Writer writer) {
        boolean result = super.start(writer);

        ValueStack stack = getStack();

        if (stack != null) {
            ValueObject vo = new ValueObject();
            OrganizationService organizationService = SpringContextHolder.getBean("organizationService");
            List<Employee> employeeList = organizationService.getAllEmployees();
            if (excludeEmployeeId != null && !excludeEmployeeId.equals("")) {
                for (Employee employee : employeeList) {
                    if (employee.getId().equals(Long.valueOf(excludeEmployeeId))) {
                        employeeList.remove(employee);
                        break;
                    }
                }
            }
            vo.setEmployeeList(employeeList);
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

    @StrutsTagAttribute(description = "不包含的职员ID", required = false)
    public void setExcludeEmployeeId(String excludeEmployeeId) {
        this.excludeEmployeeId = excludeEmployeeId;
    }
}
