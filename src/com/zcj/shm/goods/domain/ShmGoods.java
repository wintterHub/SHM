package com.zcj.shm.goods.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.zcj.shm.message.domain.ShmMessage;
import com.zcj.shm.user.domain.ShmUser;

public class ShmGoods implements java.io.Serializable {

	private static final long serialVersionUID = -5247562573682069455L;

	private String id;
	private ShmUser publisher;
	private Float price;
	private String categroy;
	private String title;
	private String imagePath;
	private Integer amount;
	private String loco;
	private String chafferFlag;
	private String detailed;
	private String saleFlag;
	private String delFlag;
	private String prop1;
	private String memo;
	private Calendar dueTime;
	private Calendar addTime;
	private Calendar modifyTime;
	private Integer viewCount;

	private List<ShmMessage> messages = new ArrayList<ShmMessage>();
	// private Set<ShmUser> CollUsers = new HashSet<ShmUser>();

	private String[] images = new String[3];
	private String publishTime;
	private String collectionId;
	private int collectionCount;
	private String publishUser;
	private String publishUserId;
	private String idCopy;

	public ShmGoods() {

	}

	public ShmGoods(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ShmUser getPublisher() {
		return publisher;
	}

	public void setPublisher(ShmUser publisher) {
		this.publisher = publisher;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCategroy() {
		return categroy;
	}

	public void setCategroy(String categroy) {
		this.categroy = categroy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getLoco() {
		return loco;
	}

	public void setLoco(String loco) {
		this.loco = loco;
	}

	public String getChafferFlag() {
		return chafferFlag;
	}

	public void setChafferFlag(String chafferFlag) {
		this.chafferFlag = chafferFlag;
	}

	public String getDetailed() {
		return detailed;
	}

	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}

	public String getSaleFlag() {
		return saleFlag;
	}

	public void setSaleFlag(String saleFlag) {
		this.saleFlag = saleFlag;
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

	public Calendar getDueTime() {
		return dueTime;
	}

	public void setDueTime(Calendar dueTime) {
		this.dueTime = dueTime;
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

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public List<ShmMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ShmMessage> messages) {
		this.messages = messages;
	}

	// public Set<ShmUser> getCollUsers() {
	// return CollUsers;
	// }
	//
	// public void setCollUsers(Set<ShmUser> collUsers) {
	// CollUsers = collUsers;
	// }

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public int getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(int collectionCount) {
		this.collectionCount = collectionCount;
	}

	public String getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}

	public String getPublishUserId() {
		return publishUserId;
	}

	public void setPublishUserId(String publishUserId) {
		this.publishUserId = publishUserId;
	}

	public String getIdCopy() {
		return idCopy;
	}

	public void setIdCopy(String idCopy) {
		this.idCopy = idCopy;
	}

	@Override
	public String toString() {
		return "ShmGoods [id=" + id + ", price=" + price + ", categroy=" + categroy + ", title=" + title + ", imagePath=" + imagePath
				+ ", amount=" + amount + ", loco=" + loco + ", chafferFlag=" + chafferFlag + ", detailed=" + detailed + ", saleFlag="
				+ saleFlag + ", delFlag=" + delFlag + ", prop1=" + prop1 + ", memo=" + memo + ", viewCount=" + viewCount + ", publishTime="
				+ publishTime + ",collectionId=" + collectionId + ",collectionCount=" + collectionCount + ",publishUser=" + publishUser
				+ ",publishUserId=" + publishUserId + "]";
	}

}
