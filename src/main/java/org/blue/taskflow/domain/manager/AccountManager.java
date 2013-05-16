package org.blue.taskflow.domain.manager;

import org.blue.taskflow.domain.dao.account.AuthorityDAO;
import org.blue.taskflow.domain.dao.account.RoleDAO;
import org.blue.taskflow.domain.dao.account.UserDAO;
import org.blue.taskflow.domain.entity.account.Authority;
import org.blue.taskflow.domain.entity.account.Role;
import org.blue.taskflow.domain.entity.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-8-27
 * Time: 11:04:07
 */
@Component
public class AccountManager {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private AuthorityDAO authorityDAO;

    public void createAndStoreUser(User user) {
        userDAO.save(user);
    }

    public User getUser(Long id) {
        return userDAO.get(id);
    }

    public User getUserByLoginName(String loginName) {
        User user = userDAO.findUniqueBy("loginName", loginName);
        userDAO.initAllProperties(user);
        return user;
    }

    public void deleteUser(Long id) {
        userDAO.delete(id);
    }

    /**
     * 检查用户名是否唯一.
     *
     * @param newLoginName 新登录名
     * @param oldLoginName 老登录名
     * @return loginName在数据库中唯一或等于oldLoginName时返回true.
     */
    public boolean isLoginNameUnique(String newLoginName, String oldLoginName) {
        return userDAO.isPropertyUnique("loginName", newLoginName, oldLoginName);
    }

    public List<Role> getAllRole() {
        return roleDAO.getAll("id", true);
    }

    public Role getRole(Long id) {
        return roleDAO.get(id);
    }

    public void saveRole(Role role) {
        roleDAO.save(role);
    }

    public void deleteRole(Long id) {
        roleDAO.delete(id);
    }

    public List<Authority> getAllAuthority() {
		return authorityDAO.getAll();
	}
}
