package com.babi.common.beans;

import java.util.HashMap;
import java.util.Map;

import com.babi.common.constant.CodeEnum;

public class Result {
	// 返回码
	private String code;
	// 返回消息
	private String msg;
	// map数据
	private Map<String, Object> data;

	public static Result setCodeMsg(String code, String msg) {
		Result r = new Result();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static Result setCodeMsgData(String code, String msg, String key, Object value) {
		Result r = new Result();
		r.setCode(code);
		r.setMsg(msg);
		Map<String, Object> map = new HashMap<String, Object>();
		r.setData(map);
		r.getData().put(key, value);
		return r;
	}

	public static Result setCodeMsg(CodeEnum codeEnum) {
		Result r = new Result();
		r.setCode(codeEnum.getCode());
		r.setMsg(codeEnum.getMsg());
		return r;
	}

	public static Result setCodeMsgData(CodeEnum codeEnum, String key, Object value) {
		Result r = new Result();
		r.setCode(codeEnum.getCode());
		r.setMsg(codeEnum.getMsg());
		Map<String, Object> map = new HashMap<String, Object>();
		r.setData(map);
		r.getData().put(key, value);
		return r;
	}

	public Result addData(String key, Object value) {
		if (this.getData() != null) {
			this.getData().put(key, value);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			this.setData(map);
			this.getData().put(key, value);
		}
		return this;
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

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public static class ResultBuilder {
		private Result result = new Result();

		public ResultBuilder buildCodeMsg(String code, String msg) {
			result.setCode(code);
			result.setMsg(msg);
			return this;
		}

		public ResultBuilder buildCodeMsg(CodeEnum codeEnum) {
			result.setCode(codeEnum.getCode());
			result.setMsg(codeEnum.getMsg());
			return this;
		}

		public ResultBuilder buildData(String key, Object value) {
			if (this.result.getData() == null) {
				Map<String, Object> map = new HashMap<>();
				map.put(key, value);
				result.setData(map);
			} else {
				result.getData().put(key, value);
			}
			return this;
		}

		public Result build() {
			return result;
		}
	}

	public static ResultBuilder build() {
		return new ResultBuilder();

	}
}
