package com.zcj.shm.message.web.action;

import java.io.IOException;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcj.shm.base.action.BaseAction;
import com.zcj.shm.code.Excludes;
import com.zcj.shm.message.domain.ShmMessage;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.ObjectUtil;

@Controller
@Scope("prototype")
public class MessageAdminAction extends BaseAction<ShmMessage> {

	private static final long serialVersionUID = 1595405045401681701L;

	private String ids;

	public String sendMessage() {
		boolean isAllSend = messageService.batchSendMessage(ids, model);
		if (isAllSend) {
			write2ajax("{'status':true,'message':'操作成功'}");
		} else {
			write2ajax("{'status':false,'message':'操作失败'}");
		}
		return NONE;
	}

	public String pageQuery() {
		Criterion criterion = Restrictions.or(Restrictions.eq("type", "P"), Restrictions.eq("type", "H"));
		pageBean.getDetachedCriteria().add(criterion);
		messageService.pageQuery(pageBean);
		List<ShmMessage> rows = pageBean.getRows();
		if (ObjectUtil.notEmpty(rows)) {
			for (ShmMessage messages : rows) {
				if (messages.getAddTime() != null) {
					messages.setPublishTime(DateUtil.getStrAddTime(messages.getAddTime()));
				}
				messages.setToUserName(messages.getToUser().getNickName());
				messages.setFromUserName(messages.getFromUser().getNickName());
				messages.setToGoodsId(messages.getToGoods().getId());
			}
		}
		try {
			writeObject2Json(pageBean, Excludes.MessageExcludes);
		} catch (IOException e) {
			put("Error", "评论数据请求失败");
		}
		return NONE;
	}

	public String doDelete() {
		if (ids.isEmpty()) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (messageService.delete(ids)) {
			write2ajax("{'status':true,'message':'操作成功'}");
		} else {
			write2ajax("{'status':false,'message':'操作失败'}");
		}
		return NONE;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
