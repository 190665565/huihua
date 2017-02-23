package com.partner.huihua.test;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.partner.huihua.utils.common.XString;
import com.partner.huihua.utils.security.StrMD5;

public class SignTest {
	
	/**
	 * 发送验证码
	 */
	public static void testSendPin(){
		String accountid = "19";
		String mobile = "18614045370";
		String type = "1";
		String signstr = StrMD5.getInstance().signature(accountid , mobile , type , "ZBmL#@Tgvi");
		System.out.println(signstr);
		
	}
	/**
	 * 增加收益
	 */
	public static void testAddProceeds(){
		String accountid = "19";
	
		JSONArray jsarry = new JSONArray();
		JSONObject jso = new JSONObject();
		jso.put("rowid", 1);
		jso.put("type", 1);
		jso.put("amt",0.01);
		jso.put("timestamp", "20160127212300");
		jsarry.add(jso);
		String details = jsarry.toString();
		
		String signstr = StrMD5.getInstance().signature(accountid, details, "ZBmL#@Tgvi");
		System.out.println(signstr);
		
	}
	/**
	 * 产品兑换
	 */
	public static void testExchange(){
		String accountid = "19";
		String productid = "13";
		String number = "1";
		String name = "测试";
		String mobile = "18614045370";
		String address = "测试";
		String pc = "";
		String signstr = StrMD5.getInstance().
				signature(accountid, productid,number,XString.encode(name),mobile,XString.encode(address),pc, "ZBmL#@Tgvi");
		System.out.println(signstr);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		testSendPin();
		testAddProceeds();
//		testExchange();
	}

}
