package com.babi.common.constant;

public enum CodeEnum {
	SYS_ERROR("SYSTEM_ERROR", "服务器开小差啦"),

	SYS_PARAM_ERR("SYS_PARAM_ERR", "参数不合法"),
	
	ORDER_OPEN_FAIL("O001", "打开失败！"),

	ORDER_OPEN_SUCCESS("O002", "打开成功！"),

	ORDER_CLOSE_FAIL("O003", "关闭失败！"),

	ORDER_CLOSE_SUCCESS("O004", "关闭成功！"),

	SYS_SUCCESS("SUCCESS", "请求成功");
	private String code;

	private String msg;

	private CodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
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
}
