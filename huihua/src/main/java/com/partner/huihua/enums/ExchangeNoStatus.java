package com.partner.huihua.enums;

import java.util.HashMap;
import java.util.Map;

import com.partner.huihua.utils.base.MyBatisEnum;



/**
 * HH_EXCHANGE_VALIDATE.STATUS  兑换码状态
 * @author jesse wang 
 * @since 2016-1-28 上午11:36:37
 */

public enum ExchangeNoStatus implements MyBatisEnum {
	INVALID(0),//无效
	VALID(1);//有效
	
	private int value;
	ExchangeNoStatus(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	// 所有值对应的类型
	private static Map<Integer, ExchangeNoStatus> valueLookup = new HashMap<Integer, ExchangeNoStatus>();
	static {
		for (ExchangeNoStatus type : ExchangeNoStatus.values()) {
			valueLookup.put(type.value, type);
		}
	}
	// 按数字值检索类型
	public static ExchangeNoStatus forValue(int value) {
		return valueLookup.get(value);
	}
}
