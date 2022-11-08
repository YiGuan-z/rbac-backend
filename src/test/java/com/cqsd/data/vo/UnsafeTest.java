package com.cqsd.data.vo;

import com.cqsd.data.entry.Employee;
import org.junit.jupiter.api.Test;
import org.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

/**
 * @author caseycheng
 * @date 2022/11/7-20:14
 **/
public class UnsafeTest {
	private final Unsafe unsafe= UnsafeUtils.getUnsafe();
	@Test
	void clazzTestFiled() throws NoSuchFieldException {
		final var employee = new Employee();
		employee.setAge(32);
		employee.setUsername("casey");
		System.out.println(employee);
		final Class<? extends Employee> employeeClass = employee.getClass();
		final var age = employeeClass.getDeclaredField("age");
		final var username = employeeClass.getDeclaredField("username");
		unsafe.putObject(employee,unsafe.objectFieldOffset(username),"ni");
		unsafe.putObject(employee,unsafe.objectFieldOffset(age),Integer.valueOf(25));
		System.out.println(unsafe.getObject(employee,unsafe.objectFieldOffset(username)));
		System.out.println(employee);
		
		
	}
}
