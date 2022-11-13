package com.cqsd.net.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author caseycheng
 * @date 2022/11/12-14:42
 **/
@Setter
@Getter
public class TokenInfo {
	@JsonIgnore
	private Date createTime=new Date();
	private Long id;
	private String userName;
	private String password;
	private String sing;
	private boolean login=false;
}
