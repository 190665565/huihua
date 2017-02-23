package com.partner.huihua.action.product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.partner.huihua.bean.ProductInfo;
import com.partner.huihua.service.product.ProductService;
import com.partner.huihua.utils.base.BaseAction;
import com.partner.huihua.utils.common.RegExp;
import com.partner.huihua.utils.common.XString;
import com.partner.huihua.utils.exception.UnsatisfiedParamException;
import com.partner.huihua.utils.security.StrMD5;

public class ProductAction extends BaseAction{

	@Autowired
	private ProductService productservice;
	

	
	/**
	 * 产品列表
	 * @return
	 */
	public String list() {
		String validPrompt = "查询产品列表参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String type = this.getRequest().getParameter("type");
		String signstr = StrMD5.getInstance().signature(accountid, type, SEED);
		super.setNeedCount("true");
		
		if(accountid==null || "".equals(accountid)){
			return renderFailMsg("accountid 不能为空");
		}
		if(!RegExp.isDigit(accountid)){
			return renderFailMsg("accountid 必须为数字");
		}
		if((type!=null && !"".endsWith(type)) && !RegExp.isDigit(accountid)){
			return renderFailMsg("type 必须为数字");
		}
		
		// 检查时间范围及分页参数的有效性
		try {
//			checkKeySafe();
			checkParamSafe();
		} catch (UnsatisfiedParamException e) {
			log.error(validPrompt + e.getMessage());
			return renderFailMsg(e.getMessage());
		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		
		try {
			log.info("正在查询产品记录, 请求参数: " + super.getQueryString());
			//组织参数查询
			ProductInfo pi = new ProductInfo();
			if(type!=null && !"".endsWith(type)){
				pi.setType(Integer.parseInt(type));
			}
			pi.setPageInfo(super.getPageInfo());
			
			// 按输入条件查询
			JSONArray lstProduct = productservice.getProductInfo(pi);
			log.info("加载产品记录成功, 请求参数: " + super.getQueryString());
			String isremain  = "false";
			if(isremain()){
				isremain = "true";
				}
			// 内容签名
			String respSign = StrMD5.getInstance().signature(accountid, RESP_SUCCESS, isremain, XString.encode(lstProduct.toString()), SEED);
			JSONObject out = super.createSuccessRespJson(respSign);
			out.put("isremain", isremain);
			out.put("data", lstProduct);
			outText = out;
			return SUCCESS;
		} catch (Exception e) {
			log.error("查询产品记录出错, 请求参数值: " + super.getQueryString(), e);
			return super.renderFailMsg("查询产品记录出错: 系统内部错误, 请稍候再试");
		}
	}
	
	
	/**
	 * 产品信息明细
	 * @return
	 */
	public String getDetail() {
		String validPrompt = "查询产品明细参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";

		String productid = this.getRequest().getParameter("productid");
		String signstr = StrMD5.getInstance().signature(productid, SEED);

		if(productid==null || "".equals(productid)){
			return renderFailMsg("productid 不能为空");
		}
		if(!RegExp.isDigit(productid)){
			return renderFailMsg("productid 必须为数字");
		}
		
		// 检查时间范围及分页参数的有效性
		try {
//			checkKeySafe();
			checkParamSafe();
		} catch (UnsatisfiedParamException e) {
			log.error(validPrompt + e.getMessage());
			return renderFailMsg(e.getMessage());
		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		
		try {
			log.info("正在查询产品明细, 请求参数: " + super.getQueryString());
			//组织参数查询
			ProductInfo pi = new ProductInfo();
			pi.setId(Long.parseLong(productid));
			// 按输入条件查询
			JSONObject Product = productservice.getProductDetail(pi);
			log.info("加载产品详情成功, 请求参数: " + super.getQueryString());

			// 内容签名
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS, XString.encode(Product.toString()), SEED);
			JSONObject out = super.createSuccessRespJson(respSign);
			out.put("data", Product);
			outText = out;
			return SUCCESS;
		} catch (Exception e) {
			log.error("查询产品详情出错, 请求参数值: " + super.getQueryString(), e);
			return super.renderFailMsg("查询产品详情出错: 系统内部错误, 请稍候再试");
		}
	}
	
}
