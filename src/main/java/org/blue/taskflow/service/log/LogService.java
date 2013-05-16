package org.blue.taskflow.service.log;

import org.blue.taskflow.domain.entity.log.OperateLog;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-11-5
 * Time: 11:06:29
 */
public interface LogService {

    /**
     * 在数据库中增加操作记录
     *
     * @param operateLog 操作记录
     */
    void operateLogging(OperateLog operateLog);
}
