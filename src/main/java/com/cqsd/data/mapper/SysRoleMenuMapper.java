package com.cqsd.data.mapper;

import com.cqsd.data.entry.SysRoleMenu;
import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu, QueryObject> {
	int insert(SysRoleMenu record);
	
	List<SysRoleMenu> selectAll();
	
	void save(@Param("id") Long id, @Param("menusId") ArrayList<Long> menusId);
	
	 int deleteByPrimaryKey(Long id);
	 List<SysRoleMenu> selectMenuIdByRoleId(Long id);
}