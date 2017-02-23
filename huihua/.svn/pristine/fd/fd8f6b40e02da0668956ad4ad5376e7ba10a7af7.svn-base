package com.partner.huihua.utils.base;


import java.sql.Timestamp;

import com.partner.huihua.utils.page.PageInfo;



/**
 * 所有mybatis实体类的父类, 包含各表都有的通用字段
 * @author rainyhao 
 * @since 2015-3-5 下午4:29:16
 */
public abstract class MapperVO {
	
	public MapperVO() {
		versionOptimizedLock = 0;
		Timestamp now = new Timestamp(System.currentTimeMillis());
		createdOn = now;
		updatedOn = now;
	}
	
	public MapperVO(long id) {
		this();
		this.id = id;
	}
	
	// 数字类型的主键
	private Long id;
	// 乐观锁
	private Integer versionOptimizedLock;
	// 创建时间
	private Timestamp createdOn;
	// 最后修改时间
	private Timestamp updatedOn;
	
	// Transient 非表字段, 查询时间范围: 开始时间
	private Timestamp startTime;
	
	// Transient 非表字段, 查询时间范围, 结束时间
	private Timestamp endTime;
	
	// Transient 非表字段, 分页参数, 辅助mybatis分页拦截器创建分页sql用
	private PageInfo pageInfo;
	
	// Transient 非表字段, 查指定表的哪些列, 多列用逗号分开
	private String fields;
	//status_s为多状态拼接的字符串
	private String status_s;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getVersionOptimizedLock() {
		return versionOptimizedLock;
	}

	public void setVersionOptimizedLock(Integer versionOptimizedLock) {
		this.versionOptimizedLock = versionOptimizedLock;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getStatus_s() {
		return status_s;
	}

	public void setStatus_s(String status_s) {
		this.status_s = status_s;
	}
	
	
}