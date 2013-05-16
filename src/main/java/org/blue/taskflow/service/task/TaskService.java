package org.blue.taskflow.service.task;

import org.blue.taskflow.domain.entity.task.Task;
import org.blue.taskflow.domain.entity.task.TaskStateLog;

import java.util.List;

/**
 * 任务管理服务接口
 * <p/>
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-10
 * Time: 15:27:52
 */
public interface TaskService {

    /**
     * 创建任务
     *
     * @param task 任务
     */
    void createTaskOperate(Task task);

    /**
     * 分配任务
     *
     * @param task 任务
     */
    void assignTaskOperate(Task task);

    /**
     * 接受任务
     *
     * @param task 任务
     */
    void acceptTaskOperate(Task task);

    /**
     * 拒绝任务
     *
     * @param task 任务
     */
    void refuseTaskOperate(Task task);

    /**
     * 完成任务
     *
     * @param task 任务
     */
    void completeTaskOperate(Task task);

    /**
     * 延期任务
     *
     * @param task 任务
     */
    void postponeTaskOperate(Task task);

    /**
     * 关闭任务
     *
     * @param task 任务
     */
    void closedTaskOperate(Task task);

    /**
     * 添加任务状态日志
     *
     * @param taskStateLog 任务状态日志
     */
    void taskStateLogging(TaskStateLog taskStateLog);

    /**
     * 获取机构通过任务ID
     *
     * @param id 任务ID
     * @return 任务
     */
    Task getTask(Long id);

    /**
     * 删除任务
     *
     * @param id 任务ID
     */
    void deleteTask(Long id);

    /**
     * 查询我的任务
     *
     * @param employeeId 职员ID
     * @return 任务集合
     */
    List<Task> queryMyTasks(Long employeeId);

    /**
     * 查询任务状态日志
     *
     * @param taskId 任务ID
     * @return 任务状态日志集合
     */
    List<TaskStateLog> queryTaskStateLogs(Long taskId);
}
