package com.partner.huihua.action.trans;

import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.partner.huihua.bean.ProductInfo;
import com.partner.huihua.bean.TransInfo;
import com.partner.huihua.service.product.ProductService;
import com.partner.huihua.service.trans.TransService;
import com.partner.huihua.utils.base.BaseAction;
import com.partner.huihua.utils.common.DateStyle;
import com.partner.huihua.utils.common.DateUtil;
import com.partner.huihua.utils.common.RegExp;
import com.partner.huihua.utils.common.XString;
import com.partner.huihua.utils.exception.HuiHuaException;
import com.partner.huihua.utils.exception.UnsatisfiedParamException;
import com.partner.huihua.utils.security.StrMD5;


public class TransAction extends BaseAction{

	@Autowired
	TransService transservice;
	@Autowired
	ProductService productservice;
	

	
	/**
	 * 兑换记录列表
	 * @return
	 */
	public String list() {
		String validPrompt = "查看兑换记录参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String signstr = StrMD5.getInstance().signature(accountid, page,rows, SEED);
		super.setNeedCount("true");
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
			TransInfo ti = new TransInfo();
			ti.setAccountId(Long.parseLong(accountid));
			ti.setPageInfo(super.getPageInfo());
			
			// 按输入条件查询
			JSONArray lstTrans = transservice.getTransInfo(ti);
			log.info("加载兑换记录成功, 请求参数: " + super.getQueryString());
			String isremain  = "false";
			if(isremain()){
				isremain = "true";
				}
			// 内容签名
			String respSign = StrMD5.getInstance().signature(accountid, RESP_SUCCESS, isremain, XString.encode(lstTrans.toString()), SEED);
			JSONObject out = super.createSuccessRespJson(respSign);
			out.put("accountid", accountid);
			out.put("isremain", isremain);
			out.put("data", lstTrans);
			outText = out;
			return SUCCESS;
		} catch (Exception e) {
			log.error("查询兑换记录出错, 请求参数值: " + super.getQueryString(), e);
			return super.renderFailMsg("查询兑换记录出错: 系统内部错误, 请稍候再试");
		}
	
	}
	
	
	public String transDetail(){
		String validPrompt = "查看兑换详情参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		String transno = this.getRequest().getParameter("transno");
		String signstr = StrMD5.getInstance().signature(transno, SEED);

		// 检查时间范围及分页参数的有效性
		try {
			checkKeySafe();
//			if (!sign.equals(signstr)) {
//				return renderFailMsg("签名错误");
//			}
		} catch (UnsatisfiedParamException e) {
			log.error(validPrompt + e.getMessage());
			return renderFailMsg(e.getMessage());
		}
		
		try {
			TransInfo ti = new TransInfo();
			ti.setTransNo(transno);
			ti = transservice.getTransDetail(ti);
			if(ti==null){
				throw new HuiHuaException("超找订单详情失败，请稍后再试");
			}
			
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS ,ti.getTransNo(),ti.getPname(),ti.getStatus().getValue(),SEED);
			JSONObject out = super.createSuccessRespJson(respSign);
			out.put("transno", ti.getTransNo());
			out.put("pname", ti.getPname());
			out.put("time", DateUtil.DateToString(new Date(ti.getCreatedOn().getTime()),DateStyle.YYYY_MM_DD_HH_MM));
			out.put("pnum", ti.getNumber());
			out.put("amt", ti.getAmt());
			out.put("trans_status", ti.getStatus().getValue());
			out.put("trans_desc", ti.getDesc()==null ? "" : ti.getDesc() );
			outText = out;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("查询兑换详情出错, 请求参数值: " + super.getQueryString(), e);
			return super.renderFailMsg("查询兑换详情出错: 系统内部错误, 请稍候再试");
		}
		
		return super.SUCCESS;
	}
	
	/**
	 * 兑换
	 * @return
	 */
	public String exchange(){
		String validPrompt = "兑换参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String productid = this.getRequest().getParameter("productid");
		String number = this.getRequest().getParameter("number");
		
		String name = this.getRequest().getParameter("name");
		String mobile = this.getRequest().getParameter("mobile");
		String address = this.getRequest().getParameter("address");
		String pc = this.getRequest().getParameter("pc");
		String signstr = StrMD5.getInstance().signature(accountid, productid,number,XString.encode(name),mobile,XString.encode(address),pc, SEED);
		
		// 检查psid是及签名是否输入
		try {
			checkKeySafe();
		} catch (UnsatisfiedParamException e) {
			return renderFailMsg(e.getMessage());
		}
		if (!sign.equals(signstr)) {
			return renderFailMsg("签名错误");
		}
		
		if(accountid==null || "".equals(accountid)){
			return renderFailMsg("accountid 不能为空");
		}
		if(!RegExp.isDigit(accountid)){
			return renderFailMsg("accountid 必须为数字");
		}
		
		if(productid==null || "".equals(productid)){
			return renderFailMsg("productid 不能为空");
		}
		if(!RegExp.isDigit(productid)){
			return renderFailMsg("productid 必须为数字");
		}
		if(number==null || "".equals(number)){
			return renderFailMsg("number 不能为空");
		}
		if(!RegExp.isDigit(number)){
			return renderFailMsg("number 必须为数字");
		}
		// 获取产品类型，如果是type=2（实物），则必须填写name、mobile、address、pc
		ProductInfo pi = new ProductInfo();
		pi.setId(Long.parseLong(productid));
		pi = productservice.getProductObject(pi);
		if(pi==null){
			return renderFailMsg("选择的产品不存在，请检查 ");
		}else{
			if(pi.getType()==2){
				if(name==null || "".equals(name)){
					return renderFailMsg("name 不能为空");
				}
				if(mobile==null || "".equals(mobile)){
					return renderFailMsg("mobile 不能为空");
				}
				if(!RegExp.isDigit(number)){
					return renderFailMsg("number 必须为数字");
				}
				if(address==null || "".equals(address)){
					return renderFailMsg("address 不能为空");
				}
				if(pc==null || "".equals(pc)){
					return renderFailMsg("pc 不能为空");
				}
				if(!RegExp.isDigit(pc)){
					return renderFailMsg("pc 必须为数字");
				}
			}
		}
		// 错误描述
		TransInfo ti = new TransInfo();
		ti.setAccountId(Long.parseLong(accountid));
		ti.setProductId(Long.parseLong(productid));
		ti.setNumber(Integer.parseInt(number));
		ti.setName(name);
		ti.setMobile(mobile);
		ti.setAddress(address);
		ti.setPc((pc==null||"".equals(pc)) ? -1 : Long.parseLong(pc));
		
		
		try{
			transservice.exchange(ti);
			// 内容签名
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS ,SEED);
//			super.outText = super.createSuccessRespJson(respSign);
			JSONObject result = super.createSuccessRespJson(respSign);
			result.put("transno", ti.getTransNo());
			super.outText = result;
		}catch(Exception e){
			super.outText = super.createFailRespJson(e.getMessage());
		}
		return super.SUCCESS;
	}
	
}
