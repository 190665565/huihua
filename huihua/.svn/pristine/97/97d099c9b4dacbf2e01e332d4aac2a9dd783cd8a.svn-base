package com.partner.huihua.test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.partner.huihua.bean.AccountInfo;
import com.partner.huihua.enums.AccountStatus;
import com.partner.huihua.mapper.AccountInfoMapper;
import com.partner.huihua.utils.common.HttpHelper;
import com.partner.huihua.utils.common.XString;
import com.partner.huihua.utils.security.StrMD5;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appContext.xml" })
public class AccountTest {

	public static String host = "101.200.228.71";
//	public static String host = "localhost";
	@Autowired
	private AccountInfoMapper accountinfomapper;
	
	
	
	@Test
	public void test1() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		AccountInfo info = new AccountInfo();
		info.setEquipmentID("eeeeeee");
		info.setStatus(AccountStatus.NORMAL);
		info.setAccountAmt(new BigDecimal(0.00));
		info.setWithdrawAmt(new BigDecimal(0.00));
		info.setOnwayAmt(new BigDecimal(0.00));
		info.setFreezeAmt(new BigDecimal(0.00));
		info.setSalt(XString.random(8));
		info.setVersionOptimizedLock(0);
		info.setCreatedOn(now);
		info.setUpdatedOn(now);
		accountinfomapper.insert(info);
	}
	
	@Test
	public void test_reg() {
		String url = "http://"+host+"/huihua/api/account/reg.action";
		String equipmentid = XString.random(13);
		String sign = StrMD5.getInstance().signature("");
		String respons = HttpHelper
				.getInstance(url)
				.set("equipmentid", equipmentid)
				.set("sign", sign).post();
		System.out.println(respons);
		
	}
	@Test
	public void test_login() {
		String url = "http://"+host+"/huihua/api/account/login.action";
		String mobile = "18810438633";
		String loginpwd = "19891010";
		String sign = StrMD5.getInstance().signature("");
		String respons = HttpHelper
				.getInstance(url)
				.set("mobile", mobile)
				.set("loginpwd", loginpwd)
				.set("sign", sign).post();
		System.out.println(respons);
	}
	
	//下发验证码
	@Test
	public void sendpin() {
		String url = "http://"+host+"/huihua/api/account/sendpin.action";
		String accountid = "1";
		String mobile = "15510750798";
		String type = "1";
		String sign = StrMD5.getInstance().signature("");
		String respons = HttpHelper
				.getInstance(url)
				.set("accountid", accountid)
				.set("mobile", mobile)
				.set("type", type)
				.set("sign", sign).post();
		System.out.println(respons);
		
	}
	//验证验证码
	@Test
	public void checkPIN() {
		String url = "http://"+host+"/huihua/api/account/checkPIN.action";
		String accountid = "1";
		String pin = "111111";
		String type = "1";
		String sign = StrMD5.getInstance().signature("");
		String respons = HttpHelper
				.getInstance(url)
				.set("accountid", accountid)
				.set("pin", pin)
				.set("type", type)
				.set("sign", sign).post();
		System.out.println(respons);
		
	}
	
	//绑定手机
	@Test
	public void bindmobile() {
		String url = "http://"+host+"/huihua/api/account/bindmobile.action";
		String accountid = "1";
		String mobile = "15510750798";
		String pin = "111111";
		String sign = StrMD5.getInstance().signature("");
		String respons = HttpHelper
				.getInstance(url)
				.set("accountid", accountid)
				.set("mobile", mobile)
				.set("pin", pin)
				.set("sign", sign).post();
		System.out.println(respons);
		
	}
	//找回密码
	@Test
	public void pwdback() {
		String url = "http://"+host+"/huihua/api/account/pwdback.action";
		String accountid = "1";
		String newpwd= "111111";
		String sign = StrMD5.getInstance().signature("");
		String respons = HttpHelper
				.getInstance(url)
				.set("accountid", accountid)
				.set("newpwd", newpwd)
				.set("sign", sign).post();
		System.out.println(respons);
	}
	
	//修改密码 by旧密码
	@Test
	public void changepwd() {
		String url = "http://"+host+"/huihua/api/account/changepwd.action";
		String accountid = "1";
		String oldpwd= "111111";
		String newpwd= "222222";
		String sign = StrMD5.getInstance().signature("");
		String respons = HttpHelper
				.getInstance(url)
				.set("accountid", accountid)
				.set("oldpwd", oldpwd)
				.set("newpwd", newpwd)
				.set("sign", sign).post();
		System.out.println(respons);
	}
	//补全账户
		@Test
		public void test_supplement() {
			String url = "http://"+host+"/huihua/api/account/supplement.action";
			String accountid = "1";
			String username = "测试1";
			String birthday = "19850627";
			String sex = "1";
			String address = "立水桥";
			String sign = StrMD5.getInstance().signature("");
			String respons = HttpHelper
					.getInstance(url)
					.set("accountid", accountid)
					.set("username", username)
					.set("birthday", birthday)
					.set("sex", sex)
					.set("address", address)
					.set("sign", sign).post();
			System.out.println(respons);
			
		}
	
	//验证手机号是否注册过
	@Test
	public void isexistmobile() {
		String url = "http://"+host+"/huihua/api/account/isexistmobile.action";
		String mobile = "15510750798";
		String sign = StrMD5.getInstance().signature("");
		String respons = HttpHelper
				.getInstance(url)
				.set("mobile", mobile)
				.set("sign", sign).post();
		System.out.println(respons);
	}
	

	
	
}
