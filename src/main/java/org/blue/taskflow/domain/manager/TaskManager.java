package org.blue.taskflow.domain.manager;

import java.util.Date;
import java.util.List;

import org.blue.taskflow.domain.dao.task.TaskStateLogDAO;
import org.blue.taskflow.domain.entity.task.Task;
import org.blue.taskflow.domain.entity.task.TaskState;
import org.blue.taskflow.domain.entity.task.TaskStateLog;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.blue.taskflow.domain.dao.task.TaskDAO;

@Component
public class TaskManager {
    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private TaskStateLogDAO taskStateLogDAO;

    public void createAndStoreTask(Task task) {
        if (task.getId() == null) {
            task.setCreateTime(new Date());
        }
        taskDAO.save(task);
    }

    public Task getTask(Long id) {
        return taskDAO.get(id);
    }

    @SuppressWarnings("unchecked")
    public List<Task> queryBothTasksByEmployeeId(Long employeeId) {
        Criterion criterion = Restrictions.and(Restrictions.or(
                Restrictions.eq("createEmployee.id", employeeId),
                Restrictions.eq("belongEmployee.id", employeeId)),
                Restrictions.ne("state", TaskState.TASK_STATE_CLOSED.getSign()));
        return taskDAO.createCriteria(criterion).addOrder(Order.asc("endTime")).list();
    }

    public void deleteTask(Long id) {
        taskDAO.delete(id);
    }

    public void createAndStoreTaskStateLog(TaskStateLog taskStateLog) {
        taskStateLogDAO.save(taskStateLog);
    }

    @SuppressWarnings("unchecked")
    public List<TaskStateLog> getTaskStateLogsByTaskId(Long taskId) {
        Criterion criterion = Restrictions.eq("task.id", taskId);
        return taskStateLogDAO.createCriteria(criterion).addOrder(Order.asc("operateTime")).list();
    }
}
