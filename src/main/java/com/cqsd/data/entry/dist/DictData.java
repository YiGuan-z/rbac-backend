package com.cqsd.data.entry.dist;

import lombok.Getter;
import lombok.Setter;

/**
 * @author caseycheng
 * @date 2022/11/7-15:59
 **/
@Setter
@Getter
public class DictData {
	private Long id;
	private String dictType;
	private String label;
	private String value;
	private Integer seq;
	private String remark;
}
