package com.partner.huihua.mapper;

import java.util.List;

import com.partner.huihua.bean.ProductInfo;
import com.partner.huihua.bean.TransInfo;
import com.partner.huihua.utils.base.BaseMapper;


/**
 * 产品操作接口
 * @author jesse wang
 * since 2015-3-10 下午18:18:18
 */
public interface TransMapper extends BaseMapper<TransInfo> {

	/**
	 * 查找交易信息
	 * @param pi
	 * @return
	 */
	List<TransInfo> getTransByPage(TransInfo ti);
	/**
	 * 查找交易信息
	 * @param pi
	 * @return
	 */
	/**
	 * 查找交易详情
	 * @param pi
	 * @return
	 */
	TransInfo getTransDetail(TransInfo ti);
	
	

}
