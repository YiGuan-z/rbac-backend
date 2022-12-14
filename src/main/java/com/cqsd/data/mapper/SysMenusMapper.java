package com.cqsd.data.mapper;

import com.cqsd.data.entry.SysMenus;
import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysMenusMapper extends BaseMapper<SysMenus, QueryObject> {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenus record);

    SysMenus selectByPrimaryKey(Long id);

    List<SysMenus> selectAll();

    int updateByPrimaryKey(SysMenus record);
	
	List<SysMenus> selectAll(boolean all);
    List<SysMenus> selectByEmployeeId(Long id);
}