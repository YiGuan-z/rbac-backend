package com.cqsd.data.service.base;


import com.cqsd.data.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public  interface BaseService<T,R extends QueryObject>  {
	default void save(T record){
		throw new RuntimeException("该方法未实现");
	}
	default void deleteById(Long id){
		throw new RuntimeException("该方法未实现");
	}
	default void updateById(T record){
		throw new RuntimeException("该方法未实现");
	}
	default T findById(Long id){
		throw new RuntimeException("该方法未实现");
	}
	default PageInfo<T> findByQueryObject(R qo){
		throw new RuntimeException("该方法未实现");
	}
	default List<T> selectAll(R queryObject){
		throw new RuntimeException("该方法未实现");
	}
}
