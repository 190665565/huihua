package com.partner.huihua.action.account;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.partner.huihua.bean.AccountInfo;
import com.partner.huihua.bean.PinObject;
import com.partner.huihua.enums.AccountSex;
import com.partner.huihua.enums.VerType;
import com.partner.huihua.service.account.AccountService;
import com.partner.huihua.utils.base.BaseAction;
import com.partner.huihua.utils.common.RegExp;
import com.partner.huihua.utils.exception.HuiHuaException;
import com.partner.huihua.utils.exception.UnsatisfiedParamException;
import com.partner.huihua.utils.security.StrMD5;

public class AccountAction extends BaseAction{

	@Autowired
	private AccountService accountservice;
	
	public String count() {
//		long count = accountinfomapper.count();
//		JSONObject out = new JSONObject();
//		out.put("count", count);
//		System.out.println(out.toString());
//		outText  = out;
		return this.SUCCESS;
	}
	/**
	 * 注册
	 * @return
	 */
	public String reg() {
		String validPrompt = "1.1注册参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String equipmentid = this.getRequest().getParameter("equipmentid");
		String signstr = StrMD5.getInstance().signature(equipmentid , SEED);
		
		if(equipmentid==null || "".equals(equipmentid)){
			return renderFailMsg("设备id 不能为空");
		}
		// 检查psid是及签名是否输入
//		try {
//			checkKeySafe();
//		} catch (UnsatisfiedParamException e) {
//			return renderFailMsg(e.getMessage());
//		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		JSONObject out = new JSONObject();

		//注册入库
		AccountInfo acc = new AccountInfo();
		acc.setEquipmentID(equipmentid);
		acc = accountservice.reg(acc);
		if(acc!=null){
			// 内容签名
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS, acc.getAccountID() ,SEED);
			out = super.createSuccessRespJson(respSign);
			out.put("accountid", acc.getAccountID());
		}else{
			out = super.createFailRespJson("注册失败");
		}
		
