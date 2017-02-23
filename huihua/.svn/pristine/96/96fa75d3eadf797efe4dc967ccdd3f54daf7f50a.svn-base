package com.partner.huihua.utils.common;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * 所有的properties配置文件读取工具类
 * @author rainyhao 
 * @since 2015-3-6 下午2:59:57
 */
public final class Config {

	/**
	 * 网关配置信息 classpath:config/channel.properties
	 */
	private static ResourceBundle channelConfig = ResourceBundle.getBundle("config/client");
	
	/**
	 * 博时基金相关配置 classpath:config/bosera.properties
	 */
//	private static ResourceBundle boseraConfig = ResourceBundle.getBundle("config/bosera");
	
	/**
	 * 获取网关配置项的值
	 * @author rainyhao 
	 * @since 2015-3-6 下午3:44:46
	 * @param key 配置项名称
	 * @return
	 */
	public static final String getChannel(String key) {
		return channelConfig.getString(key);
	}
	
	/**
	 * 获取网关的全部配置项名字
	 * @author rainyhao 
	 * @since 2015-3-21 下午5:30:10
	 * @return
	 */
	public static final Enumeration<String> getChannelKeys() {
		return channelConfig.getKeys();
	}
	


}
