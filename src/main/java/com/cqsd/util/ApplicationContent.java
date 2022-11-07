package com.cqsd.util;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author caseycheng
 * @date 2022/11/7-15:44
 **/
abstract public class ApplicationContent {
	private static ConfigurableApplicationContext context = null;
	
	public static void setContext(ConfigurableApplicationContext context) {
		ApplicationContent.context = context;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) context.getBean(name);
	}
	
	public static <T> T getBean(String name, Class<T> clazz) {
		return context.getBean(name, clazz);
	}
}
