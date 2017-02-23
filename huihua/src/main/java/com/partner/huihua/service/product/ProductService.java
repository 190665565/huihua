package com.partner.huihua.service.product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.partner.huihua.bean.ProductInfo;

public interface ProductService {
	/**
	 * 查询产品列表
	 * @param entity
	 * @return
	 */
	JSONArray getProductInfo(ProductInfo entity);
	/**
	 * 根据id获取产品详情
	 * @param entity
	 * @return
	 */
	JSONObject getProductDetail(ProductInfo entity);
	
	/**
	 * 根据id获取产品详情
	 * @param entity
	 * @return
	 */
	ProductInfo getProductObject(ProductInfo entity);
	
	

}
