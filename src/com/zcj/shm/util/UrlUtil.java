package com.zcj.shm.util;

public class UrlUtil {

	private static String[] userPages = new String[] { "myInfo", "myPublish", "myCollection", "myMessage", "changePwd", "editGoodsPage" };

	public static boolean isUserPage(String url) {
		for (String userPage : userPages) {
			if (url.contains(userPage)) {
				return true;
			}
		}
		return false;
	}
}
