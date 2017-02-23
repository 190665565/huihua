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
public class ProceedsTest {

	public static String host = "101.200.228.71";
//	public static String host = "localhost";

	
	

	
	@Test
	public void add() {
		String url = "http://"+host+"/huihua/api/proceeds/add.action";
		String accountid = "12";
		String details_begin = "[";
		String sub1 = "{rowid:1, type:1, amt:1.00,timestamp:20160201314},";
		String sub2 = "{rowid:2, type:2, amt:2.00,timestamp:20160201314}";
		String details_end = "]";
		String details = details_begin+sub1+sub2+details_end;
		
		String sign = StrMD5.getInstance().signature(accountid, details, "ZBmL#@Tgvi");
		
		String respons = HttpHelper
				.getInstance(url)
				.set("accountid", accountid)
				.set("details", details)
				.set("sign", sign).post();
		System.out.println(respons);
		
	}
	
	@Test
	public void query() {
		String url = "http://"+host+"/huihua/api/proceeds/query.action";
		String accountid = "38";
		
		String sign = StrMD5.getInstance().signature("");
		
		String respons = HttpHelper
				.getInstance(url)
				.set("accountid", accountid)
				.set("sign", sign).post();
		System.out.println(respons);
		
	}

	

	
	
}
