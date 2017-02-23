package com.partner.huihua.enums;

import java.util.HashMap;
import java.util.Map;

import com.partner.huihua.utils.base.MyBatisEnum;



/**
 * 个人账户状态
 * HH_ACCOUNT_INFO.sex字段值定义
 * @author jesse wang 
 * @since 2015-3-9 上午11:36:37
 */

public enum AccountSex implements MyBatisEnum {
	male(1),//男
	female(2);//女
	
	private int value;
	AccountSex(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	// 所有值对应的类型
	private static Map<Integer, AccountSex> valueLookup = new HashMap<Integer, AccountSex>();
	static {
		for (AccountSex type : AccountSex.values()) {
			valueLookup.put(type.value, type);
		}
	}
	// 按数字值检索类型
	public static AccountSex forValue(int value) {
		return valueLookup.get(value);
	}
}
