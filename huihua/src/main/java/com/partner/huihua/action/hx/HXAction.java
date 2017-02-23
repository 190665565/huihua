package com.partner.huihua.action.hx;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.partner.huihua.service.hx.HxService;
import com.partner.huihua.utils.base.BaseAction;
import com.partner.huihua.utils.exception.UnsatisfiedParamException;
import com.partner.huihua.utils.security.StrMD5;


public class HXAction extends BaseAction{

	@Autowired
	HxService hxservice;
	

	
	/**
	 * 生成核销兑换码并返回
	 * @return
	 */
	public String hxno() {
		String validPrompt = "下发核销兑换码错误！";
		
		String signstr = StrMD5.getInstance().signature(SEED);
		// 检查psid是及签名是否输入
		try {
			checkKeySafe();
		} catch (UnsatisfiedParamException e) {
			return renderFailMsg(e.getMessage());
		}
		if (!sign.equals(signstr)) {
			return renderFailMsg("签名错误");
		}
		//生成兑换码
		String code = hxservice.generateCode();
		
		// 内容签名
		String respSign = StrMD5.getInstance().signature(RESP_SUCCESS, code, SEED);
		JSONObject out = super.createSuccessRespJson(respSign);
		out.put("code", code);
		outText = out;
		return SUCCESS;
	}
		
		/**
		 * 校验核销兑换码
		 * @return
		 */
	public String validatehxno() {
		String validPrompt = "验证&注销兑换码错误！";
		String code = this.getRequest().getParameter("code");
		String signstr = StrMD5.getInstance().signature(code, SEED);
		
		if(hxservice.validateCode(code)){
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS, SEED);
			JSONObject out = super.createSuccessRespJson(respSign);
			outText = out;
		}else{
			outText = super.createFailRespJson("验证失败");
		}
		return SUCCESS;
	}
	
	
}
