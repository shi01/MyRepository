package com.per.application.utils;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

public class DownloadUtils {
	/**
	 * 配置文件下载响应头 PS：swagger下载文件时，模板中文命名会乱码，通过浏览器请求不会乱码
	 * 
	 * @param response
	 * @param fileName
	 */
	public static HttpServletResponse setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
			}
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 下载失败时重置响应信息
	 * 
	 * @param response
	 * @param msg
	 */
	public static void response(HttpServletResponse response, String msg) {
		try {
			try {
				response.reset();
			} catch (Exception e) {
			}
			OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
			String data = "<script language='javascript'>if(confirm('" + msg
					+ "')){window.location.href='/html/export.html'}else{window.location.href='/html/export.html'}; </script>";
			writer.write(data);
			writer.close();
			// 防止nginx反向代理
//			response.sendRedirect("/html/export.html?msg="+msg);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 常用编码格式验证
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}
}
