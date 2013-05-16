package org.blue.taskflow.domain.dao.task;

import org.blue.taskflow.domain.entity.task.TaskStateLog;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-11-5
 * Time: 11:12:11
 */
@Component
public class TaskStateLogDAO extends HibernateDao<TaskStateLog, Long> {
}
