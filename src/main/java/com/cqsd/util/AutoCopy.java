package com.cqsd.util;

import org.springframework.beans.BeanUtils;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

import java.io.Serializable;

/**
 * @author caseycheng
 * @date 2022/11/5-10:25
 **/
abstract public class AutoCopy {
	private static final Unsafe unsafe = UnsafeUtils.getUnsafe();
	
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T of(Object resource, Class<T> clazz) {
		try {
			final var instance = unsafe.allocateInstance(clazz);
			BeanUtils.copyProperties(resource, instance);
			return (T) instance;
		} catch (Exception e) {
			return null;
		}
	}
}
