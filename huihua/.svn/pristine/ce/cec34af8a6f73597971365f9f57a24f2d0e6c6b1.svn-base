package com.partner.huihua.utils.common;

import java.text.DecimalFormat;

/**
 * 数字格式化工具类
 * @author rainyhao
 * @since 2015-3-11 下午5:01:57
 */
public final class NumberUtil {

	/**
	 * 将指定的数字格式化为两位小数的字符串形式
	 * @author rainyhao 
	 * @since 2015-3-11 下午5:06:35
	 * @param number 数字
	 * @return
	 */
	public static final String format(Object number) {
		return format(number, "0.00");
	}
	
	/**
	 * 把指定的数按指定的格式进行格式化
	 * @author rainyhao 
	 * @since 2015-3-11 下午5:04:31
	 * @param number 数字
	 * @param pattern 数字格式
	 * @return
	 */
	public static final String format(Object number, String pattern) {
		DecimalFormat fmt = new DecimalFormat(pattern);
		return fmt.format(number);
	}
}
