package com.partner.huihua.utils.common;
/*
 * Title HttpHelper.java
 * 提供发送http请求的通用方法 
 * 用法示例:
 * HttpHelper helper = HttpHelper.getInstance("http://somedomain.com/somurl");
 * helper.set("param1", "value1").set("param2", "value2"); // 链式添加请求参数
 * String getRes = helper.get(); // 发送get请求, 默认用utf-8将请求参数值转码
 * String postRes = helper.post(); // 发送post请求, 默认用utf-8将请求参数值转码
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.partner.huihua.utils.exception.HuiHuaException;

/**
 * 发送rest请求的工具类
 * @author rainyhao 
 * @since 2015-3-3 下午3:11:21
 */
public final class HttpHelper {
	
	private Logger log = Logger.getLogger(HttpHelper.class);
	
	/**
	 * http请求参数
	 */
	private Map<String, Object> params;
	
	/**
	 * http请求地址
	 */
	private String url;
	
	/**
	 * 不允许调构造函数
	 */
	private HttpHelper() {
	}
	
	/**
	 * 以http请求地址创建实例
	 * @author rainyhao 
	 * @since 2015-3-3 下午3:15:20
	 * @param url http 完整url
	 * @return
	 */
	public static final HttpHelper getInstance(String url) {
		HttpHelper rest = new HttpHelper();
		rest.params = new HashMap<String, Object>();
		rest.url = url;
		return rest;
	}
	
	/**
	 * 添加请求参数, 值为任意类型, 最后都会转换为字符串
	 * 如果值为空, 自动用空字符串替换
	 * @author rainyhao 
	 * @since 2015-3-3 下午3:17:10
	 * @param key 参数名
	 * @param value 参数值
	 * @return
	 */
	public final HttpHelper set(String key, Object value) {
		if (null == value) {
			value = "";
		}
		params.put(key, value);
		return this;
	}
	
	/**
	 * 移除请求参数
	 * @author rainyhao 
	 * @since 2015-3-3 下午3:18:32
	 * @param key 请求参数名
	 * @return
	 */
	public final HttpHelper remove(String key) {
		params.remove(key);
		return this;
	}
	
	/**
	 * 获取请求参数的查询字符串
	 * @author rainyhao 
	 * @since 2015-3-3 下午3:31:19
	 * @return
	 */
	public final String getQueryString() {
		return getQueryString(null);
	}
	
	/**
	 * 获取请求参数的查询字符串, 以指定的字符集将请求参数值做转码
	 * @author rainyhao 
	 * @since 2015-3-3 下午3:31:41
	 * @param charset 字符集名称
	 * @return
	 */
	public final String getQueryString(String charset) {
		StringBuffer query = new StringBuffer();
		Set<String> keys = params.keySet();
		try {
			for (String key : keys) {
				Object value = params.get(key);
				query.append(key).append("=").append(null != charset && !"".equals(charset) ? URLEncoder.encode("" + value, charset) : value).append("&");
			}
			if (query.length() > 0) {
				query = new StringBuffer(query.subSequence(0, query.length() - 1));
			}
		} catch (UnsupportedEncodingException ignore) {
			return "未知字符集:" + charset;
		}
		return query.toString();
	}
	
	/**
	 * 获取get形式的完整url
	 * 参数不做url转码
	 * @author rainyhao 
	 * @since 2015-3-12 上午11:15:48
	 * @return
	 */
	public final String toUrl() {
		String query = getQueryString();
		return url + ("".equals(query) ? "" : "?" + query);
	}
	
	/**
	 * 获取get形式的完整url
	 * 用指定的字符集请求参数做url转码
	 * @author rainyhao 
	 * @since 2015-3-12 上午11:17:58
	 * @param charset 字符集名称
	 * @return
	 */
	public final String toUrl(String charset) {
		String query = getQueryString(charset);
		return url + ("".equals(query) ? "" : "?" + query);
	}
	
	/**
	 * 发送http get请求, 用UTF-8将请求参数值转码
	 * @author rainyhao 
	 * @since 2015-3-3 下午5:12:22
	 * @return
	 */
	public final String get() throws HuiHuaException {
		return get("UTF-8");
	}
	
	/**
	 * 发送http get请求, 用指定的字符集将请求参数转码
	 * @author rainyhao 
	 * @since 2015-3-3 下午3:55:48
	 * @param charset 转码请求参数用的字符集名称
	 * @return
	 */
	public final String get(String charset) throws HuiHuaException {
		// 接口响应内容
		String result = "";
		// 所有参数组成的查询字符串
		String query = getQueryString(charset);
		// 完整的get请求
		String fullUrl = url + ("".equals(query) ? "" : "?" + query);
		CloseableHttpClient client = HttpClients.createDefault();
		// 准备发送get请求
		HttpGet get = new HttpGet(fullUrl);
		try {
			log.info("正在发送HTTP GET请求: " + fullUrl + ", 原始参数: " + getQueryString());
			// 发送
			result = client.execute(get, new BasicResponseHandler());
		} catch (Exception e) {
			String errorMsg = "发送HTTP GET请求失败: " + fullUrl;
			throw new HuiHuaException(errorMsg, e);
		} finally {
			try { client.close(); } catch (IOException ignore) { }
		}
		return result;
	}
	
	/**
	 * 发送http post请求, 用UTF-8字符集将请求参数转码s
	 * @author rainyhao 
	 * @since 2015-3-3 下午5:13:34
	 * @return
	 */
	public final String post() throws HuiHuaException {
		return post("UTF-8");
	}
	
	/**
	 * 发送http post请求, 用指定的字符集将请求参数转码
	 * <p>
	 *  简单的添加了数组类型的支持
	 *  </p>
	 * @author rainyhao 
	 * @since 2015-3-3 下午5:14:30
	 * @param charset 转码请求参数用的字符集名称
	 * @return
	 */
	public final String post(String charset) throws HuiHuaException {
		// 接口的响应内容
		String result = "";
		CloseableHttpClient client = HttpClients.createDefault();
		// 准备发送post请求
		HttpPost post = new HttpPost(url);
		// post 请求参数
		List<NameValuePair> nvs = new ArrayList<NameValuePair>();
		Set<String> keys = params.keySet();
		for (String key : keys) {
			Object obj = params.get(key);
			if(obj.getClass().isArray()) {
				Object[] objs = (Object[])params.get(key);
				for (Object ob : objs) {
					nvs.add(new BasicNameValuePair(key, "" + ob));
				}
			}else {
				nvs.add(new BasicNameValuePair(key, "" + params.get(key)));
			}
		}
		try {
			log.info("正在发送HTTP POST请求: " + url + "?" + getQueryString());
			// 添加post请求内容
			post.setEntity(new UrlEncodedFormEntity(nvs, charset));
			// 发送
			result = client.execute(post, new BasicResponseHandler());
		} catch (UnsupportedEncodingException ignore) {
			throw new HuiHuaException("未知字符集: " + charset);
		} catch (Exception e) {
			String errorMsg = "发送HTTP POST请求失败: " + url + "?" + getQueryString();
			throw new HuiHuaException(errorMsg, e);
		} finally {
			try { client.close(); } catch (IOException ignore) { }
		}
		return result;
	}
}
