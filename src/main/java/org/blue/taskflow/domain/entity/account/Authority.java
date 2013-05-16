package org.blue.taskflow.domain.entity.account;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.blue.taskflow.domain.entity.IdEntity;

import javax.persistence.*;

/**
 * 权限
 *
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-8-27
 * Time: 16:25:43
 */
@Entity
@Table(name = "ACCT_AUTHORITY")
public class Authority extends IdEntity implements java.io.Serializable {
    /**
     * SpringSecurity中默认的角色/授权名前缀.
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    private String name;

    public Authority() {
    }

    public Authority(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Column(name = "NAME", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public String getPrefixedName() {
        return AUTHORITY_PREFIX + name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
