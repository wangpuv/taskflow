package org.blue.taskflow.web.account;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.blue.taskflow.domain.entity.account.Role;
import org.blue.taskflow.domain.entity.account.User;
import org.blue.taskflow.domain.entity.organization.Employee;
import org.blue.taskflow.service.ServiceException;
import org.blue.taskflow.service.account.AccountService;
import org.blue.taskflow.service.organization.OrganizationService;
import org.blue.taskflow.web.CrudActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.blue.taskflow.domain.dao.HibernateUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-2
 * Time: 17:41:58
 */
@Namespace("/account")
@Results({@Result(name = CrudActionSupport.RELOAD, location = "/organization/employee.action?organizationId=${model.employee.organization.id}", type = "redirect")})
public class UserAction extends CrudActionSupport<User> {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrganizationService organizationService;

    //-- 页面属性 --//
    private Long id;
    private User entity;
    private Long employeeId;
    private List<Long> checkedRoleIds; //页面中钩选的角色id列表

    //-- ModelDriven 与 Preparable函数 --//
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public User getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (id != null) {
            entity = accountService.getUser(id);
        } else {
            entity = new User();
        }
    }

    @Override
    public String list() throws Exception {
        return SUCCESS;
    }

    @Override
    public String input() throws Exception {
        if (entity != null) {
            checkedRoleIds = entity.getRoleIds();
        }
        return INPUT;
    }

    @Override
    public String save() throws Exception {
        //根据页面上的checkbox选择 整合User的Roles Set
        HibernateUtils.mergeByCheckedIds(entity.getRoleList(), checkedRoleIds, Role.class);
        if (this.employeeId != null) {
            Employee employee = organizationService.getEmployee(this.employeeId);
            entity.setEmployee(employee);
        }
        accountService.createAndStoreUser(entity);
        addActionMessage("保存用户成功");
        return RELOAD;
    }

    @Override
    public String delete() throws Exception {
        try {
            accountService.deleteUser(id);
            addActionMessage("删除用户成功");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            addActionMessage("删除用户失败");
        }
        return RELOAD;
    }

    //-- 其他Action函数 --//

    /**
     * 支持使用Jquery.validate Ajax检验用户名是否重复.
     */
    public String checkLoginName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String newLoginName = request.getParameter("loginName");
        String oldLoginName = request.getParameter("oldLoginName");

        if (accountService.isLoginNameUnique(newLoginName, oldLoginName)) {
            Struts2Utils.renderText("true");
        } else {
            Struts2Utils.renderText("false");
        }
        //因为直接输出内容而不经过jsp,因此返回null.
        return null;
    }

    //-- 页面属性访问函数 --//
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
	 * input页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return accountService.getAllRole();
	}

    /**
     * input页面显示用户拥有的角色.
     */
    public List<Long> getCheckedRoleIds() {
        return checkedRoleIds;
    }

    /**
     * input页面提交用户拥有的角色.
     */
    public void setCheckedRoleIds(List<Long> checkedRoleIds) {
        this.checkedRoleIds = checkedRoleIds;
    }
}
