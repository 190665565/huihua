package com.partner.huihua.task;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.partner.huihua.bean.AccountInfo;
import com.partner.huihua.mapper.AccountInfoMapper;

@Component("taskJob")  
public class TaskJobForDBConnection {
	private Logger logger = Logger.getLogger(TaskJobForDBConnection.class); 
	@Autowired
	private AccountInfoMapper accountinfomapper;
	
	 @Scheduled(cron = "0 0 0/2 * * ?")  
	    public void job1() {  
		 Long i = accountinfomapper.count(new AccountInfo());
		 logger.debug("task doing at "+ new Date());  
	    }  
}
