package org.blue.taskflow.domain.entity.account;

// Generated 2009-12-17 11:09:01 by Hibernate Tools 3.2.5.Beta

import javax.persistence.*;

import com.google.common.collect.Lists;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.blue.taskflow.domain.entity.IdEntity;
import org.blue.taskflow.domain.entity.organization.Employee;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.utils.reflection.ConvertUtils;

import java.util.List;

/**
 * 用户
 *
 * 使用JPA annotation 定义ORM关系
 * 使用Hibernate annotation 定义JPA 1.0未覆盖的部分
 */
@Entity
@Table(name = "ACCT_USER")
public class User extends IdEntity implements java.io.Serializable {

    private Employee employee; // 所属职员
    private String loginName; // 登录用户名
    private String password; // 用户密码
    private List<Role> roleList = Lists.newArrayList();//有序的关联对象集合

    public User() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", unique = true)
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Column(name = "LOGIN_NAME", nullable = false, length = 20, unique = true)
    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "PASSWORD", length = 20)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany
    //中间表定义,表名采用默认命名规则
    @JoinTable(name = "ACCT_USER_ROLE", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    //Fecth策略定义
    @Fetch(FetchMode.SUBSELECT)
    //集合按id排序.
    @OrderBy("id")
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    /**
     * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
     */
    //非持久化属性.
    @Transient
    public String getRoleNames() {
        return ConvertUtils.convertElementPropertyToString(roleList, "name", ", ");
    }

    /**
     * 用户拥有的角色id字符串, 多个角色id用','分隔.
     */
    //非持久化属性.
    @Transient
    @SuppressWarnings("unchecked")
    public List<Long> getRoleIds() {
        return ConvertUtils.convertElementPropertyToList(roleList, "id");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
