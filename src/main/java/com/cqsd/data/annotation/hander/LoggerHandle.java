package com.cqsd.data.annotation.hander;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerHandle {
	@Pointcut("@annotation(com.cqsd.data.annotation.Logger)")
	public void pointcut(){}
	@Around("pointcut()")
	public Object handleLog(ProceedingJoinPoint pjp){
        var args = pjp.getArgs();
        return null;
	}
}
