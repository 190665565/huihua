package com.partner.huihua.enums;

import java.util.HashMap;
import java.util.Map;

import com.partner.huihua.utils.base.MyBatisEnum;



/**
 * HH_VERIFICATION _INFO.TYPE字段值定义
 * @author jesse wang 
 * @since 2015-3-9 上午11:36:37
 */

public enum VerType implements MyBatisEnum {
	PWD(2),//修改密码
	MOBILE(1);//绑定、修改手机
	
	private int value;
	VerType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	// 所有值对应的类型
	private static Map<Integer, VerType> valueLookup = new HashMap<Integer, VerType>();
	static {
		for (VerType type : VerType.values()) {
			valueLookup.put(type.value, type);
		}
	}
	// 按数字值检索类型
	public static VerType forValue(int value) {
		return valueLookup.get(value);
	}
}
