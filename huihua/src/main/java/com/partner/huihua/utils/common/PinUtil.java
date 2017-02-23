package com.partner.huihua.utils.common;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import com.partner.huihua.bean.SMSObject;
import com.partner.huihua.utils.security.MD5Bit32;

public class PinUtil {
	
	public static String uid="";
	public static String sendto_url="";
	public static String sms_key="";
	public static String smsText="";
	public static int pin_len=5;
	//发送验证码最大次数
	public static long max_num=0L;
	//限定发送周期
	public static long cycle=70;
	//判断是否是测试模式
	public static String modle="test";
	private static ResourceBundle channelConfig = ResourceBundle.getBundle("config/sms_config");
	
	
	static {
		modle = channelConfig.getString("modle");
		uid = channelConfig.getString("uid");
		sendto_url = channelConfig.getString("sendto_url");
		sms_key = channelConfig.getString("sms_key");
//		smsText = channelConfig.getString("smsText");
		try {
			smsText = new String(channelConfig.getString("smsText").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		max_num = Long.parseLong(channelConfig.getString("max_num"));
		pin_len = Integer.parseInt(channelConfig.getString("pin_len"));
		cycle = Long.parseLong(channelConfig.getString("cycle"));
//		System.out.println("**********"+smsText);
	}
	
	public static boolean sendPin(SMSObject sms){
		try{
			HttpHelper http  = HttpHelper.getInstance(sms.getSendto_url());
			String result = http.set("Uid", sms.getUid())
			.set("KeyMD5", MD5Bit32.getInstance().md5(sms.getSms_key()))
			.set("smsMob", sms.getTarget_mobile())
			.set("smsText", sms.getSmsText())
			.post();
			if(!"1".equals(result)){
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

}
