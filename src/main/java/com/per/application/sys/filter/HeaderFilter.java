package com.per.application.sys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.per.application.utils.AlgorithmUtils;
import com.per.application.utils.IPUtils;
import com.per.application.utils.ValidationMethods;

@Component
@Order(3)
@WebFilter(urlPatterns = "/*", filterName = "ACAFilter")
public class HeaderFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(HeaderFilter.class);

	private static String[] STATIC_URL = { ".html", ".css", ".png", ".js", ".jpg", ".gif", ".woff2", ".xlsx" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURL().toString();
		String ip = IPUtils.getIpAddress(request);
		if (printFilter(url)) {
			logger.info("【" + ip + "】【" + request.getMethod() + "】" + url);
		}

		// 内网允许跨域
//		if (ip.contains("0:0:0:0:0:0:0:1")) {
//			response.setHeader("Access-Control-Allow-Origin", "*");
//			response.setHeader("Access-Control-Allow-Methods", "POST, GET");
//			response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//			response.setHeader("Access-Control-Max-Age", "3600");
//		} 
		if (url.contains("/admin/")) {
			String token = request.getParameter("token");
			if (ValidationMethods.isNotEmpty(token) && AlgorithmUtils.valiadToken(token)) {
				filterChain.doFilter(servletRequest, servletResponse);
			} else {
				logger.error("TOKEN无效：" + token);
				response.sendRedirect("/tologin");
			}
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
//		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
	}

	/**
	 * 过滤目标静态资源
	 * 
	 * @param url
	 * @return
	 */
	private boolean printFilter(String url) {
		for (String t : STATIC_URL) {
			if (url.endsWith(t)) {
				return false;
			}
		}
		return true;
	}
}
