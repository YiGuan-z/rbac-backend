package com.cqsd.auth.aop.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;

public class ProceedingJoinPointAnnotation {
	
	public static <T extends Annotation> T getAnnotation(ProceedingJoinPoint pjp, Class<T> annotation) {
		if (Objects.isNull(pjp)) return null;
		try {
			final var methodName = pjp.getSignature().getName();
			final var argsType = Arrays.stream(pjp.getArgs()).map(Object::getClass).toArray(Class<?>[]::new);
			final var method = pjp.getThis().getClass().getDeclaredMethod(methodName, argsType);
			return AnnotationUtils.findAnnotation(method, annotation);
		} catch (Exception e) {
			return null;
		}
	}
}
