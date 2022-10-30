package com.cqsd.data.annotation.hander;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@Component
@Aspect
public class LoggerHandle {
	@Pointcut("@annotation(com.cqsd.data.annotation.Logger)")
	public void pointcut() {
	}
	
	@Around("pointcut()")
	public Object handleLog(ProceedingJoinPoint pjp) {
		final var req = RequestContextHolder.getRequestAttributes();
		
		
		var args = pjp.getArgs();
		return null;
	}
	
	/**
	 * 返回一个Spring对象上的annotation
	 *
	 * @param pjp        切入点
	 * @param annotation anntotaion 类型
	 * @param <T>        annotation
	 * @return null&annotation
	 */
	public static <T extends Annotation> T getAnnotation(ProceedingJoinPoint pjp, Class<T> annotation) {
		final var methodName = pjp.getSignature().getName();
		final var argsType = Arrays.stream(pjp.getArgs()).map(Object::getClass).toArray(Class<?>[]::new);
		try {
			final var method = pjp.getThis().getClass().getDeclaredMethod(methodName, argsType);
			return AnnotationUtils.findAnnotation(method, annotation);
		} catch (NoSuchMethodException e) {
			return null;
		}
	}
}
