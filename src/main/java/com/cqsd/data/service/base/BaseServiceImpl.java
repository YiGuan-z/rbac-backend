package com.cqsd.data.service.base;

import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

abstract public class BaseServiceImpl<T, R extends QueryObject, I extends BaseMapper<T, R>> implements BaseService<T, R> {
	protected final I mapper;
	
	public BaseServiceImpl(I mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public void save(T record) {
		mapper.insert(record);
	}
	
	@Override
	public void deleteById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void updateById(T record) {
		mapper.updateByPrimaryKey(record);
	}
	
	@Override
	public T findById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public PageInfo<T> findByQueryObject(R qo) {
		final Page<T> page = PageHelper.startPage(qo.current(), qo.limit());
		mapper.selectAll();
		return PageInfo.of(page);
	}
	
	public List<T> selectAll(){
		return mapper.selectAll();
	}
}
