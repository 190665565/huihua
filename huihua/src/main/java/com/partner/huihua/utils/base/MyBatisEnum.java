package com.partner.huihua.utils.base;
/**
 * ibatis 枚举必须实现的接口
 * 用与com.sohu.focus.util.common.IntEnumHandler配合使用.
 * @author rainyhao
 * @since 2015-3-10 上午11:59:25
 */
public interface MyBatisEnum {
	/**
	 * 获取枚举常量所对应的int值
	 * @author rainyhao 
	 * @since 2015-3-10 下午1:17:31
	 * @return
	 */
	public int getValue();
}
