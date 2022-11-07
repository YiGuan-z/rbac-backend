package com.cqsd.net.base;

import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.base.BaseService;
import com.cqsd.data.vo.JsonResult;

public class HttpContrroller<T, R extends QueryObject, I extends BaseService<T, R>> extends BaseController<T, R, I> {
	public HttpContrroller(I service) {
		super(service);
	}
	/**
	 * 通过查询对象查询
	 *
	 * @param queryObject
	 * @return
	 */
	public JsonResult<?> getByQueryObject(R queryObject) {
		throw new NullPointerException("方法未实现");
	}
	
	/**
	 * 获取一个资源
	 *
	 * @param id
	 * @return
	 */
	public JsonResult<?> getById(Long id) {
		throw new NullPointerException("方法未实现");
	}
	
	/**
	 * 保存或更新一个资源，通过是否有id判断
	 *
	 * @param record
	 * @return
	 */
	public JsonResult<?> saveOrUpdate(T record) {
		throw new NullPointerException("方法未实现");
	}
	
	/**
	 * 删除一个资源
	 *
	 * @param id
	 * @return
	 */
	
	public JsonResult<?> deleteById(Long id) {
		throw new NullPointerException("方法未实现");
	}
}
