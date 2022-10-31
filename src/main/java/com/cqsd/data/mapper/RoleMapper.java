package com.cqsd.data.mapper;

import com.cqsd.data.entry.Role;
import com.cqsd.data.mapper.base.BaseMapper;
import com.cqsd.data.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RoleMapper extends BaseMapper<Role,QueryObject> {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();
    List<Role> selectForList(QueryObject queryObject);

    int updateByPrimaryKey(Role record);
}