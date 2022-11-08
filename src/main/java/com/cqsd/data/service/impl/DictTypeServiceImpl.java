package com.cqsd.data.service.impl;

import com.cqsd.data.entry.dist.DictType;
import com.cqsd.data.mapper.DictTypeMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.DictTypeService;
import com.cqsd.data.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caseycheng
 * @date 2022/11/7-16:12
 **/
@Service
public class DictTypeServiceImpl extends BaseServiceImpl<DictType,QueryObject, DictTypeMapper> implements DictTypeService {
	
	public DictTypeServiceImpl(DictTypeMapper mapper) {
		super(mapper);
	}
}
