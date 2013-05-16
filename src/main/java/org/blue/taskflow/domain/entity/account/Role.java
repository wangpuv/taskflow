package org.blue.taskflow.domain.entity.account;

import com.google.common.collect.Lists;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.blue.taskflow.domain.entity.IdEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.utils.reflection.ConvertUtils;

import javax.persistence.*;
import java.util.List;

/**
 * ½ÇÉ«
 *
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-8-27
 * Time: 16:12:53
 */
@Entity
@Table(name = "ACCT_ROLE")
public class Role extends IdEntity implements java.io.Serializable {
    private String name;
    private List<Authority> authorityList = Lists.newArrayList();

    public Role() {

    }

    public Role(Long id, String name) {
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

    @ManyToMany
    @JoinTable(name = "ACCT_ROLE_AUTHORITY", joinColumns = {@JoinColumn(name = "ROLE_ID")}, inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID")})
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id")
    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }

    @Transient
    public String getAuthNames() {
        return ConvertUtils.convertElementPropertyToString(authorityList, "name", ", ");
    }

    @Transient
    @SuppressWarnings("unchecked")
    public List<Long> getAuthIds() {
        return ConvertUtils.convertElementPropertyToList(authorityList, "id");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
