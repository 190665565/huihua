package com.partner.huihua.utils.base;

import java.util.List;


/**
 * 所有mybatis 数据访问Mapper继承此接口
 * 包含数据访问的基本方法
 * @author rainyhao 
 * @since 2015-3-5 下午5:24:44
 * @param T 所对应的实体对象类型
 */
public interface BaseMapper<T> {
	
	/**
	 * 向数据库写入一行数据
	 * @author rainyhao 
	 * @since 2015-3-5 下午5:28:03
	 * @param entity 要入库的数据载体
	 * @return
	 */
	int insert(T entity);
	
	/**
	 * 按id修改一行数据
	 * @author rainyhao 
	 * @since 2015-3-5 下午5:41:28
	 * @param entity 新数据
	 * @return
	 */
	int update(T entity);
	
	/**
	 * 使用乐观锁更新一行数据
	 * @author rainyhao 
	 * @since 2015-3-5 下午5:53:40
	 * @param entity 新数据
	 * @return
	 */
	int updateByVersion(T entity);
	
	/**
	 * 按id查询
	 * @author rainyhao 
	 * @since 2015-3-5 下午5:52:05
	 * @param id id 主键
	 * @return
	 */
	T selectByPrimaryKey(Long id);
	
	/*------------------------------------------*/
	
	/**
	 * 按指定的查询条件查一条记录
	 * @author rainyhao 
	 * @since 2015-3-11 下午4:20:45
	 * @param entity 查询条件
	 * @return
	 */
	T selectForObject(T entity);
	

	
	/**
	 * 按指定的条查多条记录, 返回多条数据的List
	 * @author rainyhao 
	 * @since 2015-3-11 下午7:10:31
	 * @param entity 查询条件
	 * @return
	 */
	List<T> selectForList(T entity);
	
	/**
	 * 查询指定的列
	 * 按指定的条件查多条记录, 返回多条数据的List
	 * 有时候不需要查全部的列
	 * 而老是在对应的xml文件里写多次select不同列又麻烦
	 * 就在程序中指明要查哪些列
	 * 然后在xml文件里使用 select ${fields}来接收service传入的列名称
	 * 如果查询所有列, 参数entity.fields传null
	 * @author rainyhao 
	 * @since 2015-3-11 下午7:11:07
	 * @param entity
	 * @return
	 */
	List<T> selectFieldsForList(T entity);
	
	/**
	 * 带条件的分页查询
	 * @author rainyhao 
	 * @since 2015-3-5 下午5:56:00
	 * @param entity entit中必须包含PageInfo的对象
	 * @return
	 */
	List<T> selectByPage(T entity);
	
	/**
	 * 查询指定的列
	 * 带条件的分页查询
	 * 有时候不需要查全部的列
	 * 而老是在对应的xml文件里写多次select不同列又麻烦
	 * 就在程序中指明要查哪些列
	 * 然后在xml文件里使用 select ${fields}来接收service传入的列名称
	 * @author rainyhao 
	 * @since 2015-3-11 下午7:17:47
	 * @param fields
	 * @param entity
	 * @return
	 */
	List<T> selectFieldsForPagedList(T entity);
}
