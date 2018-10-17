package com.zcj.shm.user.domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.message.domain.ShmMessage;

public class ShmUser implements java.io.Serializable {

	private static final long serialVersionUID = 5505307118173110361L;

	private String userName;
	private String password;
	private String nickName;
	private String studentID;
	private String email;
	private String gender;
	private Integer age;
	private Integer point;
	private String headImagePath;
	private String qqNumber;
	private String address;
	private String department;
	private String delFlag;
	private String authenticationStatus;
	private String mobile;
	private Calendar lastLoginTime;
	private String lastLoginIP;
	private String prop1;
	private String memo;
	private Calendar addTime;
	private Calendar modifyTime;
	private Integer loginErrorCount;

	private Set<ShmGoods> publishGoods = new HashSet<ShmGoods>(0);
	// private List<ShmGoods> collGoods = new ArrayList<ShmGoods>(0);
	private Set<ShmMessage> sendMessages = new HashSet<ShmMessage>(0);
	private Set<ShmMessage> receiveMessages = new HashSet<ShmMessage>(0);

	private String loginTime;
	private String userNameCopy;

	public ShmUser() {
	}

	public ShmUser(String userName) {
		this.userName = userName;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getHeadImagePath() {
		return headImagePath;
	}

	public void setHeadImagePath(String headImagePath) {
		this.headImagePath = headImagePath;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getAuthenticationStatus() {
		return authenticationStatus;
	}

	public void setAuthenticationStatus(String authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
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

	public Integer getLoginErrorCount() {
		return loginErrorCount;
	}

	public void setLoginErrorCount(Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	public Set<ShmGoods> getPublishGoods() {
		return publishGoods;
	}

	public void setPublishGoods(Set<ShmGoods> publishGoods) {
		this.publishGoods = publishGoods;
	}

	// public List<ShmGoods> getCollGoods() {
	// return collGoods;
	// }
	//
	// public void setCollGoods(List<ShmGoods> collGoods) {
	// this.collGoods = collGoods;
	// }

	public Set<ShmMessage> getSendMessages() {
		return sendMessages;
	}

	public void setSendMessages(Set<ShmMessage> sendMessages) {
		this.sendMessages = sendMessages;
	}

	public Set<ShmMessage> getReceiveMessages() {
		return receiveMessages;
	}

	public void setReceiveMessages(Set<ShmMessage> receiveMessages) {
		this.receiveMessages = receiveMessages;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getUserNameCopy() {
		return userNameCopy;
	}

	public void setUserNameCopy(String userNameCopy) {
		this.userNameCopy = userNameCopy;
	}

	@Override
	public String toString() {
		return "ShmUser [userName=" + userName + ", password=" + password + ", nickName=" + nickName + ", studentID=" + studentID
				+ ", email=" + email + ", gender=" + gender + ", age=" + age + ", point=" + point + ", headImagePath=" + headImagePath
				+ ", qqNumber=" + qqNumber + ", address=" + address + ", department=" + department + ", delFlag=" + delFlag
				+ ", authenticationStatus=" + authenticationStatus + ", mobile=" + mobile + ", lastLoginTime=" + lastLoginTime
				+ ", lastLoginIP=" + lastLoginIP + ", prop1=" + prop1 + ", memo=" + memo + ", addTime=" + addTime + ", modifyTime="
				+ modifyTime + ", loginErrorCount=" + loginErrorCount + "]";
	}

}
