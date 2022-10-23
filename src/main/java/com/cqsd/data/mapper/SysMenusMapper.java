package com.cqsd.data.mapper;

import com.cqsd.data.entry.SysMenus;
import java.util.List;

public interface SysMenusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenus record);

    SysMenus selectByPrimaryKey(Long id);

    List<SysMenus> selectAll();

    int updateByPrimaryKey(SysMenus record);
}