package com.partner.huihua.service.notice.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partner.huihua.bean.NoticeInfo;
import com.partner.huihua.bean.TransInfo;
import com.partner.huihua.mapper.NoticeMapper;
import com.partner.huihua.service.notice.NoticeService;
import com.partner.huihua.utils.common.DateStyle;
import com.partner.huihua.utils.common.DateUtil;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeMapper noticemappper;
	
	@Override
	public JSONArray getNoticesInfo(NoticeInfo entity) {
		// TODO Auto-generated method stub
		JSONArray out = new JSONArray();
		List<NoticeInfo> lstT = noticemappper.selectByPage(entity);
		// 重组json
		for (NoticeInfo ti : lstT) {
			JSONObject ret = new JSONObject();
			ret.put("id", ti.getId()); // id
			ret.put("name", ti.getNoticeName()); // name
			ret.put("url", ti.getPageUrl());//url
			out.add(ret);
		}
		return out;
	}

	
}
