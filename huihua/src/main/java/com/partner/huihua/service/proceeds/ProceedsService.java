package com.partner.huihua.service.proceeds;

import net.sf.json.JSONArray;

import com.partner.huihua.bean.ProceedsInfo;

public interface ProceedsService {
	/**
	 *  更新或插入
	 */
	public void updateOrinsert(ProceedsInfo proceedsinfo);
	
	/**
	 *  收益累加
	 */
	public String add(Long accountID, JSONArray subsidies);
	/**
	 *  获取收益信息
	 */
	public ProceedsInfo getpcds(ProceedsInfo proceedsinfo);
	/**
	 * 查询总收益
	 * @param entity
	 * @return
	 */
	ProceedsInfo getProceedsInfo(ProceedsInfo entity);
	
	

}
