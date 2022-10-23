package com.cqsd.data.mapper;

import com.cqsd.data.entry.SysRoleMenu;
import java.util.List;

public interface SysRoleMenuMapper {
    int insert(SysRoleMenu record);

    List<SysRoleMenu> selectAll();
}