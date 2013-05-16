package org.blue.taskflow.web.account;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.blue.taskflow.domain.entity.account.Authority;
import org.blue.taskflow.domain.entity.account.Role;
import org.blue.taskflow.service.account.AccountService;
import org.blue.taskflow.web.CrudActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.blue.taskflow.domain.dao.HibernateUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-8
 * Time: 9:03:11
 */
@Namespace("/account")
@Results({@Result(name = CrudActionSupport.RELOAD, location = "role.action", type = "redirect")})
public class RoleAction extends CrudActionSupport<Role> {

    @Autowired
    private AccountService accountService;

    //-- 页面属性 --//
	private Long id;
	private Role entity;
	private List<Role> allRoleList;//角色列表
	private List<Long> checkedAuthIds;//页面中钩选的权限id列表

    //-- ModelDriven 与 Preparable函数 --//
    public void setId(Long id) {
		this.id = id;
	}

    @Override
	public Role getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = accountService.getRole(id);
		} else {
			entity = new Role();
		}
	}

	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		allRoleList = accountService.getAllRole();
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		checkedAuthIds = entity.getAuthIds();
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//根据页面上的checkbox 整合Role的Authorities Set.
		HibernateUtils.mergeByCheckedIds(entity.getAuthorityList(), checkedAuthIds, Authority.class);
		//保存用户并放入成功信息.
		accountService.saveRole(entity);
		addActionMessage("保存角色成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		accountService.deleteRole(id);
		addActionMessage("删除角色成功");
		return RELOAD;
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return allRoleList;
	}

	/**
	 * input页面显示所有授权列表.
	 */
	public List<Authority> getAllAuthorityList() {
		return accountService.getAllAuthority();
	}

	/**
	 * input页面显示角色拥有的授权.
	 */
	public List<Long> getCheckedAuthIds() {
		return checkedAuthIds;
	}

	/**
	 * input页面提交角色拥有的授权.
	 */
	public void setCheckedAuthIds(List<Long> checkedAuthIds) {
		this.checkedAuthIds = checkedAuthIds;
	}
}
