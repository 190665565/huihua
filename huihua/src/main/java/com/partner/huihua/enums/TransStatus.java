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

public enum TransStatus implements MyBatisEnum {
	success(0),//完成
	processing(1),//处理中
	failed(2);//失败
	
	private int value;
	TransStatus(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	// 所有值对应的类型
	private static Map<Integer, TransStatus> valueLookup = new HashMap<Integer, TransStatus>();
	static {
		for (TransStatus type : TransStatus.values()) {
			valueLookup.put(type.value, type);
		}
	}
	// 按数字值检索类型
	public static TransStatus forValue(int value) {
		return valueLookup.get(value);
	}
}
