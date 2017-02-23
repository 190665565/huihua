package com.partner.huihua.test;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.partner.huihua.bean.NoticeInfo;
import com.partner.huihua.mapper.NoticeMapper;
import com.partner.huihua.utils.common.HttpHelper;
import com.partner.huihua.utils.page.PageInfo;
import com.partner.huihua.utils.security.StrMD5;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appContext.xml" })
public class NoticeTest {

//	public static String host = "101.200.228.71";
	public static String host = "localhost";
	@Autowired
	private NoticeMapper noticemapper;

	
	

//	
//	@Test
//	public void add() {
//		
//		
//	}
//	
	@Test
	public void list() {
		String url = "http://"+host+"/huihua/api/notice/noticelist.action";
		String accountid = "1";
		
		String sign = StrMD5.getInstance().signature("");
		
		String respons = HttpHelper
				.getInstance(url)
				.set("accountid", accountid)
				.set("page", 1)
				.set("rows", 1)
				.set("sign", sign).post();
		System.out.println(respons);
		
		
	}

	@Test
	public void dao_add() {
		Timestamp now  = new Timestamp(System.currentTimeMillis());
		NoticeInfo ni = new NoticeInfo();
		ni.setNoticeName("公告 20150911");
		ni.setPageUrl("baidu.com");
		ni.setVersionOptimizedLock(0);
		ni.setCreatedOn(now);
		
		noticemapper.insert(ni);
		
		
		
		
	}
	
	@Test
	public void dao_query() {
		PageInfo pi = new PageInfo();
		
		NoticeInfo ni = new NoticeInfo();
		ni.setPageInfo(pi);
		List<NoticeInfo> list = noticemapper.selectByPage(ni);
		System.out.println(list.size());
		
		
	}
	

	
	
}
