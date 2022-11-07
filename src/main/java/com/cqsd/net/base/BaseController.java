package com.cqsd.net.base;


import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.base.BaseService;
import com.cqsd.data.vo.JsonResult;
import org.springframework.util.Assert;


abstract public class BaseController<T, R extends QueryObject, I extends BaseService<T, R>> {
	protected final I service;
	
	public BaseController(I service) {
		Assert.notNull(service, String.format("[空指针]:在%s中%s空指针了", this.getClass(), service.getClass()));
		this.service = service;
	}
}
