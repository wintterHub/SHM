package com.zcj.shm.test;

import org.junit.Test;

import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.message.domain.ShmMessage;
import com.zcj.shm.message.service.impl.MessageServiceImpl;
import com.zcj.shm.page.PageBean;
import com.zcj.shm.user.domain.ShmUser;

public class testMessageService {

	public static ShmUser getDemoUser() {
		ShmUser user = new ShmUser();
		user.setUserName("zhaochangjin");
		user.setEmail("1505322835@qq.com");
		user.setMobile("17096701945");
		user.setQqNumber("1505322835");
		return user;
	}

	public static PageBean<ShmMessage> getDemoPageBean() {
		PageBean<ShmMessage> pageBean = new PageBean<ShmMessage>();
		pageBean.setCurrentPage(1);
		pageBean.setPageSize(10);
		return pageBean;
	}

	public static ShmMessage getDemoMessage() {
		ShmMessage message = new ShmMessage();
		message.setContent("这是消息内容");
//		message.setToUser("赵长进");
//		message.setFromUser("zhaochangjin");
		// message.setType("P");
		 message.setType("H");
		// message.setType("F");
//		message.setType("T");
		return message;

	}

	public static ShmGoods getDemoGoods() {
		ShmGoods goods = new ShmGoods();
		goods.setId("297ef6ec5a99b553015a99b5d88b0000");
		// goods.setTitle("");
		// goods.setImagePath("");
		// goods.setPublisher(getDemoUser());
		// goods.setDetailed("");
		// goods.setCategroy("");
		// goods.setChafferFlag("");
		// goods.setLoco("");
		// goods.setPrice(999f);
		// goods.set
		return goods;
	}

	@Test
	public void test1() {
		MessageServiceImpl messageService = new MessageServiceImpl();
		boolean sendMessage = messageService.sendMessage(getDemoMessage());
		System.out.println(sendMessage);
	}

}
