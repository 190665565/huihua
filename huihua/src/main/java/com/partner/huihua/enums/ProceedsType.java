package com.partner.huihua.enums;

import java.util.HashMap;
import java.util.Map;

import com.partner.huihua.utils.base.MyBatisEnum;



/**
 * HH_PROCEEDS_INFO.TYPE字段值定义（收益类型）
 * @author jesse wang 
 * @since 2015-3-9 上午11:36:37
 */

public enum ProceedsType implements MyBatisEnum {
	hp(1),//滑屏收益
	xz(2),//下载app
	sp(3),//视频
	tc(4);//弹窗
	
	private int value;
	ProceedsType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	// 所有值对应的类型
	private static Map<Integer, ProceedsType> valueLookup = new HashMap<Integer, ProceedsType>();
	static {
		for (ProceedsType type : ProceedsType.values()) {
			valueLookup.put(type.value, type);
		}
	}
	// 按数字值检索类型
	public static ProceedsType forValue(int value) {
		return valueLookup.get(value);
	}
	
}