		outText = out;
		return this.SUCCESS;
	}
	/**
	 * 登录
	 * @return
	 */
	public String login(){
		String validPrompt = "1.2登录参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String mobile = this.getRequest().getParameter("mobile");
		String login_pwd = this.getRequest().getParameter("loginpwd");
		String signstr = StrMD5.getInstance().signature(mobile , login_pwd , SEED);
		
		if(mobile==null || "".equals(mobile)){
			return renderFailMsg("手机号 不能为空");
		}
		if(!RegExp.isMobile(mobile)){
			return renderFailMsg("输入的手机号码错误，请检查");
		}
		if(login_pwd==null || "".equals(login_pwd)){
			return renderFailMsg("登录密码不能为空");
		}
		// 检查psid是及签名是否输入
//		try {
//			checkKeySafe();
//		} catch (UnsatisfiedParamException e) {
//			return renderFailMsg(e.getMessage());
//		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		
		AccountInfo ac = new AccountInfo();
		ac.setMobile(Long.parseLong(mobile));
		ac = accountservice.getAccountInfo(ac);
		if(ac==null){
			return renderFailMsg("手机号错误");
		}
		else{
			String pwd = ac.getLoginPwd();
			String salt = ac.getSalt();
			if(!StrMD5.getInstance().encrypt(login_pwd, salt).equals(pwd)){
				return renderFailMsg("密码错误，登录失败");
			}
		}
		JSONObject out = new JSONObject();
		// 内容签名
		String respSign = StrMD5.getInstance().signature(RESP_SUCCESS, ac.getAccountID(),ac.getUserName() ,ac.getBirthday(),ac.getSex()==null ? "":ac.getSex().getValue()
				,ac.getMobile(),ac.getAddress(),ac.getStatus()==null  ? "" : ac.getStatus().getValue(), ac.getAccountAmt()
				,ac.getWithdrawAmt(),ac.getOnwayAmt(),ac.getFreezeAmt(),SEED);
		out = super.createSuccessRespJson(respSign);
		out.put("accountid", ac.getAccountID());
		out.put("username", ac.getUserName());
		out.put("birthday", ac.getBirthday());
		out.put("sex", ac.getSex()==null ? "":ac.getSex().getValue());
		out.put("mobile", ac.getMobile());
		out.put("address", ac.getAddress());
		out.put("accountstatus", ac.getStatus()==null  ? "" : ac.getStatus().getValue());
		out.put("accountamt", ac.getAccountAmt());
		out.put("withdrawamt", ac.getWithdrawAmt());
		out.put("onwayamt", ac.getOnwayAmt());
		out.put("freezeamt", ac.getFreezeAmt());
		outText = out;
		return super.SUCCESS;
	}
	/**
	 * 下发验证码
	 * @return
	 */
	public String sendpin(){
		String validPrompt = "1.3下发验证码参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String mobile = this.getRequest().getParameter("mobile");
		String type = this.getRequest().getParameter("type");
		String signstr = StrMD5.getInstance().signature(accountid , mobile , type , SEED);
		if(mobile==null || "".equals(mobile)){
			return renderFailMsg("手机号 不能为空");
		}
		if(!RegExp.isMobile(mobile)){
			return renderFailMsg("输入的手机号码错误，请检查");
		}
		if(type==null || "".equals(type)){
			return renderFailMsg("类型不能为空");
		}
		
		// 检查psid是及签名是否输入
		try {
			checkKeySafe();
		} catch (UnsatisfiedParamException e) {
			return renderFailMsg(e.getMessage());
		}
		if (!sign.equals(signstr)) {
			return renderFailMsg("签名错误");
		}
		
		try{
			AccountInfo ac = new AccountInfo();
			ac.setAccountID(Long.parseLong(accountid));
			ac.setMobile(Long.parseLong(mobile));
			//生成、下发、保存验证码
			accountservice.sendPIN(ac, Integer.parseInt(type));
			// 内容签名
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS,SEED);
			JSONObject out = super.createSuccessRespJson(respSign);
			outText = out;
		}catch(HuiHuaException e){
			outText = super.createFailRespJson(e.getMessage());
		}
		return super.SUCCESS;
		
	}
	/**
	 * 验证验证码
	 * @return
	 */
	public String checkpin(){
		String validPrompt = "1.4验证验证码参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String pin = this.getRequest().getParameter("pin");
		String type = this.getRequest().getParameter("type");
		String signstr = StrMD5.getInstance().signature(accountid , pin , type , SEED);
		
		if(accountid==null || "".equals(accountid)){
			return renderFailMsg("账号 不能为空");
		}
		if(pin==null || "".equals(pin)){
			return renderFailMsg("验证码不能为空");
		}
		if(type==null || "".equals(type)){
			return renderFailMsg("类型不能为空");
		}
		
		// 检查签名是否输入
//		try {
//			checkKeySafe();
//		} catch (UnsatisfiedParamException e) {
//			return renderFailMsg(e.getMessage());
//		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		
		PinObject po_param = new PinObject();
		po_param.setAccountid(Long.parseLong(accountid));
		po_param.setLast_pin(Long.parseLong(pin));
		po_param.setType(VerType.forValue(Integer.parseInt(type)));
		
		try{
			PinObject po = this.accountservice.checkPIN(po_param);
			if(po.isCheckResult()){
				// 内容签名
				String respSign = StrMD5.getInstance().signature(RESP_SUCCESS, 0 , SEED);
				outText = super.createSuccessRespJson(respSign);
			}else{
				outText = super.createFailRespJson("验证码错误");
			}
		}catch(HuiHuaException e){
			outText = super.createFailRespJson(e.getMessage());
		}
		return super.SUCCESS;
	}
	/**
	 *  绑定手机
	 * @return
	 */
	public String bindMobile(){
		String validPrompt = "1.5绑定手机参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String mobile = this.getRequest().getParameter("mobile");
		String pin = this.getRequest().getParameter("pin");
		String signstr = StrMD5.getInstance().signature(accountid ,mobile , pin , SEED);
		if(accountid==null || "".equals(accountid)){
			return renderFailMsg("账号 不能为空");
		}
		if(mobile==null || "".equals(mobile)){
			return renderFailMsg("手机号不能为空");
		}
		if(!RegExp.isMobile(mobile)){
			return renderFailMsg("输入的手机号码错误，请检查");
		}
		if(pin==null || "".equals(pin)){
			return renderFailMsg("验证码不能为空");
		}
		
		// 检查签名是否输入
//		try {
//			checkKeySafe();
//		} catch (UnsatisfiedParamException e) {
//			return renderFailMsg(e.getMessage());
//		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		
		try{
			//校验验证码
			PinObject po_param = new PinObject();
			po_param.setAccountid(Long.parseLong(accountid));
			po_param.setLast_pin(Long.parseLong(pin));
			po_param.setType(VerType.MOBILE);
			PinObject po = this.accountservice.checkPIN(po_param);
			if(!po.isCheckResult()){
				outText = super.createFailRespJson("验证码错误");
			}
			//更改手机号
			AccountInfo ac = new AccountInfo();
			ac.setAccountID(Long.parseLong(accountid));
			ac.setMobile(Long.parseLong(mobile));
			accountservice.changeMobile(ac);
			// 内容签名
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS , SEED);
			outText = super.createSuccessRespJson(respSign);
		}catch(HuiHuaException e){
			outText = super.createFailRespJson("注册失败");
		}
		return super.SUCCESS;
	}
	
	/**
	 *  修改手机手机（目前不用）
	 * @return
	 */
	public String changeMobile(){
		
		return super.SUCCESS;
	}
	
	/**
	 *  更新密码（通过手机验证码方式）
	 * @return
	 */
	public String pwdBack(){
		String validPrompt = "1.7 找回密码  参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String newpwd = this.getRequest().getParameter("newpwd");
		String signstr = StrMD5.getInstance().signature(accountid ,newpwd , SEED);
		if(accountid==null || "".equals(accountid)){
			return renderFailMsg("账号 不能为空");
		}
		if(newpwd==null || "".equals(newpwd)){
			return renderFailMsg("新密码不能为空");
		}
		// 检查签名是否输入
//		try {
//			checkKeySafe();
//		} catch (UnsatisfiedParamException e) {
//			return renderFailMsg(e.getMessage());
//		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		AccountInfo ac = new AccountInfo();
		ac.setAccountID(Long.parseLong(accountid));
		ac.setLoginPwd(newpwd);
		try{
			this.accountservice.pwdBack(ac);
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS , SEED);
			outText = super.createSuccessRespJson(respSign);
			
		}catch(Exception e){
			outText = super.createFailRespJson("内部错误，修改密码失败");
		}
		return super.SUCCESS;
	}
	
	/**
	 *  更新密码（通过输入旧密码的方式）
	 * @return
	 */
	public String changePwdByPwd(){
		String validPrompt = "1.8 修改密码  参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String oldpwd = this.getRequest().getParameter("oldpwd");
		String newpwd = this.getRequest().getParameter("newpwd");
		String signstr = StrMD5.getInstance().signature(accountid ,oldpwd,newpwd , SEED);
		if(accountid==null || "".equals(accountid)){
			return renderFailMsg("账号 不能为空");
		}
		if(newpwd==null || "".equals(newpwd)){
			return renderFailMsg("新密码不能为空");
		}
		if(oldpwd==null || "".equals(oldpwd)){
			return renderFailMsg("旧密码不能为空");
		}
		
		// 检查签名是否输入
//		try {
//			checkKeySafe();
//		} catch (UnsatisfiedParamException e) {
//			return renderFailMsg(e.getMessage());
//		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		AccountInfo ac = new AccountInfo();
		ac.setAccountID(Long.parseLong(accountid));
		ac.setLoginPwd(oldpwd);
		
		try{
			this.accountservice.changePwdByPwd(ac, newpwd);
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS , SEED);
			outText = super.createSuccessRespJson(respSign);
		}catch(Exception e){
			outText = super.createFailRespJson("内部错误，修改密码失败");
		}
		
		return super.SUCCESS;
	}
	
	/**
	 *  用户信息补全
	 * @return
	 */
	public String supplement(){
		String validPrompt = "1.9 用户信息补全  参数校验错误: 请求参数值: " + super.getQueryString() + ", 错误信息: ";
		
		String accountid = this.getRequest().getParameter("accountid");
		String username = this.getRequest().getParameter("username");
		String birthday = this.getRequest().getParameter("birthday");
		String sex = this.getRequest().getParameter("sex");
		String address = this.getRequest().getParameter("address");
		String signstr = StrMD5.getInstance().signature(accountid ,username,birthday,sex ,address, SEED);
		
		if(accountid==null || "".equals(accountid)){
			return renderFailMsg("账号 不能为空");
		}
		if(birthday!=null && !RegExp.isDigit(birthday)){
			return renderFailMsg("日期格式错误");
		}
		if(sex!=null && !RegExp.isDigit(sex)){
			return renderFailMsg("性别错误");
		}
		// 检查签名是否输入
//		try {
//			checkKeySafe();
//		} catch (UnsatisfiedParamException e) {
//			return renderFailMsg(e.getMessage());
//		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		AccountInfo ac = new AccountInfo();
		ac.setAccountID(Long.parseLong(accountid));
		ac.setUserName(username);
		ac.setSex(AccountSex.forValue(Integer.parseInt(sex)));
		ac.setBirthday(Long.parseLong(birthday));
		ac.setAddress(address);
		try{
			this.accountservice.supplement(ac);
			String respSign = StrMD5.getInstance().signature(RESP_SUCCESS , SEED);
			outText = super.createSuccessRespJson(respSign);
		}catch(Exception e){
			outText = super.createFailRespJson("补全信息失败");
		}
		
		return super.SUCCESS;
	}
	
	
	/**
	 *  用户信息补全
	 * @return
	 */
	public String isexistmobile(){
		String mobile = this.getRequest().getParameter("mobile");
		String signstr = StrMD5.getInstance().signature(mobile , SEED);
	
		if(mobile==null || "".equals(mobile)){
			return renderFailMsg("手机号不能为空");
		}
		if(!RegExp.isMobile(mobile)){
			return renderFailMsg("输入的手机号码错误，请检查");
		}
		
		// 检查签名是否输入
//		try {
//			checkKeySafe();
//		} catch (UnsatisfiedParamException e) {
//			return renderFailMsg(e.getMessage());
//		}
//		if (!sign.equals(signstr)) {
//			return renderFailMsg("签名错误");
//		}
		try{
			JSONObject out = new JSONObject();
			if(!this.accountservice.mobileIsExist(Long.parseLong(mobile))){
				String respSign = StrMD5.getInstance().signature(RESP_SUCCESS , 0 ,SEED);
				out = super.createSuccessRespJson(respSign);
				out.put("isexist", 0);
				outText = out;
			}else{
				String respSign = StrMD5.getInstance().signature(RESP_SUCCESS , 1 ,SEED);
				out = super.createSuccessRespJson(respSign);
				out.put("isexist", 1);
				outText = out;
			}
		}catch(HuiHuaException e){
			outText = super.createFailRespJson(e.getMessage());
		}
		return super.SUCCESS;
		
	}
	
	public static void main(String[] args) {
		System.out.println(StrMD5.getInstance().encrypt("111111", "lUL1AsXp"));
	}

}
