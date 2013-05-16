package org.blue.taskflow.service.log;

import org.blue.taskflow.domain.entity.log.OperateLog;
import org.blue.taskflow.domain.manager.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-11-5
 * Time: 11:06:49
 */
@Service("logService")
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
	private LogManager logManager;

	public void operateLogging(OperateLog operateLog) {
		logManager.createAndStoreOperateLog(operateLog);
	}
}
