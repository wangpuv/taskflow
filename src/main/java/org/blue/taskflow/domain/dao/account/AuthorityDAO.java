package org.blue.taskflow.domain.dao.account;

import org.blue.taskflow.domain.entity.account.Authority;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 授权对象的泛型DAO.
 *
 */
@Component
public class AuthorityDAO extends HibernateDao<Authority, Long> {
}