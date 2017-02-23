package com.partner.huihua.enums;

import java.util.HashMap;
import java.util.Map;

import com.partner.huihua.utils.base.MyBatisEnum;



/**
 * 个人账户状态
 * SP_ACCOUNT_INFO.ACCOUNT_STATUS字段值定义
 * @author jesse wang 
 * @since 2015-3-9 上午11:36:37
 */

public enum AccountStatus implements MyBatisEnum {
	NORMAL(0),//正常
	FREEZ(1),//冻结
	FORBIDDEN(2);//禁用
	
	private int value;
	AccountStatus(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	// 所有值对应的类型
	private static Map<Integer, AccountStatus> valueLookup = new HashMap<Integer, AccountStatus>();
	static {
		for (AccountStatus type : AccountStatus.values()) {
			valueLookup.put(type.value, type);
		}
	}
	// 按数字值检索类型
	public static AccountStatus forValue(int value) {
		return valueLookup.get(value);
	}
}
