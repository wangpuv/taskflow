package org.blue.taskflow.rest.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.blue.taskflow.domain.entity.log.OperateLog;
import org.blue.taskflow.domain.entity.task.Task;
import org.blue.taskflow.domain.entity.task.TaskStateLog;
import org.blue.taskflow.rest.security.OperatorDetails;
import org.blue.taskflow.service.log.LogService;
import org.blue.taskflow.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import java.util.Date;

/**
 * 日志记录
 *
 * @author blue
 */
@Aspect
@Component
public class LogUtil {
    private static final Log LOG = LogFactory.getLog(LogUtil.class);

    @Autowired
    private LogService logService;

    @Autowired
    private TaskService taskService;

    @Pointcut("execution(* org.blue.taskflow.service..*ServiceImpl.*(..))")
    public void textLogInsertPointcut() {
    }

    @After("textLogInsertPointcut()")
    public void textLogging(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String className = String.valueOf(signature.getDeclaringType());
        String methodName = signature.getName();
        LOG.info("#######日志记录：" + className + "." + methodName + "被执行……######");
    }

    @Pointcut("execution(* org.blue.taskflow.service..UserDetailsServiceImpl.loadUserByUsername(..))")
    public void loginLogInsertPointcut() {
    }

    @AfterReturning(pointcut = "loginLogInsertPointcut()", returning = "result")
    public void loginLogging(JoinPoint joinPoint, OperatorDetails result) {
        if (result != null) {
            OperateLog operateLog = new OperateLog();
            operateLog.setUserLoginName(result.getUsername());
            if (result.getEmployee() != null) {
                operateLog.setOrganizationName(result.getEmployee().getOrganization().getName());
                operateLog.setEmployeeName(result.getEmployee().getName());
            }
            operateLog.setOperateType("登录");
            operateLog.setOperateTime(new Date());
            logService.operateLogging(operateLog);
        }
    }

    @Pointcut("execution(* org.blue.taskflow.service.task.TaskServiceImpl.*TaskOperate(..))")
    public void taskStateLogInsertPointcut() {
    }

    @AfterReturning(pointcut = "taskStateLogInsertPointcut()")
    public void taskStateLogging(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        Task task = (Task) objects[0];
        TaskStateLog taskStateLog = new TaskStateLog();
        taskStateLog.setTask(task);
        taskStateLog.setOperateTime(new Date());
        OperatorDetails operatorDetails = SpringSecurityUtils.getCurrentUser();
        Assert.notNull(operatorDetails.getEmployee());
        taskStateLog.setOperateEmployeeName(operatorDetails.getEmployee().getName());
        taskStateLog.setOperateEmployeeLoginName(operatorDetails.getEmployee().getUsers().iterator().next().getLoginName());
        taskStateLog.setOperateTaskState(task.getState());
        taskStateLog.setCreateEmployeeName(task.getCreateEmployee().getName());
        taskStateLog.setBelongEmployeeName(task.getBelongEmployee() == null ? null : task.getBelongEmployee().getName());
        taskService.taskStateLogging(taskStateLog);
    }
}
