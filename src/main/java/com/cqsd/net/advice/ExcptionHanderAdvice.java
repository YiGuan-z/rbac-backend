package com.cqsd.net.advice;

import com.cqsd.data.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExcptionHanderAdvice {
	@ExceptionHandler(Exception.class)
	public JsonResult<?> defaultExceptionHandler(Exception e){
		log.error("{}[发现异常]{}",System.currentTimeMillis(),e.getMessage());
		return JsonResult.failed(500,"服务器出现异常，请查看服务器日志");
	}
}
