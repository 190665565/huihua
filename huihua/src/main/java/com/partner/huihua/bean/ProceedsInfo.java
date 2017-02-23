package com.partner.huihua.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.partner.huihua.enums.AccountSex;
import com.partner.huihua.enums.AccountStatus;
import com.partner.huihua.enums.ProceedsType;
import com.partner.huihua.utils.base.MapperVO;



/**
 * mybatis 实体, 个人收益表
 * 表: HH_PROCEEDS_INFO
 * @author jesse wang
 * @since 2015-3-9 下午13:53:59
 */
public class ProceedsInfo extends MapperVO{
	//账号
	private Long accountID;
	//收益类型
	private ProceedsType proceesType;
	//今天收益
	private BigDecimal accountAmt;
	//今天收益
	private BigDecimal todayProceeds;
	//总收益
	private BigDecimal allProceeds;
	//上次收益所在小时
	private Long lastTime;
	//当天小时收益次数
	private Integer hourCount;
	//本次收益时间  YYYYMMDDHH
	private Long hour;
	
	public Long getAccountID() {
		return accountID;
	}
	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}
	public ProceedsType getProceesType() {
		return proceesType;
	}
	public void setProceesType(ProceedsType proceesType) {
		this.proceesType = proceesType;
	}
	public BigDecimal getAccountAmt() {
		return accountAmt;
	}
	public void setAccountAmt(BigDecimal accountAmt) {
		this.accountAmt = accountAmt;
	}
	public BigDecimal getTodayProceeds() {
		return todayProceeds;
	}
	public void setTodayProceeds(BigDecimal todayProceeds) {
		this.todayProceeds = todayProceeds;
	}
	public BigDecimal getAllProceeds() {
		return allProceeds;
	}
	public void setAllProceeds(BigDecimal allProceeds) {
		this.allProceeds = allProceeds;
	}
	public Long getLastTime() {
		return lastTime;
	}
	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}
	public Integer getHourCount() {
		return hourCount;
	}
	public void setHourCount(Integer hourCount) {
		this.hourCount = hourCount;
	}
	public Long getHour() {
		return hour;
	}
	public void setHour(Long hour) {
		this.hour = hour;
	}
	

}
