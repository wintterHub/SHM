package com.zcj.shm.util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zcj.shm.user.domain.ShmUser;

public class VerifyUtil {

	public static String getVcode(String email) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = (c.get(Calendar.MINUTE)) / 10;// 每十分钟内有效

		String code1 = StringUtil.md5(email + year + month + date + hour + minute);
		String code2 = "";
		for (int i = 0; i < code1.length(); i++) {
			if (Character.isDigit(code1.charAt(i)))
				code2 += code1.charAt(i);
		}
		return code2.substring(0, 4);
	}

	public static boolean isVcode(String email, String vcode) {
		return getVcode(email).equals(vcode);
	}

	/**
	 * 正则验证
	 * 
	 * @param value
	 * @param reg
	 * @return
	 */
	public static boolean match(String value, String reg) {
		Pattern regex = Pattern.compile(reg);
		Matcher matcher = regex.matcher(value);
		return matcher.matches();
	}

	public static boolean isImage(String contentType) {
		String[] types = { "image/bmp", "image/png", "image/gif", "image/jpeg", "image/pjpeg", "image/x-png" };
		for (String type : types) {
			if (contentType.equals(type)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAllImage(String[] contentType) {
		for (int i = 0; i < contentType.length; i++) {
			boolean isImage = isImage(contentType[i]);
			if (!isImage) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmail(String email) {
		String reg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(reg);
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}

}
