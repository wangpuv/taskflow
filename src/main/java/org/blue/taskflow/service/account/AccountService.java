package org.blue.taskflow.service.account;

import org.blue.taskflow.domain.entity.account.Authority;
import org.blue.taskflow.domain.entity.account.Role;
import org.blue.taskflow.domain.entity.account.User;

import java.util.List;

/**
 * 账号管理服务接口
 * <p/>
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-2
 * Time: 17:47:02
 */
public interface AccountService {

    /**
     * 创建或保存用户
     *
     * @param user 用户
     */
    void createAndStoreUser(User user);

    /**
     * 获取用户通过用户ID
     *
     * @param id 用户ID
     * @return 用户
     */
    User getUser(Long id);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void deleteUser(Long id);

    /**
     * 检查用户名是否唯一
     *
     * @param newLoginName 用户名
     * @param oldLoginName 用户名
     * @return loginName在数据库中唯一或等于oldLoginName时返回true.
     */
    boolean isLoginNameUnique(String newLoginName, String oldLoginName);

    /**
     * 获取所有角色列表
     *
     * @return 所有角色列表
     */
    List<Role> getAllRole();

    /**
     * 获取角色通过角色ID
     *
     * @param id 角色ID
     * @return 角色
     */
    Role getRole(Long id);

    /**
     * 保存角色
     *
     * @param role 角色
     */
    void saveRole(Role role);

    /**
     * 删除角色
     *
     * @param id 角色ID
     */
    void deleteRole(Long id);

    /**
     * 获取所有权限列表
     *
     * @return 所有权限列表
     */
    List<Authority> getAllAuthority();
}
