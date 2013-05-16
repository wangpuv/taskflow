package org.blue.taskflow.domain.manager;

import org.blue.taskflow.domain.entity.log.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.blue.taskflow.domain.dao.log.OperateLogDAO;

@Component
public class LogManager {
	
	@Autowired
	private OperateLogDAO operateLogDAO;
	
	public void createAndStoreOperateLog(OperateLog operateLog) {
		operateLogDAO.save(operateLog);
	}
}
