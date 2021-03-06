package com.partner.huihua.service.trans.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partner.huihua.bean.AccountInfo;
import com.partner.huihua.bean.ProductInfo;
import com.partner.huihua.bean.TransInfo;
import com.partner.huihua.enums.TransStatus;
import com.partner.huihua.mapper.AccountInfoMapper;
import com.partner.huihua.mapper.ProductMapper;
import com.partner.huihua.mapper.TransMapper;
import com.partner.huihua.service.trans.TransService;
import com.partner.huihua.utils.common.DateStyle;
import com.partner.huihua.utils.common.DateUtil;
import com.partner.huihua.utils.common.TransUtil;
import com.partner.huihua.utils.exception.HuiHuaException;

@Service
public class TransServiceImpl implements TransService {
	@Autowired
	private AccountInfoMapper accountinfomapper;
	@Autowired
	private ProductMapper productmapper;
	@Autowired
	private TransMapper transmapper;
	
	@Transactional(rollbackFor=Throwable.class)
	@Override
	public void exchange(TransInfo entity) {
		try{
			Timestamp now = new Timestamp(System.currentTimeMillis());
			// TODO Auto-generated method stub
			BigDecimal all_amt; 
			ProductInfo pi = new ProductInfo();
			pi.setId(entity.getProductId());
			pi = productmapper.getProductDetail(pi);
			
			all_amt = pi.getSalt().multiply(new BigDecimal(entity.getNumber()));
			//获取账户信息
			AccountInfo ac = new AccountInfo();
			ac.setAccountID(entity.getAccountId());
			ac = accountinfomapper.getAcByAc(ac);
			if(all_amt.compareTo(ac.getAccountAmt())>0){
				throw new HuiHuaException("兑换总金额超出账户金额");
			}
			//入transinfo表
			entity.setTransNo(TransUtil.getTransNo(entity.getAccountId()));
			entity.setStatus(TransStatus.processing);
			entity.setAmt(all_amt);
			entity.setCreatedOn(now);
			entity.setUpdatedOn(now);
			transmapper.insert(entity);
			//更新accountinfo金额字段
			ac.setAccountAmt(ac.getAccountAmt().subtract(all_amt));
			ac.setWithdrawAmt(all_amt.compareTo(ac.getWithdrawAmt())>0 ? new BigDecimal(0.00):ac.getWithdrawAmt().subtract(all_amt));
			ac.setUpdatedOn(now);
			accountinfomapper.updateByVersion(ac);
		}catch(HuiHuaException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HuiHuaException("内部错误，请稍后再试");
		}
		
		

	}

	@Override
	public JSONArray getTransInfo(TransInfo entity) {
		// TODO Auto-generated method stub
		JSONArray out = new JSONArray();
		List<TransInfo> lstT = transmapper.getTransByPage(entity);
		// 重组json
		for (TransInfo ti : lstT) {
			JSONObject ret = new JSONObject();
			ret.put("transno", ti.getTransNo()); // 兑换流水号
			ret.put("productid", ti.getProductId()); // 商品id
			ret.put("pname", ti.getPname()); // 商品id
			ret.put("big_url", ti.getPictureUrl()); // 大图url
			ret.put("min_url", ti.getMpUrl()); // 小图url
			ret.put("number", ti.getNumber());//数量
			ret.put("amt", ti.getAmt());//金额
			ret.put("time", DateUtil.DateToString(new Date(ti.getCreatedOn().getTime()),DateStyle.YYYY_MM_DD_HH_MM));
			ret.put("status", ti.getStatus()); // 状态
			out.add(ret);
		}
		return out;
	}

	@Override
	public TransInfo getTransDetail(TransInfo entity) {
		// TODO Auto-generated method stub
		return transmapper.getTransDetail(entity);
	}

}
