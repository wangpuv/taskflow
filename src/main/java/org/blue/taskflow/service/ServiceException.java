package org.blue.taskflow.service;

/**
 * Service层公用的Exception.
 *
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 *
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-8-27
 * Time: 11:06:13
 */
public class ServiceException extends RuntimeException {

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
