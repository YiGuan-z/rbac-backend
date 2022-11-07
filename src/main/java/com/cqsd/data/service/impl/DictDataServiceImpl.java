package com.cqsd.data.service.impl;

import com.cqsd.data.entry.dist.DictData;
import com.cqsd.data.mapper.DictDataMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.DictDataService;
import com.cqsd.data.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author caseycheng
 * @date 2022/11/7-16:42
 **/
@Service
public class DictDataServiceImpl extends BaseServiceImpl<DictData, QueryObject, DictDataMapper> implements DictDataService {
	public DictDataServiceImpl(DictDataMapper mapper) {
		super(mapper);
	}
}
