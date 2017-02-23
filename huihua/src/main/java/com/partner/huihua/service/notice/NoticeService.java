package com.partner.huihua.service.notice;

import net.sf.json.JSONArray;

import com.partner.huihua.bean.NoticeInfo;

public interface NoticeService {

	
	/**
	 * 查询公告列表
	 * @param entity
	 * @return
	 */
	JSONArray getNoticesInfo(NoticeInfo entity);
	
	
	

}
