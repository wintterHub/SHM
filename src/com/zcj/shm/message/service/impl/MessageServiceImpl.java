package com.zcj.shm.message.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.message.dao.IMessageDao;
import com.zcj.shm.message.domain.ShmMessage;
import com.zcj.shm.message.service.IMessageService;
import com.zcj.shm.page.PageBean;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.ObjectUtil;

@Service
@Transactional
public class MessageServiceImpl implements IMessageService {

	@Autowired
	private IMessageDao messageDao;

	@Override
	public void getAllMessage(PageBean<ShmMessage> pageBean) {
		messageDao.findAll(pageBean);
	}

	@Override
	public boolean sendMessage(ShmMessage message) {
		try {
			if ("H".equalsIgnoreCase(message.getType()) || "P".equalsIgnoreCase(message.getType())) {
				ShmGoods goods = new ShmGoods(message.getProp1());
				message.setToGoods(goods);
				message.setProp1(null);
			}
			message.setDelFlag("N");
			message.setReadFlag("N");
			message.setAddTime(DateUtil.getCalendar());
			messageDao.save(message);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(ShmMessage model) {
		try {
			messageDao.update("update ShmMessage set delFlag=? where id=?", new Object[] { "Y", model.getId() });
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ShmMessage> getMessage(ShmGoods goods, PageBean<ShmMessage> pageBean) {
		return getAllMessage(goods, pageBean);
	}

	private List<ShmMessage> getAllMessage(Object obj, PageBean<ShmMessage> pageBean) {
		if (ObjectUtil.empty(obj)) {
			return null;
		}
		Object param = "";
		String condition = "";
		if (obj instanceof ShmUser) {
			param = (ShmUser) obj;
			condition = "and toUser=? and delFlag=? order by addTime desc";
		} else if (obj instanceof ShmGoods) {
			param = (ShmGoods) obj;
			condition = "and toGoods=? and delFlag=? order by addTime desc";
		}
		List<ShmMessage> msgList = new ArrayList<ShmMessage>();
		Object[] objects = new Object[] { param, "N" };
		if (ObjectUtil.notEmpty(pageBean)) {
			int pageSize = pageBean.getPageSize();
			int startIndex = (pageBean.getCurrentPage() - 1) * pageSize;
			if (pageSize > 0 && startIndex >= 0) {
				msgList = messageDao.findAll(condition, objects, startIndex, pageSize);
			} else {
				return null;
			}
		} else {
			msgList = messageDao.findAll(condition, objects);
		}
		for (int i = 0; i < msgList.size(); i++) {
			msgList.get(i).setPublishTime(DateUtil.getStrAddTime(msgList.get(i).getAddTime()));// 将时间参数优化
		}
		return msgList;
	}

	@Override
	public boolean read(ShmMessage model) {
		try {
			messageDao.update("update ShmMessage set readFlag=? where id=?", new Object[] { "Y", model.getId() });
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//
	// @Override
	// public List<ShmMessage> getMessage(ShmUser user, PageBean<ShmMessage> pageBean) {
	// return getAllMessage(user, pageBean);
	//
	// }
	//
	@Override
	public int getMessageCount(ShmUser user) {
		return messageDao.getTotalRecord("and toUser=? and readFlag=?", new Object[] { user, "N" });
	}
	//
	// @Override
	// public int getMessageCount(ShmGoods goods, PageBean<ShmMessage> pageBean) {
	// return getAllMessage(goods, pageBean).size();
	// }

	@Override
	public void sendWelcomeMessage(ShmUser user) {
		ShmMessage message = new ShmMessage();
		message.setTitle("系统消息");
		message.setType("T");// 系统通知类型
		message.setToUser(user);
		message.setFromUser(new ShmUser("297ef6ec5af48fea015af4904f840000"));
		message.setContent("欢迎你：" + user.getNickName() + " ,感谢您注册校园二手商城账号");
		message.setDelFlag("N");
		message.setReadFlag("N");
		message.setAddTime(DateUtil.getCalendar());
		messageDao.save(message);
	}

	@Override
	public boolean batchSendMessage(String ids, ShmMessage model) {
		String[] IDs = ids.split(",");
		try {
			for (String id : IDs) {
				ShmMessage message = new ShmMessage();
				message.setTitle("系统消息");
				message.setType("T");// 系统通知类型
				message.setToUser(new ShmUser(id));
				message.setFromUser(new ShmUser("297ef6ec5af48fea015af4904f840000"));
				message.setContent(model.getContent());
				message.setDelFlag("N");
				message.setReadFlag("N");
				message.setAddTime(DateUtil.getCalendar());
				messageDao.save(message);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void pageQuery(PageBean<ShmMessage> pageBean) {
		messageDao.findAll(pageBean);
	}

	@Override
	public boolean delete(String ids) {
		String[] IDs = ids.split(",");
		try {
			for (String id : IDs) {
				messageDao.update("delete ShmMessage where id=?", new Object[] { id });
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
