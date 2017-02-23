package com.partner.huihua.utils.common;

import com.partner.huihua.utils.exception.UnsatisfiedParamException;

public class TransUtil {
	
	public  static String getTransNo(long accountid){
		// 流水号: 前缀 + 当前日期 (+ 18位数字, 数字用指定的序列值)
		String transno = "TN" + DateUtil.formatNow("yyyyMMddHHmmss");
		// 指定序列的下个值
		// 不足18位的序列值前边补0
		transno += NumberUtil.format(accountid, "00000000000000");
		if (transno.length() > 30) {
			throw new UnsatisfiedParamException("流水号总长度不得大于30个字符(网关要求)");
		}
		
		
		return transno;
	}


}
