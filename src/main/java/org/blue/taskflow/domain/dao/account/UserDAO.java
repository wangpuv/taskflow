package org.blue.taskflow.domain.dao.account;

import java.util.List;

import org.blue.taskflow.domain.entity.account.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

@Component
public class UserDAO extends HibernateDao<User, Long> {
    /**
     * LazyLoad关联对象的初始化函数.
     */
    public void initAllProperties(User user) {
        if (user != null) {
            Hibernate.initialize(user.getEmployee());
            if (user.getEmployee() != null) {
                Hibernate.initialize(user.getEmployee().getUsers());
                Hibernate.initialize(user.getEmployee().getOrganization());
            }
        }
    }

    public void initAllProperties(List<User> userList) {
        for (User user : userList) {
            initAllProperties(user);
        }
    }
}
