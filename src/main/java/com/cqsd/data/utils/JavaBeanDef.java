package com.cqsd.data.utils;

import com.cqsd.util.UnsafeUtil;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author caseycheng
 * @date 2022/11/7-21:31
 **/
public class JavaBeanDef<T> {
	private final static Unsafe unsafe = UnsafeUtil.getUnsafe();
	private final Class<T> clazz;
	private final T value;
	private final Map<Field, Long> map = new HashMap<>();
	
	public JavaBeanDef(Class<T> clazz) {
		this.clazz = clazz;
		map.putAll(Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toMap((Field v) -> v, unsafe::objectFieldOffset)));
		this.value = newInstance();
	}
	
	/**
	 * 检查一个字段是否已解析
	 *
	 * @param field 字段
	 * @return 是否解析
	 */
	
	public boolean concat(Field field) {
		return map.containsKey(field);
	}
	
	/**
	 * 直接创建一个对象，绕过构造函数和类型检查
	 *
	 * @return 一个空的实例对象
	 */
	public T newInstance() {
		return newInstance(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<? extends T> clazz) {
		try {
			return (T) unsafe.allocateInstance(clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 源对象 偏移量 值对象
	 *
	 * @param resource 源对象
	 * @param field    字段
	 * @param value    值对象
	 */
	private void setFieldValue(Object resource, Field field, Object value) throws DangerousoperationException {
		hit(field);
		unsafe.putObject(resource, map.get(field), value);
	}
	
	/**
	 * 获取字段值
	 *
	 * @param resource 源对象
	 * @param field    字段
	 * @return 源对象的字段值
	 * @throws DangerousoperationException 未解析字段异常
	 */
	private Object getFieldValue(Object resource, Field field) throws DangerousoperationException {
		hit(field);
		return unsafe.getObject(resource, map.get(field));
	}
	
	/**
	 * 设置一个字段值
	 *
	 * @param field 字段
	 * @param value 值
	 * @throws DangerousoperationException 未解析字段异常
	 */
	public void setFieldValue(Field field, Object value) throws DangerousoperationException {
		setFieldValue(this.value, field, value);
	}
	
	/**
	 * 获取一个字段值
	 *
	 * @param field 字段
	 * @return 该字段的值
	 * @throws DangerousoperationException 未解析字段异常
	 */
	public Object getFieldValue(Field field) throws DangerousoperationException {
		return getFieldValue(this.value, field);
	}
	
	/**
	 * 原子性操作，给对象的int设置一个值
	 * 包装类型请使用getAndSetObject
	 *
	 * @param field    字段
	 * @param newValue 该字段的值
	 * @return 更新前的字段值
	 * @throws DangerousoperationException 未解析字段异常
	 */
	public int getAndSetInt(Field field, int newValue) throws DangerousoperationException {
		return getAndSetInt(this.value, field, newValue);
	}
	
	/**
	 * 原子性操作，给对象的Long设置一个值
	 *
	 * @param field    字段
	 * @param newValue 该字段的值
	 * @return 更新前的字段值
	 * @throws DangerousoperationException 未解析字段异常
	 */
	public long getAndSetLong(Field field, long newValue) throws DangerousoperationException {
		return getAndSetLong(this.value, field, newValue);
	}
	
	/**
	 * 原子性操作，给对象的int增加一个值
	 *
	 * @param field 字段
	 * @param delta 该字段的值
	 * @return 更新前的字段值
	 * @throws DangerousoperationException 未解析字段异常
	 */
	public int getAndAddInt(Field field, int delta) throws DangerousoperationException {
		return getAndAddInt(this.value, field, delta);
	}
	
	/**
	 * 原子性操作，给对象的long增加一个值
	 *
	 * @param field 字段
	 * @param delta 加上的值
	 * @return 更新前的值
	 * @throws DangerousoperationException 未解析字段异常
	 */
	public long getAndAddLong(Field field, long delta) throws DangerousoperationException {
		return getAndAddLong(this.value, field, delta);
	}
	
	/**
	 * 原子性操作，给字段设置并更新一个对象
	 *
	 * @param field 字段
	 * @param delta 新对象
	 * @return 更新前的对象
	 * @throws DangerousoperationException 未解析字段异常
	 */
	public Object getAndSetObject(Field field, Object delta) throws DangerousoperationException {
		return getAndSetObject(this.value, field, delta);
	}
	
	private int getAndSetInt(Object resource, Field field, int newValue) throws DangerousoperationException {
		hit(field);
		checkClass(field.getType() != int.class);
		return unsafe.getAndSetInt(resource, map.get(field), newValue);
	}
	
	private long getAndSetLong(Object resource, Field field, long newValue) throws DangerousoperationException {
		hit(field);
		checkClass(field.getType() != long.class);
		return unsafe.getAndSetLong(resource, map.get(field), newValue);
	}
	
	private long getAndAddLong(Object resource, Field field, long delta) throws DangerousoperationException {
		hit(field);
		checkClass(field.getType() != long.class);
		return unsafe.getAndAddLong(resource, map.get(field), delta);
	}
	
	private int getAndAddInt(Object resource, Field field, int delta) throws DangerousoperationException {
		hit(field);
		checkClass(field.getType() != int.class);
		return unsafe.getAndAddInt(resource, map.get(field), delta);
	}
	
	private Object getAndSetObject(Object resource, Field field, Object newValue) throws DangerousoperationException {
		hit(field);
		return unsafe.getAndSetObject(resource, map.get(field), newValue);
	}
	
	private void hit(Field field) throws DangerousoperationException {
		if (!concat(field)) throw new DangerousoperationException("未命中目标的偏移量，无法继续");
	}
	
	private static void checkClass(boolean field) throws DangerousoperationException {
		if (field) {
			throw new DangerousoperationException("包装类型请使用getAndSetObject");
		}
	}
	
	public Object getValue() {
		return value;
	}
	
	static class DangerousoperationException extends Exception {
		/**
		 * Constructs a new exception with the specified detail message.  The
		 * cause is not initialized, and may subsequently be initialized by
		 * a call to {@link #initCause}.
		 *
		 * @param message the detail message. The detail message is saved for
		 *                later retrieval by the {@link #getMessage()} method.
		 */
		public DangerousoperationException(String message) {
			super(message);
		}
	}
	
}
