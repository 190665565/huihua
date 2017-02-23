package com.partner.huihua.mapper;

import com.partner.huihua.bean.AccountInfo;
import com.partner.huihua.bean.ProceedsInfo;
import com.partner.huihua.utils.base.BaseMapper;




/**
 * 用户收益操作接口
 * @author jesse wang
 * since 2015-3-10 下午18:18:18
 */
public interface ProceedsInfoMapper extends BaseMapper<ProceedsInfo> {


	/**
	 * 查找用户信息
	 * @param entity
	 * @return
	 */
	ProceedsInfo getObjByObj(ProceedsInfo entity);
	
	/**
	 * 查询总收益
	 * @param entity
	 * @return
	 */
	ProceedsInfo getProceedsInfo(ProceedsInfo entity);
}
