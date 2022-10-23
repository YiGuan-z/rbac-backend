package com.cqsd.data.annotation.hander;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Aspect
public class LoggerHandle {
	@Pointcut("@annotation(com.cqsd.data.annotation.Logger)")
	public void pointcut(){}
	@Around("pointcut()")
	public Object handleLog(ProceedingJoinPoint pjp){
		final var req = RequestContextHolder.currentRequestAttributes();
		
		var args = pjp.getArgs();
        return null;
	}
}
