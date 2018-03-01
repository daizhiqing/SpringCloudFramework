package com.dzq.example.model.resp;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author daizhiqng
 * @date 2018-01-02 16:18 Created
 */
public class JsonView {
	private String code;
	private String msg;
	private Object data;

	public JsonView() {
	}

	private JsonView(ErrorEnum errorEnum) {
		this.code = errorEnum.getCode();
		this.msg = errorEnum.getMsg();
		this.data = isBaseData("");
	}

	private JsonView(ErrorEnum errorEnum, Object data) {
		this.code = errorEnum.getCode();
		this.msg = errorEnum.getMsg();
		this.data = isBaseData(data);
	}

	/**
	 * 返回信息
	 * 
	 * @param errorEnum
	 *            返回码枚举
	 * @return
	 */
	public static JsonView info(ErrorEnum errorEnum) {
		return new JsonView(errorEnum);
	}

	/**
	 * 返回信息，带返回数据
	 * 
	 * @param errorEnum
	 *            返回码枚举
	 * @param data
	 *            返回数据
	 * @return
	 */
	public static JsonView info(ErrorEnum errorEnum, Object data) {
		return new JsonView(errorEnum, data);
	}

	/**
	 * 返回成功
	 * 
	 * @return
	 */
	public static JsonView success() {
		return info(ErrorEnum.SUCCESS);
	}

	/**
	 * 返回成功，带返回数据
	 * 
	 * @param data
	 * @return
	 */
	public static JsonView success(Object data) {
		return info(ErrorEnum.SUCCESS, data);
	}

	/**
	 * 返回系统错误
	 * 
	 * @return
	 */
	public static JsonView failed() {
		return info(ErrorEnum.SYSTEM_ERROR);
	}

	public static JsonView failed(Object data) {
		return info(ErrorEnum.SYSTEM_ERROR , data);
	}

	/**
	 * Token无效
	 * 
	 * @return
	 */
	public static JsonView tokenInvalid() {
		return info(ErrorEnum.SESSION_TIMEOUT);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 判断一个类是否为基本数据类型。
	 * 
	 * @return true 表示为基本数据类型。
	 */
	private static Object isBaseData(Object data) {
		if (data == null) {
			return null;
		}
		Class<? extends Object> clazz = data.getClass();
		if (clazz.equals(String.class) ||
				data instanceof String[] ||
				data instanceof java.util.List ||
				clazz.equals(Integer.class) ||
				clazz.equals(Boolean.class) ||
				clazz.equals(Long.class) ||
				clazz.equals(Double.class) ||
				clazz.equals(Float.class) ||
				clazz.equals(Character.class) ||
				clazz.equals(Short.class) ||
				clazz.equals(BigDecimal.class) ||
				clazz.equals(BigInteger.class) ||
				clazz.isPrimitive()) {
			JSONObject json=new JSONObject();
			json.put("result", data);
			return json;
		}
		return JSONObject.toJSON(data);
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
