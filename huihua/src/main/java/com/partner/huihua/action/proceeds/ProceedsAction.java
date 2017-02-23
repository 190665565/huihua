package com.partner.huihua.action.proceeds;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.partner.huihua.bean.ProceedsInfo;
import com.partner.huihua.service.proceeds.ProceedsService;
import com.partner.huihua.utils.base.BaseAction;
import com.partner.huihua.utils.common.RegExp;
import com.partner.huihua.utils.exception.UnsatisfiedParamException;
import com.partner.huihua.utils.security.StrMD5;

public class ProceedsAction extends BaseAction{

	@Autowired
	private ProceedsService ProceedsService;
	
	/**
	 * 增加收益
	 * @return
	 */
	public String add() {
		String validPrompt = "2.1累加参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String details = this.getRequest().getParameter("details");
		String signstr = StrMD5.getInstance().signature(accountid, details, SEED);
		
		if(accountid==null || "".equals(accountid)){
			return renderFailMsg("accountid 不能为空");
		}
		if(!RegExp.isDigit(accountid)){
			return renderFailMsg("accountid 必须为数字");
		}
		// 检查psid是及签名是否输入
		try {
			checkKeySafe();
		} catch (UnsatisfiedParamException e) {
			return renderFailMsg(e.getMessage());
		}
		if (!sign.equals(signstr)) {
			return renderFailMsg("签名错误");
		}
		log.info("Proceeds add action::: accountid="+accountid+" details= "+details);
		// 错误描述
		boolean hasError = false;
		String errorDesc = "details中";
		
		JSONArray  subsidies =  JSONArray.fromObject(details);
		int size = subsidies.size();
		if (0 == size) {
			// 内容签名
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS ,SEED);
			super.outText = super.createSuccessRespJson(respSign);
			return this.SUCCESS;
		}
		String Desc = this.ProceedsService.add(Long.parseLong(accountid), subsidies);
		if(Desc!=null && !"".equals(Desc)){
			super.outText = super.createFailRespJson(Desc);
		}else{
			// 内容签名
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS ,SEED);
			super.outText = super.createSuccessRespJson(respSign);
			return this.SUCCESS;
		}
		
		return this.SUCCESS;
	}

	
	/**
	 * 查看收益
	 * @return
	 */
	public String query() {
		String validPrompt = "2.2查询收益参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String signstr = StrMD5.getInstance().signature(accountid, SEED);
		
		// 检查psid是及签名是否输入
//		try {
//			checkKeySafe();
//		} catch (UnsatisfiedParamException e) {
//			return renderFailMsg(e.getMessage());
//		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		
		if(accountid==null || "".equals(accountid)){
			return renderFailMsg("accountid 不能为空");
		}
		if(!RegExp.isDigit(accountid)){
			return renderFailMsg("accountid 必须为数字");
		}
		
		try{
			ProceedsInfo pi = new ProceedsInfo();
			pi.setAccountID(Long.parseLong(accountid));
			
			pi = ProceedsService.getProceedsInfo(pi);

			if(pi==null){
				super.outText = super.createFailRespJson("无此用户信息");
			}
			JSONObject  out = new JSONObject();
			// 内容签名
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS ,pi.getAccountAmt(),pi.getTodayProceeds(),pi.getAllProceeds(),SEED);
			out = super.createSuccessRespJson(respSign);
			out.put("accountAmt", pi.getAccountAmt());
			out.put("todayProceeds", pi.getTodayProceeds());
			out.put("totalProceeds", pi.getAllProceeds());
			super.outText = out;
		}catch(Exception e){
			super.outText = super.createFailRespJson("内部错误");
		}
		
		return this.SUCCESS;
	}
	
}
