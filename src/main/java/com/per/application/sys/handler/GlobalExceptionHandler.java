package com.per.application.sys.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常捕获失败时，启动该类捕获
 * @author MingyuLI
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class) // 所有的异常都是Exception子类
	public void defaultErrorHandler(HttpServletRequest request, Exception e) { // 出现异常之后会跳转到此方法
		logger.error("【异常信息】\n{}", e.getMessage());
	}
}