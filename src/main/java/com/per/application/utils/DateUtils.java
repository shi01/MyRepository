package com.per.application.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT);
	public static String DATA_FORMAT2 = "yyyy-MM-dd";
	public static SimpleDateFormat sdf2 = new SimpleDateFormat(DATA_FORMAT2);
	public static String DATA_FORMAT3 = "yyyy年MM月dd日";
	public static SimpleDateFormat sdf3 = new SimpleDateFormat(DATA_FORMAT3);

	public static SimpleDateFormat getSDF(String str) {
		return new SimpleDateFormat(str);
	}

	/**
	 * 日期时间 转 无时分秒的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date time2Date(Date date) {
		try {
			return sdf2.parse(sdf2.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Date转String
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str1(Date date) {
		return sdf.format(date);
	}

	/**
	 * Date转String
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str2(Date date) {
		return sdf2.format(date);
	}

	/**
	 * String转Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date1(String date) {
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * String转Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date2(String date) {
		try {
			return sdf2.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前年
	 * 
	 * @return 2021
	 */
	public static int getCurrentYear() {
		return Year.now().getValue();
	}

	/**
	 * 验证当前系统时间是否在[A,B]区间
	 * 
	 * @param a 开始时间
	 * @param b 结束时间
	 * @return
	 */
	public static boolean betweenAandB(Date a, Date b) {
		Date now = getDate();
		if (a.getTime() <= now.getTime() && b.getTime() >= now.getTime()) {
			return true;
		}
		return false;
	}

	// 不能用int类型，丢失精度，无法跨年
//	public static Date calcDate(Date start, int num) {
//		return new Date(start.getTime() + num * 24 * 60 * 60 * 1000);
//	}

	/**
	 * 指定日期加减指定天数
	 * 
	 * @param start
	 * @param num   （正数为加，负数为减）
	 * @return 新的日期
	 */
	public static Date calcDate(Date date, long num) throws ParseException {
		return new Date(date.getTime() + num * 24 * 60 * 60 * 1000); // 将毫秒数转换成日期
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param small 较小的时间
	 * @param big   较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date small, Date big) throws ParseException {
		small = sdf2.parse(sdf2.format(small));
		big = sdf2.parse(sdf2.format(big));
		Calendar cal = Calendar.getInstance();
		cal.setTime(small);
		long time1 = cal.getTimeInMillis();
		cal.setTime(big);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param small 较小的时间
	 * @param big   较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(String small, String big) throws ParseException {
		Date smallDate = sdf2.parse(small);
		Date bigDate = sdf2.parse(big);
		Calendar cal = Calendar.getInstance();
		cal.setTime(smallDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bigDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 当前时间字符串形式
	 * 
	 * @return
	 */
	public static String now2SDF() {
		return sdf.format(getDate());
	}

	public static Date getDate() {
		return new Date();
	}

	/**
	 * 字符串yyyy-MM-dd, yyyy/MM/dd, yyyy年MM月dd日, yyyy.MM.dd转日期不含时分秒
	 * 
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str) {
		if (str == null) {
			return null;
		}
		try {
			return sdf2.parse(str);
		} catch (Exception e) {
			try {
				return getSDF("yyyy/MM/dd").parse(str);
			} catch (Exception e2) {
				try {
					return sdf3.parse(str);
				} catch (Exception e3) {
					try {
						return getSDF("yyyy.MM.dd").parse(str);
					} catch (Exception e4) {
						return null;
					}
				}
			}
		}
	}
}
