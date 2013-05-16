package org.blue.taskflow.domain.entity.task;

import org.blue.taskflow.domain.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 任务状态日志
 * <p/>
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-11-4
 * Time: 15:59:21
 */
@Entity
@Table(name = "TASK_STATELOG")
public class TaskStateLog extends IdEntity implements java.io.Serializable {

    private Task task;
    private Date operateTime;
    private String operateTaskState;
    private String operateEmployeeName;
    private String operateEmployeeLoginName;
    private String createEmployeeName;
    private String belongEmployeeName;

    public TaskStateLog() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TASK_ID")
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OPERATE_TIME", nullable = false)
    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Column(name = "OPERATE_TASK_STATE", nullable = false, length = 30)
    public String getOperateTaskState() {
        return operateTaskState;
    }

    public void setOperateTaskState(String operateTaskState) {
        this.operateTaskState = operateTaskState;
    }

    @Column(name = "OPERATE_EMPLOYEE_NAME", length = 100)
    public String getOperateEmployeeName() {
        return operateEmployeeName;
    }

    public void setOperateEmployeeName(String operateEmployeeName) {
        this.operateEmployeeName = operateEmployeeName;
    }

    @Column(name = "OPERATE_EMPLOYEE_LOGIN_NAME", length = 20)
    public String getOperateEmployeeLoginName() {
        return operateEmployeeLoginName;
    }

    public void setOperateEmployeeLoginName(String operateEmployeeLoginName) {
        this.operateEmployeeLoginName = operateEmployeeLoginName;
    }

    @Column(name = "CREATE_EMPLOYEE_NAME", length = 100)
    public String getCreateEmployeeName() {
        return createEmployeeName;
    }

    public void setCreateEmployeeName(String createEmployeeName) {
        this.createEmployeeName = createEmployeeName;
    }

    @Column(name = "BELONG_EMPLOYEE_NAME", length = 100)
    public String getBelongEmployeeName() {
        return belongEmployeeName;
    }

    public void setBelongEmployeeName(String belongEmployeeName) {
        this.belongEmployeeName = belongEmployeeName;
    }
}
