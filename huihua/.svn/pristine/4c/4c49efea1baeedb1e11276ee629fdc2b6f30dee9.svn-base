package com.partner.huihua.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partner.huihua.bean.AccountInfo;
import com.partner.huihua.utils.base.BaseMapper;




/**
 * 用户信息操作接口
 * @author jesse wang
 * since 2015-3-10 下午18:18:18
 */
public interface AccountInfoMapper extends BaseMapper<AccountInfo> {
	/**
	 * 获取用户 总数
	 * @author jesse wang
	 * since 2015-6-10 下午18:18:18
	 * @return
	 */
	Long count(AccountInfo entity);
	/**
	 * 获取用户信息带投资总额
	 * @author jesse wang
	 * since 2015-3-10 下午18:18:18
	 * @param entity entit中必须包含PageInfo的对象
	 * @return
	 */
	List<AccountInfo> queryAccountInfoByPage(AccountInfo entity);


	/**
	 * 根据username查询account是否正常状态
	 * @author dkp
	 * since 2015-3-22 下午18:18:18
	 * @param username
	 * @return
	 */
	AccountInfo getAcIsNorm(@Param("username") String username);
	/**
	 * 查找用户信息
	 * @param entity
	 * @return
	 */
	AccountInfo getAcByAc(AccountInfo entity);
	

}
