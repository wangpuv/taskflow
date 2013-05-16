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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-1
 * Time: 16:32:57
 */
@Namespace("/organization")
@Results({@Result(name = CrudActionSupport.RELOAD, location = "employee.action?organizationId=${organizationId}", type = "redirect")})
public class EmployeeAction extends CrudActionSupport<Employee> {

    @Autowired
    private OrganizationService organizationService;

    //-- 页面属性 --//
    private Long id;
    private Employee entity;
    private List<Employee> employeeList = new ArrayList<Employee>();
    private List<Organization> organizationList = new ArrayList<Organization>();
    private Long organizationId;

    //-- ModelDriven 与 Preparable函数 --//
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Employee getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (id != null) {
            entity = organizationService.getEmployee(id);
        } else {
            entity = new Employee();
        }
    }

    @Override
    public String list() throws Exception {
        employeeList = organizationService.getEmployeesByOrganizationId(this.organizationId);
        return SUCCESS;
    }

    @Override
    public String input() throws Exception {
        organizationList = organizationService.getAllOrganization();
        return INPUT;
    }

    @Override
    public String save() throws Exception {
        if (this.organizationId != null) {
            Organization parent = organizationService.getOrganization(this.organizationId);
            entity.setOrganization(parent);
        }
        organizationService.createAndStoreEmployee(entity);
        addActionMessage("保存机构成功");
        return RELOAD;
    }

    @Override
    public String delete() throws Exception {
        try {
			organizationService.deleteEmployee(id);
			addActionMessage("删除职员成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除职员失败");
		}
        return RELOAD;
    }

    //-- 页面属性访问函数 --//
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }
}
