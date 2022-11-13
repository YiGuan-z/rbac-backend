package com.cqsd.util;

import com.cqsd.data.utils.JavaBeanDef;
import org.springframework.beans.BeanUtils;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

import java.io.Serializable;

/**
 * @author caseycheng
 * @date 2022/11/5-10:25
 **/
abstract public class AutoCopy {
	
	public static <T extends Serializable> T of(Object resource, Class<T> clazz) {
		try {
			final var instance = JavaBeanDef.newInstance(clazz);
			BeanUtils.copyProperties(resource, instance);
			return instance;
		} catch (Exception e) {
			return null;
		}
	}
}
