package com.partner.huihua.service.account;

import com.partner.huihua.bean.AccountInfo;
import com.partner.huihua.bean.PinObject;
import com.partner.huihua.mapper.PinVerPojo;
import com.partner.huihua.utils.exception.HuiHuaException;

public interface AccountService {
	/**
	 *  用户注册
	 * @throws HuiHuaException
	 */
	public AccountInfo reg(AccountInfo accountinfo) throws HuiHuaException;
	
	/**
	 * 用户登录
	 * @param accountinfo
	 * @return
	 * @throws HuiHuaException
	 */
	public AccountInfo login(AccountInfo accountinfo) throws HuiHuaException;
	
	/**
	 * 发送验证码
	 * @param accountinfo
	 * @param type
	 * @throws HuiHuaException
	 */
	public void sendPIN(AccountInfo accountinfo,int type) throws HuiHuaException;
	/**
	 * 验证下发的验证码
	 * @return
	 * @throws HuiHuaException
	 */
	public PinObject checkPIN(PinObject po) throws HuiHuaException;
	
	
	/**
	 * 修改手机
	 * @param accountinfo
	 * @param pin
	 * @return
	 * @throws HuiHuaException
	 */
	public boolean changeMobile(AccountInfo accountinfo,long pin) throws HuiHuaException;
	/**
	 * 修改密码
	 * @param accountinfo
	 * @return
	 * @throws HuiHuaException
	 */
	public boolean changePwd(AccountInfo accountinfo) throws HuiHuaException;
	/**
	 * 根据参数获取用户信息
	 * @param accountinfo
	 * @return
	 * @throws HuiHuaException
	 */
	public AccountInfo getAccountInfo(AccountInfo accountinfo) throws HuiHuaException;
	/**
	 * 绑定手机
	 * @param accountinfo
	 * @param pinobj
	 * @return
	 * @throws HuiHuaException
	 */
	public void bindMobile(AccountInfo accountinfo , PinObject pinobj) throws HuiHuaException;
	/**
	 * 修改手机
	 * @param accountinfo
	 * @param pinobj
	 * @return
	 * @throws HuiHuaException
	 */
	public void changeMobile(AccountInfo accountinfo) throws HuiHuaException;

	/**
	 * 找回密码（通过手机）
	 * @param accountinfo
	 * @param pinobj
	 * @return
	 * @throws HuiHuaException
	 */
	public void pwdBack(AccountInfo accountinfo) throws HuiHuaException;
	
	/**
	 * 修改密码（通过旧密码）
	 * @param accountinfo
	 * @param pinobj
	 * @return
	 * @throws HuiHuaException
	 */
	public void changePwdByPwd(AccountInfo accountinfo , String newpwd) throws HuiHuaException;
	
	/**
	 * 补全用户信息
	 * @param accountinfo
	 * @return
	 * @throws HuiHuaException
	 */
	public void supplement(AccountInfo accountinfo) throws HuiHuaException;
	/**
	 * 手机号是否已经被使用
	 * @param accountinfo
	 * @return
	 * @throws HuiHuaException
	 */
	public boolean mobileIsExist(Long mobile) throws HuiHuaException;
}
