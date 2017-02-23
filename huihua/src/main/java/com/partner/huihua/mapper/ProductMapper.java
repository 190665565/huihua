package com.partner.huihua.mapper;

import java.util.List;

import com.partner.huihua.bean.PinObject;
import com.partner.huihua.bean.ProductInfo;
import com.partner.huihua.utils.base.BaseMapper;


/**
 * 产品操作接口
 * @author jesse wang
 * since 2015-3-10 下午18:18:18
 */
public interface ProductMapper extends BaseMapper<ProductInfo> {

	/**
	 * 查找产品信息
	 * @param pi
	 * @return
	 */
	List<ProductInfo> getProductByPage(ProductInfo pi);
	
	ProductInfo getProductDetail(ProductInfo pi);
	
	

}
