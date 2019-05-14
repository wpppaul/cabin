package com.babi.commom.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.babi.common.constant.CodeEnum;

public class BabiDataResult implements Serializable {
	private static final long serialVersionUID = 1L;
	// 返回码
	private String code;
	// 返回消息
	private String message;
	// 返回数据
	private Map<String, Object> resultMap;

	public BabiDataResult() {
		this.setCode(new String());
		this.setMessage(new String());
		this.setResultMap(new HashMap<String, Object>());
	}

	/**
	 * 
	 * @createBy 李文慧
	 * @Date 2018年1月25日下午5:07:19
	 * @description success
	 *
	 */
	public static BabiDataResult success(String code, String message, Map<String, Object> resultMap) {
		BabiDataResult result = new BabiDataResult();
		result.setCode(code);
		result.setMessage(message);
		result.setResultMap(resultMap);
		return result;
	}

	public static BabiDataResult success(String code, String message, Object data) {
		BabiDataResult result = new BabiDataResult();
		result.setCode(code);
		result.setMessage(message);
		result.addData("data", data);
		return result;
	}

	public static BabiDataResult success(String key, Object data) {
		BabiDataResult result = new BabiDataResult();
		result.setCode("1");
		result.addData(key, data);
		return result;
	}

	public static BabiDataResult success() {
		BabiDataResult result = new BabiDataResult();
		result.setCode("1");
		return result;
	}

	public static BabiDataResult error() {
		BabiDataResult result = new BabiDataResult();
		result.setCode("0");
		return result;
	}

	public static BabiDataResult error(String code, String msg) {
		BabiDataResult result = new BabiDataResult();
		result.setCode(code);
		result.setMessage(msg);
		return result;
	}

	public static BabiDataResult error(String msg) {
		BabiDataResult result = new BabiDataResult();
		result.setCode("0");
		result.setMessage(msg);
		return result;
	}

	/**
	 * 
	 * @createBy 李文慧
	 * @Date 2018年1月25日下午5:07:08
	 * @description info
	 *
	 */
	public static BabiDataResult info(String code, String message) {
		BabiDataResult result = new BabiDataResult();
		result.setCode(code);
		result.setMessage(message);
		return result;
	}

	/**
	 * 
	 * @createBy 李文慧
	 * @Date 2018年1月25日下午5:06:55
	 * @description info
	 *
	 */
	public static BabiDataResult info(String code, String message, String key, Object data) {
		BabiDataResult result = new BabiDataResult();
		result.setCode(code);
		result.setMessage(message);
		result.addData(key, data);
		return result;
	}

	public static BabiDataResult setCodeMsg(CodeEnum codeEnum) {
		BabiDataResult r = new BabiDataResult();
		r.setCode(codeEnum.getCode());
		r.setMessage(codeEnum.getMsg());
		return r;
	}

	public static BabiDataResult info(CodeEnum ce, String key, Object data) {
		BabiDataResult result = new BabiDataResult();
		result.setCode(ce.getCode());
		result.setMessage(ce.getMsg());
		result.addData(key, data);
		return result;
	}

	public static BabiDataResult info(CodeEnum ce) {
		BabiDataResult result = new BabiDataResult();
		result.setCode(ce.getCode());
		result.setMessage(ce.getMsg());
		return result;
	}

	public void setCodeAndMessage(CodeEnum ce) {

		this.code = ce.getCode();
		this.message = ce.getMsg();

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public void addData(String key, Object data) {
		resultMap.put(key, data);
	}
}
