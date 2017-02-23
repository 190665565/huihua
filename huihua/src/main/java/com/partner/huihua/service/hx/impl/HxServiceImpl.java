package com.partner.huihua.service.hx.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partner.huihua.bean.ExchangeValidateObj;
import com.partner.huihua.enums.ExchangeNoStatus;
import com.partner.huihua.mapper.ExchangeValidateMapper;
import com.partner.huihua.service.hx.HxService;
import com.partner.huihua.utils.common.ValidateUtil;

/**
 * 核销信息操作
 * @author changjiwang
 *
 */
@Service
public class HxServiceImpl implements HxService{


	@Autowired
	private ExchangeValidateMapper mapper;
	
	@Transactional
	@Override
	public String generateCode() {
		// TODO Auto-generated method stub
		String code = ValidateUtil.getNumValidate();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		ExchangeValidateObj info = new ExchangeValidateObj();
		info.setValidateCode(code);
		info.setStatus(ExchangeNoStatus.VALID);
		info.setVersionOptimizedLock(0);
		info.setCreatedOn(now);
		info.setUpdatedOn(now);
		mapper.insert(info);
		return code;
	}
	
	@Transactional
	@Override
	public boolean validateCode(String code) {
		// TODO Auto-generated method stub
		boolean result = false;
		//库中查找验证
		ExchangeValidateObj info = new ExchangeValidateObj();
		info.setValidateCode(code);
		info.setStatus(ExchangeNoStatus.VALID);
		info = mapper.selectForObject(info);
		if(info==null){
			return result;
		}else{
			//validate ok & cancel
			Timestamp now = new Timestamp(System.currentTimeMillis());
			info.setStatus(ExchangeNoStatus.INVALID);
			info.setUpdatedOn(now);
			mapper.updateByVersion(info);
			result = true;
		}
		return result;
	}
	

	

	
	
}
