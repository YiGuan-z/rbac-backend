package com.cqsd.data.mapper;

import com.cqsd.data.entry.dist.DictType;
import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictTypeMapper extends BaseMapper<DictType, QueryObject> {
	
	int deleteByPrimaryKey(Long id);
	
	
	int insert(DictType record);
	
	
	DictType selectByPrimaryKey(Long id);
	
	List<DictType> selectAll();
	
	
	int updateByPrimaryKey(DictType record);
	
	
	List<DictType> findByQueryObject(QueryObject qo);
}
