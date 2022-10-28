package com.cqsd.data.service;

import com.cqsd.data.entry.Employee;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.base.BaseService;

import java.util.List;

public interface EmployeeService extends BaseService<Employee, QueryObject> {
	String login(String username, String password) throws LoginExeption;
	
	void deleteByIds(List<Long> ids);
	
	class LoginExeption extends Exception {
		/**
		 * Constructs a new exception with the specified detail message.  The
		 * cause is not initialized, and may subsequently be initialized by
		 * a call to {@link #initCause}.
		 *
		 * @param message the detail message. The detail message is saved for
		 *                later retrieval by the {@link #getMessage()} method.
		 */
		public LoginExeption(String message) {
			super(message);
		}
	}
	
}
