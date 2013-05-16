package org.blue.taskflow.web.organization;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.blue.taskflow.domain.entity.organization.Employee;
import org.blue.taskflow.domain.entity.organization.Organization;
import org.blue.taskflow.service.ServiceException;
import org.blue.taskflow.service.organization.OrganizationService;
import org.blue.taskflow.web.CrudActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-2-21
 * Time: 14:46:58
 */
//定义URL映射对应/organization/organization.action
@Namespace("/organization")
//定义名为reload的result重定向到organization.action, 其他result则按照convention默认.
@Results({@Result(name = CrudActionSupport.RELOAD, location = "organization.action", type = "redirect"),
        @Result(name = "tree", location = "organization-tree.jsp", type = "dispatcher")})
public class OrganizationAction extends CrudActionSupport<Organization> {

    @Autowired
    private OrganizationService organizationService;

    //-- 页面属性 --//
    private Long id;
    private Organization entity;
    private List<Organization> organizationList = new ArrayList<Organization>();
    private Long parentId;

    //-- ModelDriven 与 Preparable函数 --//
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Organization getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (id != null) {
            entity = organizationService.getOrganization(id);
        } else {
            entity = new Organization();
        }
    }

    @Override
    public String list() throws Exception {
        organizationList = organizationService.getAllOrganization();
        return SUCCESS;
    }

    @Override
    public String input() throws Exception {
        organizationList = organizationService.getAllOrganization();
        return INPUT;
    }

    @Override
    public String save() throws Exception {
        if (this.parentId != null) {
            Organization parent = organizationService.getOrganization(this.parentId);
            entity.setParent(parent);
        }
        organizationService.createAndStoreOrganization(entity);
        addActionMessage("保存机构成功");
        return RELOAD;
    }

    @Override
    public String delete() throws Exception {
        try {
            organizationService.deleteOrganization(id);
            addActionMessage("删除机构成功");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            addActionMessage("删除机构失败");
        }
        return RELOAD;
    }

    public String tree() throws Exception {
        return "tree";
    }

    public String treeData() {
        organizationList = organizationService.getAllRootOrganization();
        String organizationTree = "[" + getOrganizationTree(organizationList) + "]";
        Struts2Utils.renderJson(organizationTree, "encoding:GBK");
        return null;
    }

    private String getOrganizationTree(Collection<Organization> organizationColl) {
        String tree = "";
        for (Iterator<Organization> it = organizationColl.iterator(); it.hasNext();) {
            Organization organization = it.next();
            tree += "{";
            tree += "\"text\":\"" + organization.getName() + "(" + organization.getShortname() + ")\",";
            tree += "\"classes\":\"organ\"";
            if (organization.getEmployees().size() > 0 || organization.getOrganizations().size() > 0) {
                tree += ",\"expanded\":true,";
                tree += "\"children\":";
                tree += "[";
                if (organization.getEmployees().size() > 0) {
                    for (Iterator<Employee> i = organization.getEmployees().iterator(); i.hasNext();) {
                        Employee employee = i.next();
                        tree += "{";
                        tree += "\"text\":\"" + employee.getName() + "(" + employee.getSex() + ")\",";
                        tree += "\"classes\":\"employee\"";
                        if (i.hasNext()) {
                            tree += "},";
                        } else {
                            tree += "}";
                        }
                    }
                    if (organization.getOrganizations().size() > 0) {
                        tree += ",";
                    }
                }
                if (organization.getOrganizations().size() > 0) {
                    tree += getOrganizationTree(organization.getOrganizations());
                }
                tree += "]";
            }
            if (it.hasNext()) {
                tree += "},";
            } else {
                tree += "}";
            }
        }
        return tree;
    }

    //-- 页面属性访问函数 --//
    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
