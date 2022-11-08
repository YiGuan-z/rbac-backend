package com.cqsd.data.service.base;


import com.cqsd.data.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BaseService<T, R extends QueryObject> {
	void save(T record);
	
	void deleteById(Long id);
	
	void updateById(T record);
	
	T findById(Long id);
	
	PageInfo<T> findByQueryObject(R qo);
	
	List<T> selectAll();
	
}
