package com.cqsd.data.utils;

import com.cqsd.data.entry.Employee;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class JavaBeanDefTest {
	@Test
	void testBeanDef() throws NoSuchFieldException, JavaBeanDef.DangerousoperationException {
		final var def = new JavaBeanDef<Employee>(Employee.class);
		final var employee = def.getValue();
		final var name = Employee.class.getDeclaredField("name");
		final var age = Employee.class.getDeclaredField("age");
		
		System.out.println(employee);
		def.setFieldValue(name,"你好");
		final var oldValue = def.getAndSetObject(age, 5);
		System.out.println(oldValue);
		System.out.println();
		System.out.println(employee);
	}
	
}