package com.partner.huihua.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.partner.huihua.enums.AccountSex;
import com.partner.huihua.enums.AccountStatus;
import com.partner.huihua.utils.base.MapperVO;



/**
 * mybatis 实体, 公告 表
 * 表: HH_NOTICE_INFO
 * @author jesse wang
 * @since 2015-3-9 下午13:53:59
 */
public class NoticeInfo extends MapperVO{

	//公告名字
	private String noticeName;
	//公告页面地址
	private String pageUrl;
	
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	
	
}
