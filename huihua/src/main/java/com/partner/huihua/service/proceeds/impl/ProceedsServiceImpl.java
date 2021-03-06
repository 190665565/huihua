package com.partner.huihua.service.proceeds.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partner.huihua.bean.AccountInfo;
import com.partner.huihua.bean.ProceedsInfo;
import com.partner.huihua.enums.ProceedsType;
import com.partner.huihua.mapper.AccountInfoMapper;
import com.partner.huihua.mapper.ProceedsInfoMapper;
import com.partner.huihua.service.proceeds.ProceedsService;
import com.partner.huihua.utils.common.RegExp;
import com.partner.huihua.utils.common.XString;

@Service
public class ProceedsServiceImpl implements ProceedsService {
	protected Logger log = LogManager.getLogger(this.getClass());
	
	@Autowired
	ProceedsInfoMapper proceedsinfomapper;
	@Autowired
	private AccountInfoMapper accountinfomapper;

	@Override
	public void updateOrinsert(ProceedsInfo proceedsinfo) {
		// TODO Auto-generated method stub

	}
	
	@Transactional(rollbackFor=Throwable.class)
	@Override
	public String add(Long accountID, JSONArray subsidies) {
		// TODO Auto-generated method stub
		int size = subsidies.size();
		String errorDesc = "";
		
		BigDecimal all_proceeds = new BigDecimal(0.00);
		//循环每个元素
		for(int i = 0; i < size; i++){
			String errorPrefix = "第" + i + "个元素";
			JSONObject sub = null;
			try {
				sub = subsidies.getJSONObject(i);
			} catch (Exception e) {
				errorDesc += errorPrefix + "不是json格式";
				continue;
			}
			
			//校验
			if (!sub.containsKey("rowid") || XString.isNullOrEmpty(sub.getString("rowid")) || !RegExp.isDigit(sub.getString("rowid"))) {
				errorDesc += errorPrefix + "不包含rowid属性或者值不是数字,";
			}
			if (!sub.containsKey("type") || XString.isNullOrEmpty(sub.getString("type"))|| !RegExp.isDigit(sub.getString("type"))) {
				errorDesc += errorPrefix + "不包含type属性或者值不是数字, ";
				continue;
			}
			if (!sub.containsKey("amt") || XString.isNullOrEmpty(sub.getString("amt"))|| !RegExp.isFloat(sub.getString("amt"))) {
				errorDesc += errorPrefix + "不包含amt属性或者值不是数字, ";
				continue;
			}
			if (!sub.containsKey("timestamp") || XString.isNullOrEmpty(sub.getString("timestamp"))|| !RegExp.isDigit(sub.getString("timestamp"))) {
				errorDesc += errorPrefix + "不包含timestamp属性或者值不是数字, ";
				continue;
			}
			AccountInfo ai = new AccountInfo();
			ai.setAccountID(accountID);
			ai = this.accountinfomapper.getAcByAc(ai);
			if(ai==null){
				errorDesc += "accountID 无效 ; ";
				return errorDesc;
			}
			
			try {
				ProceedsInfo pi = new ProceedsInfo();
				pi.setAccountID(accountID);
				pi.setProceesType(ProceedsType.forValue(Integer.parseInt(sub.getString("type"))));
				//判断金额不能大于1元钱，下载不能高于5元
				if(!ProceedsType.xz.equals(ProceedsType.forValue(Integer.parseInt(sub.getString("type")))) && Float.parseFloat(sub.getString("amt"))>1.0){
					log.info("warnin proceeds_amt too much:::accountID="+accountID+"  proceed_type="+sub.getString("type") +"  amt="+sub.getString("amt"));
					continue;
				}else if(ProceedsType.xz.equals(ProceedsType.forValue(Integer.parseInt(sub.getString("type")))) && Float.parseFloat(sub.getString("amt"))>5.0){
					log.info("warnin proceeds_amt too much:::accountID="+accountID+"  proceed_type="+sub.getString("type") +"  amt="+sub.getString("amt"));
					continue;
				}
				//超找
				ProceedsInfo tmp = proceedsinfomapper.getObjByObj(pi);
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if(tmp==null){
//					Timestamp now = new Timestamp(System.currentTimeMillis());
					pi.setTodayProceeds(new BigDecimal(Float.parseFloat(sub.getString("amt"))));
					pi.setAllProceeds(new BigDecimal(Float.parseFloat(sub.getString("amt"))));
					pi.setLastTime(Long.parseLong(sub.getString("timestamp")));
					pi.setHourCount(1);
					pi.setVersionOptimizedLock(0);
					pi.setCreatedOn(now);
					pi.setUpdatedOn(now);
					proceedsinfomapper.insert(pi);
				}else{
					long last_hour = tmp.getLastTime();
					long last_day = (long) Math.floor(tmp.getLastTime()/100);
					long curr_hour = Long.parseLong(sub.getString("timestamp"));
					long curr_day = (long) Math.floor(curr_hour/100);
					if(last_hour<curr_hour){
						tmp.setAllProceeds(tmp.getAllProceeds().add(new BigDecimal(Float.parseFloat(sub.getString("amt")))));
						tmp.setLastTime(curr_hour);
						tmp.setHourCount(1);
						tmp.setUpdatedOn(now);
						if(last_day<curr_day){
						tmp.setTodayProceeds(new BigDecimal(Float.parseFloat(sub.getString("amt"))));
						}else{
						tmp.setTodayProceeds(tmp.getTodayProceeds().add(new BigDecimal(Float.parseFloat(sub.getString("amt")))));
						}
						proceedsinfomapper.updateByVersion(tmp);
						
					}else if(last_hour==curr_hour){
						if(tmp.getHourCount()>=3){
							continue;
						}
						tmp.setAllProceeds(tmp.getAllProceeds().add(new BigDecimal(Float.parseFloat(sub.getString("amt")))));
						tmp.setTodayProceeds(tmp.getTodayProceeds().add(new BigDecimal(Float.parseFloat(sub.getString("amt")))));
						tmp.setHourCount(tmp.getHourCount()+1);
						tmp.setUpdatedOn(now);
						proceedsinfomapper.updateByVersion(tmp);
					}else{
						continue;
					}
				}
				all_proceeds = all_proceeds.add(new BigDecimal(Float.parseFloat(sub.getString("amt"))));
			} catch (Exception e) {
				e.printStackTrace();
				errorDesc += errorPrefix + " 内部错误，更新失败;  ";
				continue;
			}
		}
		
		try{
			//更新账户金额
			AccountInfo ai = new AccountInfo();
			ai.setAccountID(accountID);
			ai = this.accountinfomapper.getAcByAc(ai);
			ai.setAccountAmt(ai.getAccountAmt().add(all_proceeds));
			ai.setWithdrawAmt(ai.getWithdrawAmt().add(all_proceeds));
			accountinfomapper.updateByVersion(ai);
		}catch(Exception e){
			e.printStackTrace();
			errorDesc += " 内部错误，更新账户金额失败  ";
		}
		return errorDesc;
	}

	@Override
	public ProceedsInfo getpcds(ProceedsInfo proceedsinfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProceedsInfo getProceedsInfo(ProceedsInfo entity) {
		// TODO Auto-generated method stub
		entity = proceedsinfomapper.getProceedsInfo(entity);
		return entity;
	}

}
