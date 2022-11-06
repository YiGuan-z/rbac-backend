package com.cqsd.net.advice;

import com.cqsd.data.service.EmployeeService;
import com.cqsd.data.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(Throwable.class)
	public JsonResult<?> defaultExceptionHandler(Throwable e) {
		if (e instanceof EmployeeService.LoginExeption err) {
			return JsonResult.failed(401, err.getMessage());
		} else if (e instanceof RuntimeException err) {
			log.error("{}[未侦测的异常]{}", System.currentTimeMillis(), err.getMessage());
			return JsonResult.failed(501,"服务器出现异常，请参阅服务器日志");
		} else {
			log.error("{}[发现异常]{}", System.currentTimeMillis(), e.getMessage());
			return JsonResult.failed(500, "服务器出现异常，请查看服务器日志");
		}
		
	}
}
