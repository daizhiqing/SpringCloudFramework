package com.dzq.example.model.resp;

/**
 * 返回码枚举类
 * 
 * @author daizhiqing
 * @date 2018年2月5日
 */
public enum ErrorEnum {
	/**
	 * 系统，0000
	 */
	/** 请求成功 */
	SUCCESS("000000", "请求成功"),
	/** 系统错误 */
	SYSTEM_ERROR("500000", "请求失败"),
	/** 新增失败 */
	INSERT_ERROR("000001", "新增失败"),
	/** 删除失败 */
	DELETE_ERROR("000002", "删除失败"),
	/** 更新失败 */
	UPDATE_ERROR("000003", "更新失败"),
	/** 查询数据不存在 */
	QUERY_DATA_ERROR("000004", "查询数据不存在"),
	/** 参数为空 */
	NULL_PARAM_ERROR("000005", "参数为空"),

	/**
	 * 用户，1000
	 */
	/** token无效 */
	SESSION_TIMEOUT("100008", "token过期重新登录"),

	/**
	 * 验证码，1001
	 */
	/** 手机验证码错误 */
	SMS_CODE_ERROR("100101", "手机验证码错误"),
	/** 验证码请求过于频繁 */
	COD_REQ_LIMIT("100102", "验证码请求过于频繁"),
	/** 手机号有误 */
	TEL_CODE_ERROR("100103", "手机号有误"),
	/** 图形验证码有误 */
	CAPTCHA_CODE_ERROR("100104", "图形验证码有误"),
	/** 验证码已失效，请重新获取 */
	TEL_CODE_OVERDUE("100105", "验证码已失效，请重新获取");


	private String code;
	private String msg;

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	ErrorEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
