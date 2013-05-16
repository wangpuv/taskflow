package org.blue.taskflow.rest.struts2.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;
import org.blue.taskflow.domain.entity.organization.Organization;
import org.blue.taskflow.rest.struts2.bean.ValueObject;
import org.blue.taskflow.service.organization.OrganizationService;
import org.springside.modules.utils.spring.SpringContextHolder;

import java.io.Writer;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-11-3
 * Time: 10:36:08
 */
@StrutsTag(name = "organizationList", tldTagClass = "org.blue.taskflow.rest.struts2.tag.OrganizationListTag", description = "获取机构列表并设置到ValueStack中.")
public class OrganizationList extends Component {
    protected String excludeOrganizationId;
    protected boolean pushed;

    public OrganizationList(ValueStack stack) {
        super(stack);
    }

    public boolean start(Writer writer) {
        boolean result = super.start(writer);

        ValueStack stack = getStack();

        if (stack != null) {
            ValueObject vo = new ValueObject();
            OrganizationService organizationService = SpringContextHolder.getBean("organizationService");
            List<Organization> organizationList = organizationService.getAllOrganization();
            if (excludeOrganizationId != null && !excludeOrganizationId.equals("")) {
                for (Organization organization : organizationList) {
                    if (organization.getId().equals(Long.valueOf(excludeOrganizationId))) {
                        organizationList.remove(organization);
                        break;
                    }
                }
            }
            vo.setOrganizationList(organizationList);
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

    @StrutsTagAttribute(description = "不包含的机构ID", required = false)
    public void setExcludeOrganizationId(String excludeOrganizationId) {
        this.excludeOrganizationId = excludeOrganizationId;
    }
}
