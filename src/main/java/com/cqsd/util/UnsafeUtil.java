package com.cqsd.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author caseycheng
 * @date 2022/11/7-21:34
 **/
public class UnsafeUtil {
	private static Unsafe unsafe = null;
	
	static {
		try {
			final var field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe = (Unsafe) field.get(null);
		} catch (Exception e) {
			throw new RuntimeException("unsafe获取异常，请检查模块权限", e);
		}
	}
	
	public static Unsafe getUnsafe() {
		return unsafe;
	}
}
