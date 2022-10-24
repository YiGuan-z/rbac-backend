package com.cqsd.data.service.impl;

import com.cqsd.data.entry.SysMenus;
import com.cqsd.data.mapper.SysMenusMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.SysMenuService;
import com.cqsd.data.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenus,QueryObject> implements SysMenuService {
	public SysMenuServiceImpl(SysMenusMapper mapper) {
		super(mapper);
	}
}
