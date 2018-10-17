package com.zcj.shm.message.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcj.shm.base.action.BaseAction;
import com.zcj.shm.message.domain.ShmMessage;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.ObjectUtil;

@Controller
@Scope("prototype")
public class MessageAction extends BaseAction<ShmMessage> {

	private static final long serialVersionUID = 5504223919801794698L;

	private String toUserStr;
	private String fromUserStr;

	public void setToUserStr(String toUserStr) {
		this.toUserStr = toUserStr;
	}

	public void setFromUserStr(String fromUserStr) {
		this.fromUserStr = fromUserStr;
	}

	public String myMessage() {
		ShmUser user = ((ShmUser) getSession().getAttribute("loginUser"));
		if (ObjectUtil.empty(user)) {
			return "404";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("toUser", user);
		map.put("delFlag", "N");
		Criterion criterion = Restrictions.allEq(map);
		pageBean.getDetachedCriteria().add(criterion);
		messageService.getAllMessage(pageBean);
		List<ShmMessage> messageList = pageBean.getDataRows();
		for (int i = 0; i < messageList.size(); i++) {
			messageList.get(i).setPublishTime(DateUtil.getStrAddTime(messageList.get(i).getAddTime()));// 优化显示发布时间
		}
		writeData(pageBean, null);
		return "myMessage";
	}

	// TODO 发送消息
	public String sendMessage() {
		ShmUser toUserObj = new ShmUser(toUserStr);
		ShmUser fromUserObj = new ShmUser(fromUserStr);
		model.setToUser(toUserObj);
		model.setFromUser(fromUserObj);
		boolean isSend = messageService.sendMessage(model);
		if (isSend) {
			write2ajax("1");
		} else {
			write2ajax("0");
		}
		return NONE;
	}

	public String del() {
		boolean isDelete = messageService.delete(model);
		if (isDelete) {
			write2ajax("1");
		} else {
			write2ajax("0");
		}
		return NONE;

	}

	public String read() {
		boolean isRead = messageService.read(model);
		if (isRead) {
			ShmUser user = ((ShmUser) getSession().getAttribute("loginUser"));
			int messageCount = messageService.getMessageCount(user);
			this.putSession("messageCount", messageCount);
			write2ajax("1");
		} else {
			write2ajax("0");
		}
		return NONE;

	}

	// TODO 获取消息(评论[P]、回复[H]、反馈[F]和通知[T]需要区分)
	// public String getGoodsMessage() {
	// List<ShmMessage> messages = messageService.getMessage(testMessageService.getDemoGoods(), null);
	// for (ShmMessage message : messages) {
	// System.out.println(message);
	// }
	// return null;
	// }

	// public String getUserMessage() {
	// List<ShmMessage> messages = messageService.getMessage(testMessageService.getDemoUser(), null);
	// for (ShmMessage message : messages) {
	// System.out.println(message);
	// }
	// return null;
	// }

	// TODO 获取用户信息条数
	// public String getGoodsMessageCount() {
	// int messageCount = messageService.getMessageCount(testMessageService.getDemoGoods(), null);
	// System.out.println(messageCount);
	// return null;
	// }

	public String getUserMessageCount() {
		ShmUser user = ((ShmUser) getSession().getAttribute("loginUser"));
		if (ObjectUtil.empty(user)) {
			return "404";
		}
		int messageCount = messageService.getMessageCount(user);
		this.putSession("messageCount", messageCount);
		return NONE;
	}

	// public String getAllMessage() {
	// Criterion criterion = Restrictions.eq("toGoods", testMessageService.getDemoGoods().getId());
	// criterion = Restrictions.eq("type", "F");
	// criterion = Restrictions.eq("type", "P");
	// pageBean.getDetachedCriteria().add(criterion);
	// messageService.getAllMessage(pageBean);
	// System.out.println(pageBean.getRowCount());
	// return null;
	// }
}
