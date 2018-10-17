package com.zcj.shm.manager.domain;

import java.util.Calendar;

public class ShmManager implements java.io.Serializable {

	private static final long serialVersionUID = 715311443876146818L;

	private String id;
	private String userName;
	private String password;
	private String email;
	private String delFlag;
	private String mobile;
	private Calendar lastLoginTime;
	private String lastLoginIP;
	private String isSuperAdmin;
	private String prop1;
	private String memo;
	private Calendar addTime;
	private Calendar modifyTime;

	private String loginTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Calendar getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Calendar lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String getIsSuperAdmin() {
		return isSuperAdmin;
	}

	public void setIsSuperAdmin(String isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
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

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "ShmManager [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email + ", delFlag=" + delFlag
				+ ", mobile=" + mobile + ", lastLoginIP=" + lastLoginIP + ", isSuperAdmin=" + isSuperAdmin + ", prop1=" + prop1 + ", memo="
				+ memo + ", loginTime=" + loginTime + "]";
	}

}