package com.partner.huihua.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.partner.huihua.enums.AccountSex;
import com.partner.huihua.enums.AccountStatus;
import com.partner.huihua.utils.base.MapperVO;



/**
 * mybatis 实体, 建议表
 * 表: HH_SUGGEST_INFO
 * @author jesse wang
 * @since 2015-3-9 下午13:53:59
 */
public class SuggestInfo extends MapperVO{

	//用户id
	private Long accountID;
	//内容
	private String content;
	
	public Long getAccountID() {
		return accountID;
	}
	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
