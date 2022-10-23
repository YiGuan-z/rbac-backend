package com.cqsd.net.base;


import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.vo.JsonResult;


abstract public class BaseController<T, R extends QueryObject> {
	/**
	 * 通过查询对象查询
	 *
	 * @param queryObject
	 * @return
	 */
	abstract public JsonResult<?> getByQueryObject(R queryObject);
	
	/**
	 * 获取一个资源
	 *
	 * @param id
	 * @return
	 */
	abstract public JsonResult<?> getById(Long id);
	
	/**
	 * 保存或更新一个资源，通过是否有id判断
	 *
	 * @param department
	 * @return
	 */
	abstract public JsonResult<?> saveOrUpdate(T department);
	
	/**
	 * 删除一个资源
	 *
	 * @param id
	 * @return
	 */
	
	abstract public JsonResult<?> deleteById(Long id);
}
