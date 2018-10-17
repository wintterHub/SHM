package com.zcj.shm.manager.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcj.shm.base.action.BaseAction;
import com.zcj.shm.code.Excludes;
import com.zcj.shm.manager.domain.ShmManager;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.IpUtil;
import com.zcj.shm.util.ObjectUtil;

@Controller
@Scope("prototype")
public class ManagerAction extends BaseAction<ShmManager> {

	private static final long serialVersionUID = 1386445238852154441L;

	private String flag;
	private String ids;

	public String navJump() {
		ShmManager loginManager = (ShmManager) getSession().getAttribute("loginManager");
		if (loginManager == null) {
			return "adminLogin";
		}
		// System.out.println(flag);
		put("flag", flag);
		return "frame";
	}

	public String login() {
		if (ObjectUtil.empty(model.getEmail()) || ObjectUtil.empty(model.getPassword())) {
			return "500";
		}

		String ip = IpUtil.getIp(getRequest());
		model.setLastLoginIP(ip);
		ShmManager manager = managerService.login(model);
		if (manager == null) {
			this.put("loginerror", "用户名或密码错误");
			return "adminLogin";
		} else if ("Y".equalsIgnoreCase(manager.getDelFlag())) {
			this.put("loginerror", "该账号已被禁用，请联系超级管理员");
			return "adminLogin";
		} else {
			this.putSession("loginManager", manager);
			return "adminHome";
		}
	}

	public String logout() {
		getSession().removeAttribute("loginManager");
		return "adminLogin";
	}

	public String pageQuery() {
		managerService.pageQuery(pageBean);
		List<ShmManager> rows = pageBean.getRows();
		if (ObjectUtil.notEmpty(rows)) {
			for (ShmManager manager : rows) {
				if (manager.getLastLoginTime() != null) {
					manager.setLoginTime(DateUtil.getStrAddTime(manager.getLastLoginTime()));
				}
			}
		}
		try {
			writeObject2Json(pageBean, Excludes.ManagerExcludes);
		} catch (IOException e) {
			put("Error", "用户数据请求失败");
		}
		return NONE;
	}

	public String save() {
		boolean checkUNPE = ObjectUtil.checkUNPE(new String[] { "U", "P", "E" }, model);
		if (!checkUNPE) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (managerService.userNameIsExist(model)) {
			write2ajax("{'status':false,'message':'用户名已存在'}");
		} else if (managerService.emailIsExist(model)) {
			write2ajax("{'status':false,'message':'邮箱已被注册'}");
		} else if (managerService.save(model)) {
			write2ajax("{'status':true,'message':'保存成功'}");
		} else {
			write2ajax("{'status':false,'message':'保存失败'}");
		}
		return NONE;
	}

	public String update() {
		boolean checkUNPE = ObjectUtil.checkUNPE(new String[] { "I", "U", "E" }, model);
		if (!checkUNPE) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (managerService.userNameIsExist(model)) {
			write2ajax("{'status':false,'message':'昵称已存在'}");
		} else if (managerService.emailIsExist(model)) {
			write2ajax("{'status':false,'message':'邮箱已被注册'}");
		} else if (managerService.update(model)) {
			write2ajax("{'status':true,'message':'修改成功'}");
		} else {
			write2ajax("{'status':false,'message':'修改失败'}");
		}
		return NONE;
	}

	public String doStart() {
		if (ids.isEmpty()) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (managerService.doStart(ids)) {
			write2ajax("{'status':true,'message':'操作成功'}");
		} else {
			write2ajax("{'status':false,'message':'操作失败'}");
		}
		return NONE;
	}

	public String doStop() {
		if (ids.isEmpty()) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (managerService.doStop(ids)) {
			write2ajax("{'status':true,'message':'操作成功'}");
		} else {
			write2ajax("{'status':false,'message':'操作失败'}");
		}
		return NONE;
	}

	public String updatePassword() {
		boolean checkUNPE = ObjectUtil.checkUNPE(new String[] { "I", "P", "E" }, model);
		if (!checkUNPE) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (managerService.updatePassword(model)) {
			write2ajax("{'status':true,'message':'修改成功'}");
		} else {
			write2ajax("{'status':false,'message':'修改失败'}");
		}
		return NONE;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
