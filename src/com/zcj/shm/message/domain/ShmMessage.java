package com.zcj.shm.message.domain;

import java.util.Calendar;

import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.user.domain.ShmUser;

public class ShmMessage implements java.io.Serializable {

	private static final long serialVersionUID = 133948576486053536L;

	private String id;
	private String type;
	private String title;
	private String content;
	private ShmUser toUser;
	private ShmUser fromUser;
	private ShmGoods toGoods;
	private String readFlag;
	private String delFlag;
	private String memo;
	private String prop1;
	private Calendar addTime;
	private Calendar modifyTime;

	private String publishTime;
	private String toUserName;
	private String fromUserName;
	private String toGoodsId;

	public ShmMessage() {
	}

	public ShmMessage(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ShmUser getToUser() {
		return toUser;
	}

	public void setToUser(ShmUser toUser) {
		this.toUser = toUser;
	}

	public ShmUser getFromUser() {
		return fromUser;
	}

	public void setFromUser(ShmUser fromUser) {
		this.fromUser = fromUser;
	}

	public ShmGoods getToGoods() {
		return toGoods;
	}

	public void setToGoods(ShmGoods toGoods) {
		this.toGoods = toGoods;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
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

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToGoodsId() {
		return toGoodsId;
	}

	public void setToGoodsId(String toGoodsId) {
		this.toGoodsId = toGoodsId;
	}

	@Override
	public String toString() {
		return "ShmMessage [id=" + id + ", type=" + type + ", title=" + title + ", content=" + content + ", readFlag=" + readFlag
				+ ", delFlag=" + delFlag + ", memo=" + memo + ", prop1=" + prop1 + ", addTime=" + addTime + ", publishTime=" + publishTime
				+ ", modifyTime=" + modifyTime + "]";
	}

}
