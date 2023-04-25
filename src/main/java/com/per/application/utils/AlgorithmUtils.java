package com.per.application.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * 加密工具
 * 
 * @author MYL
 *
 */
public class AlgorithmUtils {

	/**
	 * 获取指定长度随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(3);
			long result = 0;
			switch (number) {
			case 0:
				result = Math.round(Math.random() * 25 + 65);
				sb.append(String.valueOf((char) result));
				break;
			case 1:
				result = Math.round(Math.random() * 25 + 97);
				sb.append(String.valueOf((char) result));
				break;
			case 2:
				sb.append(String.valueOf(new Random().nextInt(10)));
				break;
			}
		}
		return sb.toString();
	}

	public static String getToken(String str, Long userId) {
		// 6小时有效期
		long a = new Date().getTime() + 6 * 60 * 60 * 1000;
		str += UUID.randomUUID().toString() + "#" + a + "#" + userId;
		return base64encode(str) + UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	public static boolean valiadToken(String str) {
		long eff = 0;
		str = base64decode(str.substring(0, str.length() - 32));
		String[] split = str.split("#");
		if (split.length != 3) {
			return false;
		}
		try {
			eff = Long.valueOf(split[1]);
			Long.valueOf(split[2]);
		} catch (Exception e) {
			return false;
		}
		if (eff <= new Date().getTime()) {
			return false;
		}
		return true;
	}

	public static String getToken(HttpServletRequest request) {
		return request.getParameter("token");
	}

	public static Long getUserId(HttpServletRequest request) {
		String token = getToken(request);
		try {
			return Long.valueOf(base64decode(token.substring(0, token.length() - 32)).split("#")[2]);
		} catch (Exception e) {
			return null;
		}
	}

	public static Long getUserId(String token) {
		try {
			return Long.valueOf(base64decode(token.substring(0, token.length() - 32)).split("#")[2]);
		} catch (Exception e) {
			return null;
		}
	}

//	public static String getToken(String str) {
//		// 6小时有效期
//		long a = new Date().getTime() + 6 * 60 * 60 * 1000;
//		str += UUID.randomUUID().toString() + "#" + a;
//		return base64encode(str) + UUID.randomUUID().toString().replace("-", "").toUpperCase();
//	}
//
//	public static boolean valiadToken(String str) {
//		long eff = 0;
//		str = base64decode(str.substring(0, str.length() - 32));
//		String[] split = str.split("#");
//		if (split.length == 1) {
//			return false;
//		}
//		try {
//			eff = Long.valueOf(split[1]);
//		} catch (Exception e) {
//			return false;
//		}
//		if (eff <= new Date().getTime()) {
//			return false;
//		}
//		return true;
//	}

	public static String base64encode(String str) {
		try {
			return new String(Base64.getEncoder().encodeToString(str.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String base64decode(String str) {
		try {
			return new String(Base64.getDecoder().decode(str.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	/**
	 * md5加密 32位 小写
	 * 
	 * @param plainText
	 * @return
	 */
	public static String encryption(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "1" + re_md5;
	}

//	public static void main(String[] args) throws ParseException {
//		String createSecretkey = createSecretkey(1);
//		System.out.println(createSecretkey);
//	}

	public static String createSecretkey(long num) throws ParseException {
		String date = DateUtils.sdf2.format(DateUtils.calcDate(new Date(), num));
//		System.out.println(date);
		String key = "#" + date + "#";
		String str = getRandomString(11) + base64encode(key) + getRandomString(12);
		return str;
	}

	public static boolean valiadKey(String keyData) {
		try {
			String key = FileUtils.readFileContext(keyData);
			key = base64decode(key.substring(11).substring(0, key.substring(11).length() - 12)).replace("#", "");
			return DateUtils.str2Date2(key).getTime() >= DateUtils.getDate().getTime();
		} catch (Exception e) {
			return false;
		}
	}

	public static String getEnc(String str) {
		BasicTextEncryptor enc = new BasicTextEncryptor();
		enc.setPassword("MINGYULI");
		return enc.encrypt(str);
	}
}
