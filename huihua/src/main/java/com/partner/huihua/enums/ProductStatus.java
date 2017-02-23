package com.partner.huihua.enums;

import java.util.HashMap;
import java.util.Map;

import com.partner.huihua.utils.base.MyBatisEnum;



/**
 * 个人账户状态
 * SP_PRODUCT_INFO.STATUS字段值定义
 * @author jesse wang 
 * @since 2015-3-9 上午11:36:37
 */

public enum ProductStatus implements MyBatisEnum {
	have(1),//有货
	quehuo(2);//缺货
	
	private int value;
	ProductStatus(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	// 所有值对应的类型
	private static Map<Integer, ProductStatus> valueLookup = new HashMap<Integer, ProductStatus>();
	static {
		for (ProductStatus type : ProductStatus.values()) {
			valueLookup.put(type.value, type);
		}
	}
	// 按数字值检索类型
	public static ProductStatus forValue(int value) {
		return valueLookup.get(value);
	}
}
