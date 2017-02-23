package com.partner.huihua.action.suggest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.partner.huihua.bean.SuggestInfo;
import com.partner.huihua.service.suggest.SuggestService;
import com.partner.huihua.utils.base.BaseAction;
import com.partner.huihua.utils.common.RegExp;
import com.partner.huihua.utils.common.XString;
import com.partner.huihua.utils.security.StrMD5;


public class SuggestAction extends BaseAction{

	@Autowired
	SuggestService suggestservice;
	

	
	/**
	 * 兑换记录列表
	 * @return
	 */
	public String add() {
		String validPrompt = "插入建议参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String content = this.getRequest().getParameter("content");
		super.setNeedCount("true");
		
		if(accountid==null || "".equals(accountid)){
			return renderFailMsg("accountid 不能为空");
		}
		if(content==null || "".equals(content)){
			return renderFailMsg("建议不能为空 ");
		}

		try {
			log.info("正在插入建议记录, 请求参数: " + super.getQueryString());
			//组织参数查询
			SuggestInfo si = new SuggestInfo();
			si.setAccountID(Long.parseLong(accountid));
			if(content.length()>200){
				si.setContent(content.substring(0, 199));
			}else{
				si.setContent(content);
			}
			
			
			
			// 插入
			suggestservice.addSuggest(si);
			log.info("插入建议记录成功, 请求参数: " + super.getQueryString());

			String respSign = StrMD5.getInstance().signature(accountid, RESP_SUCCESS, SEED);
			JSONObject out = super.createSuccessRespJson(respSign);
			outText = out;
			return SUCCESS;
		} catch (Exception e) {
			log.error("插入建议记录出错, 请求参数值: " + super.getQueryString(), e);
			return super.renderFailMsg("插入建议记录出错: 系统内部错误, 请稍候再试");
		}
	
	}
	
	
}
