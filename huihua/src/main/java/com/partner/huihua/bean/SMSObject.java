package com.partner.huihua.bean;

public class SMSObject {
	private String uid;
	private String sendto_url;
	private String sms_key;
	private String smsText;
	private String target_mobile;
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSendto_url() {
		return sendto_url;
	}
	public void setSendto_url(String sendto_url) {
		this.sendto_url = sendto_url;
	}
	public String getSms_key() {
		return sms_key;
	}
	public void setSms_key(String sms_key) {
		this.sms_key = sms_key;
	}
	public String getSmsText() {
		return smsText;
	}
	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}
	public String getTarget_mobile() {
		return target_mobile;
	}
	public void setTarget_mobile(String target_mobile) {
		this.target_mobile = target_mobile;
	}
	
	
}
