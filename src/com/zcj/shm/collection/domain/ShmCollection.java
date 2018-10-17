package com.zcj.shm.collection.domain;

import java.util.Calendar;

public class ShmCollection implements java.io.Serializable {

	private static final long serialVersionUID = 8052195685822946063L;

	private String id;
	private String collGoods;
	private String collUser;
	private String delFlag;
	private String prop1;
	private String memo;
	private Calendar addTime;
	private Calendar modifyTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollGoods() {
		return collGoods;
	}

	public void setCollGoods(String collGoods) {
		this.collGoods = collGoods;
	}

	public String getCollUser() {
		return collUser;
	}

	public void setCollUser(String collUser) {
		this.collUser = collUser;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Calendar getAddTime() {
		return addTime;
	}

	public void setAddTime(Calendar addTime) {
		this.addTime = addTime;
	}

	public Calendar getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Calendar modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "ShmCollection [id=" + id + ", delFlag=" + delFlag + ", prop1=" + prop1 + ", memo=" + memo + ", addTime=" + addTime
				+ ", modifyTime=" + modifyTime + "]";
	}

}
