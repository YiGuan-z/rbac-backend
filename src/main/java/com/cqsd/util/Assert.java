package com.cqsd.util;

/**
 * @author caseycheng
 * @date 2022/11/13-09:28
 **/
abstract public class Assert {
	/**
	 * 如果flag里面判定为true，就抛出右边的异常
	 *
	 * @param flag
	 * @param throwable
	 */
	public static void ifTrue(boolean flag, Throwable throwable) {
		if (flag) throw new RuntimeException(throwable);
	}
	
	public static void ifTrue(boolean flag, String message) {
		if (flag) throw new RuntimeException(message);
	}
	
	public static void ifTrue(boolean flag) {
		if (flag) throw new RuntimeException();
	}
}
