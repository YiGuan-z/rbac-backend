package com.cqsd.data.mapper.base;

import com.cqsd.data.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//@Mapper
public interface BaseMapper<T, Q extends QueryObject> {
	default int deleteByPrimaryKey(Long id) {
		throw new RuntimeException("方法未实现");
	}
	
	default int insert(T record) {
		throw new RuntimeException("方法未实现");
	}
	
	default T selectByPrimaryKey(Long id) {
		throw new RuntimeException("方法未实现");
	}
	
	default List<T> selectAll() {
		throw new RuntimeException("方法未实现");
	}
	
	default int updateByPrimaryKey(T record) {
		throw new RuntimeException("方法未实现");
	}
	
	default List<T> findByQueryObject(Q qo) {
		throw new RuntimeException("方法未实现");
	}
}
