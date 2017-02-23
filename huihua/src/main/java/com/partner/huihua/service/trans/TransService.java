package com.partner.huihua.service.trans;

import net.sf.json.JSONArray;

import com.partner.huihua.bean.ProductInfo;
import com.partner.huihua.bean.TransInfo;

public interface TransService {
	/**
	 * 兑换
	 * @param entity
	 * @return
	 */
	void exchange(TransInfo entity);
	
	/**
	 * 查询兑换列表
	 * @param entity
	 * @return
	 */
	JSONArray getTransInfo(TransInfo entity);
	
	/**
	 * 查询兑换详情
	 * @param entity
	 * @return
	 */
	TransInfo getTransDetail(TransInfo entity);
	
	

}
