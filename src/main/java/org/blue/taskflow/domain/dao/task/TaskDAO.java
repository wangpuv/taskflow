package org.blue.taskflow.domain.dao.task;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import org.blue.taskflow.domain.entity.task.Task;

@Component
public class TaskDAO extends HibernateDao<Task, Long> {
}
