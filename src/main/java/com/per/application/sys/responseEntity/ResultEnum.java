package com.per.application.sys.responseEntity;

public enum ResultEnum {
	OK(true, 200, "成功"), CREATED(true, 201, "成功"), NO_CONTENT(true, 204, "成功"), BAD_REQUEST(false, 400, "参数错误"),
	UNAUTHORIZED(false, 401, "未授权或授权时间已到，请重新登录"), NOT_FOUND(false, 404, "资源未找到"), SERVER_ERROR(false, 500, "服务器异常");

	private Boolean success;
	private Integer code;
	private String message;

	ResultEnum(Boolean success, Integer code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
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

}
