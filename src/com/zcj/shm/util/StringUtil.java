package com.zcj.shm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.UUID;

public class StringUtil {

	public static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	public static String getRandomPwd() {
		char[] arr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z' };
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = (c.get(Calendar.MINUTE));
		int second = (c.get(Calendar.SECOND));
		int millisecond = (c.get(Calendar.MILLISECOND));
		int x = (int) (Math.random() * 26);
		int y = (int) (Math.random() * 26);
		return md5(arr[x] + year + "" + month + "" + date + "" + hour + "" + minute + "" + second + "" + millisecond + "" + arr[y]);
	}

	public static String getMD5Password(String password, String salt) {
		return md5(md5(password) + salt);
	}

	public static String getNewName(String fileName) {
		return UUID.randomUUID().toString().replace("-", "") + "." + getFileType(fileName);
	}

	public static String getFileType(String fileName) {
		String[] split = fileName.split("\\.");
		int length = split.length;
		return split[length - 1];

	}

	/**
	 * 智能字符串切割，一个中文字符算两位，一个英文字符算一位
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String cutString(String str, int length) {
		int realPosition = calculatePlaces(str, length);
		if (realPosition != -1) {
			str = str.substring(0, realPosition);
		}
		return str;
	}

	/**
	 * 根据给定长度(一个中文字符算两位，一个英文字符算一位),得出真实位置
	 * 
	 * @param str
	 * @return
	 */
	public static int calculatePlaces(String str, int length) {
		char arr[] = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if ((c >= 0x0391 && c <= 0xFFE5)) { // 中文字符
				if (length > 0) {
					length = length - 2;
				} else {
					return i;
				}
			} else if ((c >= 0x0000 && c <= 0x00FF)) { // 英文字符
				if (length > 0) {
					length = length - 1;
				} else {
					return i;
				}
			}
		}
		return -1;
	}

	public static String getNewString(String str) {
		String newString = "";
		try {
			newString = new String(str.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newString;
	}

	public static String encode2Utf8(String str) {
		try {
			str = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String decode2Utf8(String str) {
		try {
			str = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	// 判断某个字符串是否是数字字符串，若是返回0，若不是则返回-1
	public static boolean isNumberString(String value) {
		String numAllString = "0123456789";
		if (value.length() <= 0)
			return false;
		for (int i = 0; i < value.length(); i++) {
			String charInString = value.substring(i, i + 1);
			if (!numAllString.contains(charInString))
				return false;
		}
		return true;
	}

	// 判断某个字符串是否是float字符串，若是返回0，若不是则返回-1
	public static boolean isFloatString(String value) {
		if (!value.contains(".")) {
			return isNumberString(value);
		} else {
			String[] floatStringPartArray = value.split("\\.");
			if (floatStringPartArray.length == 2) {
				if (isNumberString(floatStringPartArray[0]) && isNumberString(floatStringPartArray[1]))
					return true;
				else
					return false;
			} else
				return false;
		}

	}
}
