package com.partner.huihua.action.test;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{
	protected Object outText;
	public String test() {
		JSONObject out = new JSONObject();
		out.put("test_desc", "hello huihua");
		System.out.println(out.toString());
		outText = out;
		return this.SUCCESS;
	}
	public Object getOutText() {
		return outText;
	}
	public void setOutText(Object outText) {
		this.outText = outText;
	}
	
	

}
