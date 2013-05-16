package org.blue.taskflow.domain.dao.log;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import org.blue.taskflow.domain.entity.log.OperateLog;

@Component
public class OperateLogDAO extends HibernateDao<OperateLog, Long> {

}
