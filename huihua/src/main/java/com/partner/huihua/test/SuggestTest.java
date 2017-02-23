package com.partner.huihua.test;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.partner.huihua.bean.NoticeInfo;
import com.partner.huihua.bean.SuggestInfo;
import com.partner.huihua.mapper.SuggestMapper;
import com.partner.huihua.utils.common.HttpHelper;
import com.partner.huihua.utils.page.PageInfo;
import com.partner.huihua.utils.security.StrMD5;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appContext.xml" })
public class SuggestTest {

	public static String host = "101.200.228.71";
//	public static String host = "localhost";
	@Autowired
	private SuggestMapper suggestmapper;

	
	

	
	@Test
	public void add() {
		String url = "http://"+host+"/huihua/api/suggest/suggestadd.action";
		String accountid = "1";
		String content = "让我给什么意见呢";
		
		String sign = StrMD5.getInstance().signature("");
		
		String respons = HttpHelper
				.getInstance(url)
				.set("accountid", accountid)
				.set("content", content)
				.set("sign", sign)
				.post();
		System.out.println(respons);
		
	}
//	
//	@Test
//	public void query() {
//		
//		
//	}

	@Test
	public void dao_add() {
		Timestamp now  = new Timestamp(System.currentTimeMillis());
		SuggestInfo si = new SuggestInfo();
		si.setAccountID(1L);
		si.setContent("测试 测试测试");
		si.setVersionOptimizedLock(0);
		si.setCreatedOn(now);
		si.setUpdatedOn(now);
		
		
		suggestmapper.insert(si);
		
		
		
		
	}
	

	
	
}
