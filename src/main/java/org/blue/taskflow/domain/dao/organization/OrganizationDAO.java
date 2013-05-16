package org.blue.taskflow.domain.dao.organization;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import org.blue.taskflow.domain.entity.organization.Organization;

@Component
public class OrganizationDAO extends HibernateDao<Organization, Long> {
	/**
	 * LazyLoad关联对象的初始化函数.
	 */
	public void initAllProperties(List<Organization> organizationList) {
		for (Organization organization : organizationList) {
			initAllProperties(organization);
		}
	}
	
	public void initAllProperties(Organization organization) {
		if (organization != null) {
			Hibernate.initialize(organization.getEmployees());
			Hibernate.initialize(organization.getOrganizations());
			Hibernate.initialize(organization.getParent());
		}
	}
}
