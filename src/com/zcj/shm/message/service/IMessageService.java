package com.zcj.shm.message.service;

import java.util.List;

import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.message.domain.ShmMessage;
import com.zcj.shm.page.PageBean;
import com.zcj.shm.user.domain.ShmUser;

public interface IMessageService {

	void getAllMessage(PageBean<ShmMessage> pageBean);

	boolean sendMessage(ShmMessage message);

	boolean delete(ShmMessage model);

	boolean read(ShmMessage model);

	/**
	 * 获取用户消息
	 * 
	 * @param user
	 * @param pageBean 为空时不分页
	 * @return
	 */
	// List<ShmMessage> getMessage(ShmUser user, PageBean<ShmMessage> pageBean);

	/**
	 * 获取商品评论
	 * 
	 * @param toGoods
	 * @param pageBean
	 * @return
	 */
	// @Deprecated
	List<ShmMessage> getMessage(ShmGoods goods, PageBean<ShmMessage> pageBean);

	/**
	 * 用户消息计数
	 * 
	 * @param user
	 * @return
	 */
	int getMessageCount(ShmUser user);

	void sendWelcomeMessage(ShmUser user);

	boolean batchSendMessage(String ids, ShmMessage model);

	void pageQuery(PageBean<ShmMessage> pageBean);

	boolean delete(String ids);

	/**
	 * 商品消息计数
	 * 
	 * @param user
	 * @return
	 */
	// int getMessageCount(ShmGoods goods, PageBean<ShmMessage> pageBean);

}
