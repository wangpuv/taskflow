package org.blue.taskflow.service.task;

import org.blue.taskflow.domain.entity.task.Task;
import org.blue.taskflow.domain.entity.task.TaskState;
import org.blue.taskflow.domain.entity.task.TaskStateLog;
import org.blue.taskflow.domain.manager.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-10
 * Time: 15:48:14
 */
@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskManager taskManager;

    @Override
    public void createTaskOperate(Task task) {
        task.setState(TaskState.TASK_STATE_CREATE.getSign());
        taskManager.createAndStoreTask(task);
    }

    @Override
    public void assignTaskOperate(Task task) {
        task.setState(TaskState.TASK_STATE_ASSIGN.getSign());
        taskManager.createAndStoreTask(task);
    }

    @Override
    public void acceptTaskOperate(Task task) {
        task.setState(TaskState.TASK_STATE_ACCEPT.getSign());
        taskManager.createAndStoreTask(task);
    }

    @Override
    public void refuseTaskOperate(Task task) {
        task.setState(TaskState.TASK_STATE_REFUSE.getSign());
        taskManager.createAndStoreTask(task);
    }

    @Override
    public void completeTaskOperate(Task task) {
        task.setState(TaskState.TASK_STATE_COMPLETE.getSign());
        taskManager.createAndStoreTask(task);
    }

    @Override
    public void postponeTaskOperate(Task task) {
        task.setState(TaskState.TASK_STATE_POSTPONE.getSign());
        taskManager.createAndStoreTask(task);
    }

    @Override
    public void closedTaskOperate(Task task) {
        task.setState(TaskState.TASK_STATE_CLOSED.getSign());
        taskManager.createAndStoreTask(task);
    }

    @Override
    public void taskStateLogging(TaskStateLog taskStateLog) {
        taskManager.createAndStoreTaskStateLog(taskStateLog);
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTask(Long id) {
        return taskManager.getTask(id);
    }

    @Override
    public void deleteTask(Long id) {
        taskManager.deleteTask(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> queryMyTasks(Long employeeId) {
        return taskManager.queryBothTasksByEmployeeId(employeeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskStateLog> queryTaskStateLogs(Long taskId) {
        return taskManager.getTaskStateLogsByTaskId(taskId);
    }
}
