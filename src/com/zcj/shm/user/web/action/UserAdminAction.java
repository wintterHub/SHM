package com.zcj.shm.user.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcj.shm.base.action.BaseAction;
import com.zcj.shm.code.Excludes;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.ObjectUtil;

@Controller
@Scope("prototype")
public class UserAdminAction extends BaseAction<ShmUser> {

	private static final long serialVersionUID = -6211945332181135465L;

	private String ids;

	public String pageQuery() {
		userService.pageQuery(pageBean);
		List<ShmUser> rows = pageBean.getRows();
		if (ObjectUtil.notEmpty(rows)) {
			for (ShmUser user : rows) {
				if (user.getLastLoginTime() != null) {
					user.setLoginTime(DateUtil.getStrAddTime(user.getLastLoginTime()));
				}
				user.setUserNameCopy(user.getUserName());
			}
		}
		try {
			writeObject2Json(pageBean, Excludes.UserExcludes);
		} catch (IOException e) {
			put("Error", "用户数据请求失败");
		}
		return NONE;
	}

	public String save() {
		boolean checkUNPE = ObjectUtil.checkUNPE(new String[] { "N", "P", "E" }, model);
		if (!checkUNPE) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (userService.nickNameIsExist(model)) {
			write2ajax("{'status':false,'message':'昵称已存在'}");
		} else if (userService.emailIsExist(model)) {
			write2ajax("{'status':false,'message':'邮箱已被注册'}");
		} else if (userService.save(model)) {
			write2ajax("{'status':true,'message':'保存成功'}");
		} else {
			write2ajax("{'status':false,'message':'保存失败'}");
		}
		return NONE;
	}

	public String update() {
		boolean checkUNPE = ObjectUtil.checkUNPE(new String[] { "N", "U", "E" }, model);
		if (!checkUNPE) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (userService.nickNameIsExist(model)) {
			write2ajax("{'status':false,'message':'昵称已存在'}");
		} else if (userService.emailIsExist(model)) {
			write2ajax("{'status':false,'message':'邮箱已被注册'}");
		} else if (userService.update4Admin(model)) {
			write2ajax("{'status':true,'message':'修改成功'}");
		} else {
			write2ajax("{'status':false,'message':'修改失败'}");
		}
		return NONE;
	}

	public String updatePassword() {
		boolean checkUNPE = ObjectUtil.checkUNPE(new String[] { "P", "U", "E" }, model);
		if (!checkUNPE) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (userService.updatePassword(model)) {
			write2ajax("{'status':true,'message':'修改成功'}");
		} else {
			write2ajax("{'status':false,'message':'修改失败'}");
		}
		return NONE;
	}

	public String doStart() {
		if (ids.isEmpty()) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (userService.doStart(ids)) {
			write2ajax("{'status':true,'message':'操作成功'}");
		} else {
			write2ajax("{'status':false,'message':'操作失败'}");
		}
		return NONE;
	}

	public String doStop() {
		if (ids.isEmpty()) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (userService.doStop(ids)) {
			write2ajax("{'status':true,'message':'操作成功'}");
		} else {
			write2ajax("{'status':false,'message':'操作失败'}");
		}
		return NONE;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String goFrame() {
		put("flag", "memberManage");
		return "frame";
	}

}
