package com.partner.huihua.service.suggest.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partner.huihua.bean.SuggestInfo;
import com.partner.huihua.mapper.SuggestMapper;
import com.partner.huihua.service.suggest.SuggestService;
import com.partner.huihua.utils.exception.HuiHuaException;

@Service
public class SuggestServiceImpl implements SuggestService {

	@Autowired
	SuggestMapper suggestmapper;
	
	@Override
	public void addSuggest(SuggestInfo entity) {
		// TODO Auto-generated method stub
		Timestamp now = new Timestamp(System.currentTimeMillis());
		try{
			entity.setVersionOptimizedLock(0);
			entity.setCreatedOn(now);
			entity.setUpdatedOn(now);
			suggestmapper.insert(entity);
		}catch(Exception e){
			throw new HuiHuaException("插入建议失败，内如错误");
		}
	}
	

}
