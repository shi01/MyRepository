package com.per.application.sys.responseEntity;

import org.springframework.lang.Nullable;

public class ServerResponse {
	private Boolean success;
	private Integer code;
	private String message;
	private Object data;
	private Object extra;

	// 构造方法设为私有
	private ServerResponse() {
	}

	public static ServerResponse newInstance() {
		return new ServerResponse();
	}

	public ServerResponse success(Boolean success) {
		this.success = success;
		return this;
	}

	public ServerResponse message(String message) {
		this.message = message;
		return this;
	}

	public ServerResponse data(Object data) {
		this.data = data;
		return this;
	}

	public ServerResponse extra(Object extra) {
		this.extra = extra;
		return this;
	}

	public static ServerResponse ok() {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(ResultEnum.OK.getSuccess());
		serverResponse.setCode(ResultEnum.OK.getCode());
		serverResponse.setMessage(ResultEnum.OK.getMessage()); // 成功展示默认提示信息
		serverResponse.setData(null); // 返回传入的参数
		return serverResponse;
	}

	public static ServerResponse ok(Object params) {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(ResultEnum.OK.getSuccess());
		serverResponse.setCode(ResultEnum.OK.getCode());
		serverResponse.setMessage(ResultEnum.OK.getMessage()); // 成功展示默认提示信息
		serverResponse.setData(params); // 返回传入的参数
		return serverResponse;
	}

	public static ServerResponse fail() {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(ResultEnum.BAD_REQUEST.getSuccess());
		serverResponse.setCode(ResultEnum.BAD_REQUEST.getCode());
		serverResponse.setMessage(ResultEnum.BAD_REQUEST.getMessage()); // 成功展示默认提示信息
		serverResponse.setData(null); // 返回传入的参数
		return serverResponse;
	}
	
	public static ServerResponse unauthorized() {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(ResultEnum.UNAUTHORIZED.getSuccess());
		serverResponse.setCode(ResultEnum.UNAUTHORIZED.getCode());
		serverResponse.setMessage(ResultEnum.UNAUTHORIZED.getMessage()); // token无效
		serverResponse.setData(null); // 返回传入的参数
		return serverResponse;
	}

	public static ServerResponse fail(String msg) {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(ResultEnum.BAD_REQUEST.getSuccess());
		serverResponse.setCode(ResultEnum.BAD_REQUEST.getCode());
		serverResponse.setMessage(msg); // 成功展示默认提示信息
		serverResponse.setData(null); // 返回传入的参数
		return serverResponse;
	}

	public static ServerResponse error(Exception e) {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(ResultEnum.SERVER_ERROR.getSuccess());
		serverResponse.setCode(ResultEnum.SERVER_ERROR.getCode());
		serverResponse.setMessage(ResultEnum.SERVER_ERROR.getMessage()); // 成功展示默认提示信息
		serverResponse.setData(e.getMessage()); // 返回传入的参数
		return serverResponse;
	}

	public static ServerResponse badRequest(@Nullable String message) {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(ResultEnum.BAD_REQUEST.getSuccess());
		serverResponse.setCode(ResultEnum.BAD_REQUEST.getCode());
		serverResponse.setMessage(message != null ? message : ResultEnum.BAD_REQUEST.getMessage()); // 校验失败传入指定的提示信息
		serverResponse.setData(null); // 校验失败不返回参数
		return serverResponse;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getExtra() {
		return extra;
	}

	public void setExtra(Object extra) {
		this.extra = extra;
	}

}