package test.validate;

import java.sql.Timestamp;

import com.partner.huihua.utils.common.DateStyle;
import com.partner.huihua.utils.common.DateUtil;
import com.partner.huihua.utils.common.HttpHelper;
import com.partner.huihua.utils.common.XString;
import com.partner.huihua.utils.security.MD5Bit32;
import com.partner.huihua.utils.security.StrMD5;

public class TestValidateCode {
	
	public static void send_validate(){
		String url = "http://utf8.sms.webchinese.cn/";
		HttpHelper http  = HttpHelper.getInstance(url);
		String result = http.set("Uid", "hui_hua")
		.set("Key", "e9c45e81d02484a077e1")
		.set("KeyMD5", MD5Bit32.getInstance().md5("e9c45e81d02484a077e1"))
		.set("smsMob", "15510750798")
		.set("smsText", "您的验证码为：111111")
		.post();
		System.out.println("*****************"+result);
		
	}
	
	public static void testSendPin(){
//		String host = "101.200.228.71";
		String host = "localhost";
		String url = "http://"+host+"/huihua/api/account/sendpin.action";
		String accountid= "21";
		String mobile= "18614045370";
		String type= "1";
		String signstr = StrMD5.getInstance().signature(accountid , mobile , type , "ZBmL#@Tgvi");
		System.out.println(signstr);
		HttpHelper http  = HttpHelper.getInstance(url);
		String result = http.set("accountid", accountid)
						.set("mobile", mobile)
						.set("type", type)
						.set("sign", signstr).post();
		
		System.out.println("*****************"+result);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		send_validate();
		testSendPin();
//		System.out.println(DateUtil.TimestampToString(new Timestamp(System.currentTimeMillis()) , DateStyle.YYYY_MM_DD));

	}

}
