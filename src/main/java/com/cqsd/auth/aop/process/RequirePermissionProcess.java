package com.cqsd.auth.aop.process;

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
public class RequirePermissionProcess {
	@Pointcut("@annotation(com.cqsd.auth.aop.annotation.RequirePermission)")
	public void pointcut() {
	}
	
	@Around("pointcut()")
	public Object handleLog(ProceedingJoinPoint pjp) {
		final var req =RequestContextHolder.getRequestAttributes();
		
		
		
		var args = pjp.getArgs();
		return null;
	}
	
	/**
	 * 返回一个Spring对象上的annotation
	 *
	 * @param pjp        切入点
	 * @param annotation anntotaion 类型
	 * @param <A>        annotation
	 * @return null&annotation
	 */
	public static <A extends Annotation> A getAnnotation(ProceedingJoinPoint pjp, Class<A> annotation) {
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
