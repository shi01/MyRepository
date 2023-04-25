package com.per.application.utils;

public class ValidationMethods {
	public static boolean isEmpty(Object o) {
		return (o == null)
				|| (o.toString().trim().length() == 0 || (o.toString().trim().equals("null")) || "".equals(o));
	}

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	/**
	 * 仅用于日志打印，不可用于逻辑判断
	 * @param o
	 * @return
	 */
	public static String toString(Object o) {
		return isEmpty(o) ? "null" : o.toString();
	}
}
