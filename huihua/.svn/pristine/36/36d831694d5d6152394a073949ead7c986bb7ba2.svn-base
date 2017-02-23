package com.partner.huihua.action.notice;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.partner.huihua.bean.NoticeInfo;
import com.partner.huihua.service.notice.NoticeService;
import com.partner.huihua.service.product.ProductService;
import com.partner.huihua.utils.base.BaseAction;
import com.partner.huihua.utils.common.XString;
import com.partner.huihua.utils.security.StrMD5;


public class NoticeAction extends BaseAction{

	@Autowired
	NoticeService noticeservice;
	

	
	/**
	 * 兑换记录列表
	 * @return
	 */
	public String list() {
		String validPrompt = "查看公告记录参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String signstr = StrMD5.getInstance().signature(accountid, page,rows, SEED);
		super.setNeedCount("true");

		try {
			log.info("正在查询公告记录, 请求参数: " + super.getQueryString());
			//组织参数查询
			NoticeInfo ni = new NoticeInfo();
			ni.setPageInfo(super.getPageInfo());
			
			// 按输入条件查询
			JSONArray lstTrans = noticeservice.getNoticesInfo(ni);
			log.info("加载公告记录成功, 请求参数: " + super.getQueryString());
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
			log.error("查询公告记录出错, 请求参数值: " + super.getQueryString(), e);
			return super.renderFailMsg("查询公告记录出错: 系统内部错误, 请稍候再试");
		}
	
	}
	
	
}
