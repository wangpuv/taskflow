package org.blue.taskflow.web.task;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.blue.taskflow.domain.entity.organization.Employee;
import org.blue.taskflow.domain.entity.task.Task;
import org.blue.taskflow.domain.entity.task.TaskStateLog;
import org.blue.taskflow.rest.security.OperatorDetails;
import org.blue.taskflow.service.ServiceException;
import org.blue.taskflow.service.organization.OrganizationService;
import org.blue.taskflow.service.task.TaskService;
import org.blue.taskflow.web.CrudActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-10
 * Time: 15:23:16
 */
@Namespace("/task")
@Results({@Result(name = CrudActionSupport.RELOAD, location = "task.action?employeeId=${employeeId}", type = "redirect")})
public class TaskAction extends CrudActionSupport<Task> {

    @Autowired
    private TaskService taskService;

    @Autowired
    private OrganizationService organizationService;

    //-- 页面属性 --//
    private Long id;
    private Task entity;
    private List<Task> taskList = new ArrayList<Task>();
    private Long currentEmployeeId;
    private Long belongEmployeeId;
    private List<TaskStateLog> taskStateLogList = new ArrayList<TaskStateLog>();

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Task getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (id != null) {
            entity = taskService.getTask(id);
        } else {
            entity = new Task();
            OperatorDetails operatorDetails = SpringSecurityUtils.getCurrentUser();
            if (operatorDetails.getEmployee() != null) {
                entity.setCreateEmployee(operatorDetails.getEmployee());
            }
        }
    }

    @Override
    public String list() throws Exception {
        OperatorDetails operatorDetails = SpringSecurityUtils.getCurrentUser();
        if (operatorDetails.getEmployee() != null) {
            taskList = taskService.queryMyTasks(operatorDetails.getEmployee().getId());
            currentEmployeeId = operatorDetails.getEmployee().getId();
        }
        return SUCCESS;
    }

    @Override
    public String input() throws Exception {
        OperatorDetails operatorDetails = SpringSecurityUtils.getCurrentUser();
        if (operatorDetails.getEmployee() != null) {
            currentEmployeeId = operatorDetails.getEmployee().getId();
        }
        if (entity.getId() != null) {
            taskStateLogList = taskService.queryTaskStateLogs(entity.getId());
        }
        return INPUT;
    }

    @Override
    public String save() throws Exception {
        String msg;
        if (entity.getId() == null || this.belongEmployeeId == null) {
            taskService.createTaskOperate(entity);
            msg = "创建";
        } else {
            Employee employee = organizationService.getEmployee(this.belongEmployeeId);
            entity.setBelongEmployee(employee);
            taskService.assignTaskOperate(entity);
            msg = "分配";
        }
        addActionMessage(msg + "任务成功");
        return RELOAD;
    }

    /**
     * 在accept()前执行二次绑定.
     */
    public void prepareAccept() throws Exception {
        prepareModel();
    }

    public String accept() throws Exception {
        taskService.acceptTaskOperate(entity);
        addActionMessage("接受任务成功");
        return RELOAD;
    }

    /**
     * 在refuse()前执行二次绑定.
     */
    public void prepareRefuse() throws Exception {
        prepareModel();
    }

    public String refuse() throws Exception {
        taskService.refuseTaskOperate(entity);
        addActionMessage("拒绝任务成功");
        return RELOAD;
    }

    /**
     * 在complete()前执行二次绑定.
     */
    public void prepareComplete() throws Exception {
        prepareModel();
    }

    public String complete() throws Exception {
        entity = taskService.getTask(id);
        taskService.completeTaskOperate(entity);
        addActionMessage("完成任务成功");
        return RELOAD;
    }

    /**
     * 在postpone()前执行二次绑定.
     */
    public void preparePostpone() throws Exception {
        prepareModel();
    }

    public String postpone() throws Exception {
        entity = taskService.getTask(id);
        taskService.postponeTaskOperate(entity);
        addActionMessage("延期任务成功");
        return RELOAD;
    }

    /**
     * 在closed()前执行二次绑定.
     */
    public void prepareClosed() throws Exception {
        prepareModel();
    }

    public String closed() throws Exception {
        entity = taskService.getTask(id);
        taskService.closedTaskOperate(entity);
        addActionMessage("关闭任务成功");
        return RELOAD;
    }

    @Override
    public String delete() throws Exception {
        try {
            taskService.deleteTask(id);
            addActionMessage("删除任务成功");
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            addActionMessage("删除任务失败");
        }
        return RELOAD;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public Long getCurrentEmployeeId() {
        return currentEmployeeId;
    }

    public void setBelongEmployeeId(Long belongEmployeeId) {
        this.belongEmployeeId = belongEmployeeId;
    }

    public List<TaskStateLog> getTaskStateLogList() {
        return taskStateLogList;
    }
}
