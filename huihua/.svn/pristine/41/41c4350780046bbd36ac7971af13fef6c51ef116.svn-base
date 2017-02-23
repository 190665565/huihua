package com.partner.huihua.utils.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.partner.huihua.utils.common.Config;
import com.partner.huihua.utils.common.DateUtil;
import com.partner.huihua.utils.common.XString;
import com.partner.huihua.utils.exception.UnsatisfiedParamException;
import com.partner.huihua.utils.page.PageInfo;
import com.partner.huihua.utils.security.StrMD5;


/**
 * Struts2 action父类, 包括struts常用方法
 * @author rainyhao
 * @since 2015-3-5 下午1:21:55
 */
@SuppressWarnings("unchecked")
public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1142543691766182104L;

	/**
	 * 日志输出器
	 */
	protected Logger log = Logger.getLogger(this.getClass());

	/**
	 * 接口调用状态: 成功
	 */
	protected static final int RESP_SUCCESS = 0;

	/**
	 * 接口调用状态: 失败
	 */
	protected static final int RESP_FAIL = 1;

	/**
	 * 接口调用签名参数
	 */
	protected static final String SEED = Config.getChannel("seed");

	// 几乎几有接口都会有的请求参数

	/**
	 * 用户名, passport名称
	 */
	protected String username;

	/**
	 * 状态(其他...)
	 */
	protected Integer status;
	/**
	 * 筛选日期范围: 开始时间(格式: yyyyMMddHHmmss)
	 */
	protected String startTime;
	/**
	 * 筛选日期范围: 结束时间(格式: yyyyMMddHHmmss)
	 */
	protected String endTime;
	/**
	 * 分页参数: 第几页
	 */
	protected Integer page;
	/**
	 * 分页参数: 每页多少行
	 */
	protected Integer rows;
	/**
	 * 排序字段
	 */
	protected String sort;
	/**
	 * 排序顺序
	 */
	protected String order;

	/**
	 * 查询接口是否统计总记录数
	 */
	protected String needCount;
	/**
	 * 接口调用签名
	 */
	protected String sign;

	/**
	 * 配合Struts2 json插件输出响应数据的输出参数
	 */
	protected Object outText;
	
	/**
	 * 本次请求的辅助分页信息
	 */
	protected PageInfo pageInfo;

	/**
	 * 获取request对象
	 * @author rainyhao
	 * @since 2015-3-5 下午1:41:34
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取response对象
	 * @author rainyhao
	 * @since 2015-3-5 下午1:42:20
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 获取请求参数的查询字符串形式
	 * @author rainyhao 
	 * @since 2015-4-1 下午7:10:22
	 * @return
	 */
	protected String getQueryString() {
		StringBuffer queryString = new StringBuffer();
		Enumeration<String> names = getRequest().getParameterNames();
		while (names.hasMoreElements()) {
			String name =  names.nextElement();
			String value = getRequest().getParameter(name);
			queryString.append(name.toString()).append("=").append(value).append("&");
		}
		return 0 == queryString.length() ? "" : queryString.subSequence(0, queryString.length() - 1).toString();
	}

	/**
	 * 获取当前page, rows所组成的分页对象
	 * @author rainyhao
	 * @since 2015-3-9 下午7:49:18
	 * @return
	 */
	protected PageInfo getPageInfo() {
		if (needCount != null) {
			needCount = needCount.trim();
		}
		return getPageInfo(!XString.isNullOrEmpty(needCount) && "true".equals(needCount));
	}

	/**
	 * 获取当前page, rows所组成的分页对象
	 * @author rainyhao
	 * @since 2015-3-9 下午7:51:45
	 * @param statisTotal 是否让mybatis分页拦截器发送select count语句
	 * @return
	 */
	protected PageInfo getPageInfo(boolean statisTotal) {
		pageInfo = new PageInfo(this.page, rows);
		pageInfo.setStatisTotal(statisTotal);
		// 直接赋值, 请在要排序的sql中具体做控制
		pageInfo.setSort(sort);
		pageInfo.setOrder(order);
		return pageInfo;
	}

	/**
	 * 检查签名是否为空
	 * @author rainyhao
	 * @since 2015-3-10 下午3:46:20
	 */
	protected void checkKeySafe() throws UnsatisfiedParamException {
		// 检查请求参数
		if (null == sign || "".equals(sign)) {
			log.info("请求参数sign不能为空");
			throw new UnsatisfiedParamException("请求参数sign不能为空");
		}
	}

	/**
	 * 用来检查签名是否正确
	 * <p>
	 * 在checkKeySafe()方法之后检查，{@link #checkKeySafe()}
	 * </p>
	 * @param strSign 焦点数据生成的签名
	 * @throws UnsatisfiedParamException
	 */
	protected void checkSignIsEquals(String strSign) throws UnsatisfiedParamException {
		if (strSign == null) {
			strSign = "";
		}
		if (!strSign.equals(sign)) {
			log.info("sign验证签名不正确, sign: " + sign + ", strSign: " + strSign);
			throw new UnsatisfiedParamException("sign验证签名不正确");
		}
	}
	/**
	 * 检查时间范围以及分页参数的默认值
	 * @author rainyhao
	 * @since 2015-3-10 下午3:50:31
	 * @throws UnsatisfiedParamException
	 */
	protected void checkParamSafe() throws UnsatisfiedParamException {
		// 为空参数设置默认值
		// 开始时间默认一周之前
		if (null == startTime || "".equals(startTime.trim())) {
			Date beforeWeek = DateUtil.different(-7);
			startTime = DateUtil.DateToString(beforeWeek, "yyyyMMddHHmmss");
		} else { // 不为空的时候, 检查格式是否正确
			Date temp = DateUtil.parse(startTime, "yyyyMMddHHmmss");
			if (null == temp) {
				String errorMsg = "请求参数startTime格式必须为yyyyMMddHHmmss";
				log.info(errorMsg);
				throw new UnsatisfiedParamException(errorMsg);
			}
		}
		// 结束时间, 默认当前时间
		if (null == endTime || "".equals(endTime.trim())) {
			Date now = DateUtil.now();
			endTime = DateUtil.DateToString(now, "yyyyMMddHHmmss");
		} else { // 不为空的时候, 检查格式是否正常
			Date temp = DateUtil.parse(endTime, "yyyyMMddHHmmss");
			if (null == temp) {
				String errorMsg = "交易查询: 请求参数endTime格式必须为yyyyMMddHHmmss";
				log.info(errorMsg);
				throw new UnsatisfiedParamException(errorMsg);
			}
		}
		// 页码
		if (null == page || page<1) {
			page = 1;
		}
		// 页尺寸
		if (null == rows) {
			rows = 10;
		}
		if (rows > 50) {
			rows = 50;
		}
		// 通用地解决一下排序问题, 具体是否起作用, 在具体的sql中控制
		// 例子见TransInfoMapper.selectByPage的sql实现, 
		// 如果sql本身不需要排序或者就有固定的排序, 可以不理会这些下边两个检查操作
		// 排序字段, 默认createTime
		if (XString.isNullOrEmpty(sort)) {
			sort = "id";
		}
		// 排序顺序, 默认desc, 如果值不是asc和desc
		if (XString.isNullOrEmpty(order) || (!"asc".equals(order.toLowerCase()) && !"desc".equals(order.toLowerCase()))) {
			order = "desc";
		}
	}
	
	/**
	 * 清空排序
	 * @author rainyhao 
	 * @since 2015-3-28 下午1:42:24
	 */
	protected void resetSort() {
		sort = null;
		order = null;
	}

	/**
	 * 生成所有响应数据中的外层json字符串
	 * @author rainyhao
	 * @since 2015-3-5 下午1:49:52
	 * @param psid 业务线psid
	 * @param status 状态, 0: 成功, 1: 失败
	 * @param errorDesc 错误描述
	 * @param sign 响应内容签名
	 * @return
	 */
	protected JSONObject createRespOuterJson(int status, String errorDesc, String sign) {
		JSONObject out = new JSONObject();
		out.put("status", status);
		out.put("errorDesc", errorDesc);
		out.put("total", pageInfo!=null&&!XString.isNullOrEmpty(needCount)&&"true".equals(needCount.trim()) ? pageInfo.getTotalCount() : "");
		out.put("sign", sign);
		return out;
	}

	//装载返回信息
	protected JSONObject createRespOuterJsonByData(int psid, int respSuccess, String errorDesc, String sign, Object o) {
		JSONObject out = new JSONObject();
		out.put("psid", psid);
		out.put("status", respSuccess);
		out.put("errorDesc", errorDesc);
		out.put("total", pageInfo!=null&&!XString.isNullOrEmpty(needCount)&&"true".equals(needCount.trim()) ? pageInfo.getTotalCount() : "");
		out.put("sign", sign);
		out.put("data", o);
		return out;
	}

	/**
	 * 生成操作失败json
	 * @author rainyhao
	 * @since 2015-3-9 下午7:22:07
	 * @param errorDesc 错误信息
	 * @return
	 */
	protected JSONObject createFailRespJson(String errorDesc) {
		return createRespOuterJson(RESP_FAIL, errorDesc, "");
	}

	/**
	 * 生成操作成功的外层json
	 * @author rainyhao
	 * @since 2015-3-9 下午8:12:30
	 * @param sign 内容签名
	 * @return
	 */
	protected JSONObject createSuccessRespJson(String sign) {
		return createRespOuterJson(RESP_SUCCESS, "", sign);
	}

	/**
	 * 生成操作失败json并更改json输出变量 配合struts2的json插件使用, 预先装输出内容赋值到要输出json的变量上
	 * @author rainyhao
	 * @since 2015-3-9 下午7:23:01
	 * @param errorDesc
	 */
	protected String renderFailMsg(String errorDesc) {
		outText = createFailRespJson(errorDesc);
		return SUCCESS;
	}
	

	/**
	 * 使用输出流向调用端输出错误信息
	 * @author rainyhao
	 * @since 2015-3-11 下午2:15:41
	 * @param errorDesc
	 */
	protected void renderFailText(String errorDesc) {
		JSONObject out = createFailRespJson(errorDesc);
		renderText(out);
	}

	/**
	 * 生成操作成功结果的json
	 * 并更改json输出变量 配合struts2的json插件使用,
	 * 预先将输出内容赋值到要输出json的变量上
	 * @author rainyhao
	 * @since 2015-3-15 下午10:25:42
	 * @param signParams 响应内容签名参数
	 */
	protected String renderOkMsg(Object... signParams) {
		// 生成响应内容签名
		String sign = StrMD5.getInstance().signature(signParams);
		JSONObject out = createRespOuterJson(RESP_SUCCESS, "", sign);
		outText = out;
		return SUCCESS;
	}

	/**
	 * 使用servlet输出流向调用端输出操作成功的json
	 * @author rainyhao
	 * @since 2015-3-15 下午10:29:32
	 * @param signParams 响应内容签名参数
	 */
	protected void renderOkText(Object... signParams) {
		// 生成响应内容签名
		String sign = StrMD5.getInstance().signature(signParams);
		JSONObject out = createRespOuterJson(RESP_SUCCESS, "", sign);
		renderText(out);
	}

	/**
	 * 使用输出 流向调用端输出基本响应内容
	 * @author rainyhao
	 * @since 2015-3-5 下午2:37:21
	 * @param psid 业务线psid
	 * @param status 状态, 0: 成功, 1: 失败
	 * @param errorDesc 错误描述
	 * @param sign 响应内容签名
	 */
	protected void render(int psid, int status, String errorDesc, String sign) {
		JSONObject out = createRespOuterJson(status, errorDesc, sign);
		renderText(out);
	}

	/**
	 * 使用输出流向调用端输出文本
	 * @author rainyhao
	 * @since 2015-3-5 下午2:12:36
	 * @param content 输出内容
	 */
	protected void renderText(Object content) {
		HttpServletResponse response = getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(content.toString());
			out.flush();
		} catch (IOException e) {
			log.error("向调用端输出数据错误, 数据内容: " + content, e);
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	/**
	 * 获得HttpServletResponse的Writer
	 * @return
	 */
	public Writer getWriter() {
		try {
			return getResponse().getWriter();
		} catch (IOException e) {
			log.error("获得Writer失败: ", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭writer
	 * @param writer
	 * @return
	 */
	public boolean closeWirter(Writer writer) {
		try {
			if (writer != null) {
				writer.close();
			}
			return true;
		} catch (IOException e) {
			log.error("关闭Writer失败: ", e);
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 判断是否还有剩余的记录没展示
	 * @param writer
	 * @return
	 */
	public boolean isremain() {
		return this.pageInfo.getTotalCount()-this.page*this.rows>0;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}


	public void setNeedCount(String needCount) {
		this.needCount = needCount;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Object getOutText() {
		return outText;
	}
}
