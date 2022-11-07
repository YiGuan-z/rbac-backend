package com.cqsd.data.mapper;

import com.cqsd.data.entry.dist.DictData;
import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictDataMapper extends BaseMapper<DictData, QueryObject> {
	
	int deleteByPrimaryKey(Long id);
	
	
	int insert(DictData record);
	
	
	DictData selectByPrimaryKey(Long id);
	
	
	List<DictData> selectAll();
	
	
	int updateByPrimaryKey(DictData record);
	
	
	List<DictData> findByQueryObject(QueryObject qo);
}
