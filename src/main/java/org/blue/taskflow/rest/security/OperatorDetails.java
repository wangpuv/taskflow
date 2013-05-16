package org.blue.taskflow.rest.security;

import org.blue.taskflow.domain.entity.account.Role;
import org.blue.taskflow.domain.entity.organization.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 扩展SpringSecurity的WebAuthenticationDetails类, 增加登录时间属性、职员属性和角色属性.
 * <p/>
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-10
 * Time: 9:58:54
 */
public class OperatorDetails extends User {

    private Date loginTime;

    private Employee employee;

    private List<Role> roleList;

    public OperatorDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
