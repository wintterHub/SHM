package com.zcj.shm.code;

public class SortDeal {

	public static final String PRICE = "price";
	public static final String AGE = "age";
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	public static final String TOUSER = "toUser";
	public static final String FROMUSER = "fromUser";
	public static final String PUBLISHER = "publisher";
	public static final String LOGINTIME = "loginTime";
	public static final String LASTLOGINTIME = "lastLoginTime";
	public static final String PUBLISHTIME = "publishTime";
	public static final String ADDTIME = "addTime";
	public static final String TOGOODS = "toGoods";
	public static final String IDCOPY = "idCopy";
	public static final String ID = "id";
	public static final String USERNAMECOPY = "userNameCopy";
	public static final String USERNAME = "userName";

	public static String change(String sortName) {
		if (LOGINTIME.equals(sortName)) {
			return LASTLOGINTIME;
		}
		if (PUBLISHTIME.equals(sortName)) {
			return ADDTIME;
		}
		if (IDCOPY.equals(sortName)) {
			return ID;
		}
		if (USERNAMECOPY.equals(sortName)) {
			return USERNAME;
		}
		return sortName;
	}

	public static boolean isUserSort(String sortName) {
		if (TOUSER.equals(sortName)) {
			return true;
		}
		if (FROMUSER.equals(sortName)) {
			return true;
		}
		if (PUBLISHER.equals(sortName)) {
			return true;
		}
		return false;
	}

	public static boolean isGoodsSort(String sortName) {
		if (TOGOODS.equals(sortName)) {
			return true;
		}
		return false;
	}

}
