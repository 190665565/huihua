package com.partner.huihua.utils.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则检查工具
 * @author rainyhao
 * @since 2015-3-10 上午10:34:25
 */
public final class RegExp {
	
	//电话号码11位
	private static final Pattern MOBILE= Pattern.compile("^\\d{11}");
	// 正整数
	private static final Pattern DIGIT = Pattern.compile("^\\d+$");
	
	// 浮点数
	private static final Pattern FLOAT = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
	
	// email格式
	private static final Pattern EMAIL = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	
	
	/**
	 * 检查字符串是否为11位电话号码
	 * @since 2015-3-10 上午10:39:19
	 * @param number 数字形式的字符串
	 * @return
	 */
	public static final boolean isMobile(String number) {
		Matcher m = MOBILE.matcher(number);
		return m.matches();
	}
	/**
	 * 检查字符串是否为正整数
	 * @author rainyhao 
	 * @since 2015-3-10 上午10:39:19
	 * @param number 数字形式的字符串
	 * @return
	 */
	public static final boolean isDigit(String number) {
		Matcher m = DIGIT.matcher(number);
		return m.matches();
	}
	
	/**
	 * 检查是否为浮点数(不分正负)
	 * @author rainyhao 
	 * @since 2015-3-10 上午10:43:05
	 * @param number 字符形式的字符串
	 * @return
	 */
	public static final boolean isFloat(String number) {
		Matcher m = FLOAT.matcher(number);
		return m.matches();
	}
	
	/**
	 * 检查所输入的字符串是不为email格式
	 * @author rainyhao 
	 * @since 2015-3-23 下午1:18:11
	 * @param email 要检查的字符串
	 * @return
	 */
	public static final boolean isEmail(String email) {
		Matcher m = EMAIL.matcher(email);
		return m.matches();
	}
	
	/**校验传过来的多状态值是否是数字
	 * 状态值用逗号','分隔
	 * @param status_s
	 * @return
	 */
	public static boolean statusIsValid(String status_s){
		String[] all_sta = status_s.split(",");
		for(String num : all_sta){
			if(!isDigit(num)){
				return false;
			}
		}
		return true;
	}
}