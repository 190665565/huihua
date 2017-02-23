package com.partner.huihua.bean;

import java.math.BigDecimal;

import com.partner.huihua.enums.ProductStatus;
import com.partner.huihua.utils.base.MapperVO;



/**
 * mybatis 实体,  产品表
 * 表: HH_PRODUCT_INFO
 * @author jesse wang
 * @since 2015-3-9 下午13:53:59
 */
public class ProductInfo extends MapperVO{
	//账号
	private String name;
	//成本价
	private BigDecimal cost;
	//售价
	private BigDecimal salt;
	//原价
	private BigDecimal primecost;
	//描述
	private String desc;
	//使用说明
	private String instructions;
	//产品url
	private String pictureUrl;
	//小图片url
	private String mpUrl;
	//产品类型
	private Integer type;
	//产品状态
	private ProductStatus status;
	//剩余数量
	private Long remain;
	//已兑换数量
	private Long exchange;
	//是否展示
	private int is_show=1;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public BigDecimal getSalt() {
		return salt;
	}
	public void setSalt(BigDecimal salt) {
		this.salt = salt;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public ProductStatus getStatus() {
		return status;
	}
	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	public Long getRemain() {
		return remain;
	}
	public void setRemain(Long remain) {
		this.remain = remain;
	}
	public Long getExchange() {
		return exchange;
	}
	public void setExchange(Long exchange) {
		this.exchange = exchange;
	}
	public BigDecimal getPrimecost() {
		return primecost;
	}
	public void setPrimecost(BigDecimal primecost) {
		this.primecost = primecost;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getMpUrl() {
		return mpUrl;
	}
	public void setMpUrl(String mpUrl) {
		this.mpUrl = mpUrl;
	}
	public int getIs_show() {
		return is_show;
	}
	public void setIs_show(int is_show) {
		this.is_show = is_show;
	}
	
	
	

}
