package com.zcj.shm.code;

public class Excludes {

	public static final String[] ManagerExcludes = { "password", "lastLoginTime", "addTime", "modifyTime" };
	public static final String[] UserExcludes = { "password", "authenticationStatus", "addTime", "lastLoginTime", "modifyTime",
			"loginErrorCount", "publishGoods", "sendMessages", "receiveMessages", "detachedCriteria", "rowCount", "pageCount", "dataRows",
			"currentPage", "pageSize" };
	public static final String[] GoodsExcludes = { "publisher", "amount", "saleFlag", "dueTime", "addTime", "modifyTime", "messages",
			"collectionId", "detachedCriteria", "rowCount", "pageCount", "dataRows", "currentPage", "pageSize" };
	public static final String[] MessageExcludes = { "title", "toUser", "fromUser", "toGoods", "readFlag", "delFlag", "modifyTime",
			"addTime" };
}
