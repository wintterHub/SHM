package com.zcj.shm.user.web.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Cookie;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcj.shm.base.action.BaseAction;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.IOUtil;
import com.zcj.shm.util.IpUtil;
import com.zcj.shm.util.ObjectUtil;
import com.zcj.shm.util.PropertiesLoad;
import com.zcj.shm.util.SendMailServise;
import com.zcj.shm.util.StringUtil;
import com.zcj.shm.util.UrlUtil;
import com.zcj.shm.util.VerifyUtil;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<ShmUser> {

	private static final long serialVersionUID = 2819619090101780991L;
	private String vcode;
	private String goodsId;
	private File headImage;
	private String headImageFileName;
	private String headImageContentType;
	private String backUrl;
	private String rememberMe;

	/**
	 * 请求分页
	 * 
	 * @return
	 * @throws IOException
	 */
	public String pageQuery() throws IOException {
		userService.pageQuery(pageBean);
		this.writeObject2Json(pageBean, new String[] {});
		return "success";
	}

	/*
	 * 跳转到登录页面
	 */
	public String goToLogin() {
		if (backUrl != null) {
			put("backUrl", backUrl);
		} else {
			String referer = getRequest().getHeader("Referer");
			put("backUrl", StringUtil.encode2Utf8(referer));
		}
		return "login";
	}

	/*
	 * 跳转到注册页面
	 */
	public String goToRegister() {
		if (backUrl != null) {
			put("backUrl", backUrl);
		} else {
			String referer = getRequest().getHeader("Referer");
			put("backUrl", StringUtil.encode2Utf8(referer));
		}
		return "register";
	}

	/*
	 * 跳转到忘记密码页面
	 */
	public String goToForget() {
		if (backUrl != null) {
			put("backUrl", backUrl);
			return "forget";
		} else {
			String referer = getRequest().getHeader("Referer");
			put("backUrl", StringUtil.encode2Utf8(referer));
			return "forget";
		}
	}

	/*
	 * 跳转到注销
	 */
	// public String goToLogout(){
	// if (backUrl != null) {
	// put("backUrl", backUrl);
	// return "logout";
	// } else {
	// String referer = getRequest().getHeader("Referer");
	// put("backUrl", StringUtil.encode2Utf8(referer));
	// return "logout";
	// }
	// }

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public String login() {
		if ("on".equals(rememberMe)) {// 保存cookie
			String md5Password = StringUtil.getMD5Password(model.getPassword(), model.getEmail());
			Cookie cookie = new Cookie("SHM_User", model.getEmail() + "," + md5Password);
			cookie.setMaxAge(3600 * 24 * 10);
			cookie.setPath("/SHM");
			getResponse().addCookie(cookie);
		}
		String ip = IpUtil.getIp(getRequest());
		model.setLastLoginIP(ip);
		ShmUser user = userService.login(model);
		if (user == null) {
			this.put("loginerror", "用户名或密码错误");
			return "login";
		} else if ("Y".equalsIgnoreCase(user.getDelFlag())) {
			this.put("loginerror", "该账号已被禁用，请联系管理员");
			return "login";
		} else {
			this.putSession("loginUser", user);
			int messageCount = messageService.getMessageCount(user);
			this.putSession("messageCount", messageCount);
			this.getRequest().setAttribute("backUrl", backUrl);
			return "backUrl";
		}
	}

	public String cookieLogin() {
		Cookie[] cookies = getRequest().getCookies();
		String userCookie = null;
		for (Cookie cookie : cookies) {
			if ("SHM_User".equals(cookie.getName())) {
				userCookie = cookie.getValue();
				break;
			}
		}
		if (ObjectUtil.notEmpty(userCookie)) {
			String[] userCookies = userCookie.split(",");
			ShmUser user = new ShmUser();
			user.setEmail(userCookies[0]);
			user.setPassword(userCookies[1]);
			ShmUser loginUser = userService.cookieLogin(user);
			if (loginUser != null) {
				this.putSession("loginUser", loginUser);
				int messageCount = messageService.getMessageCount(loginUser);
				this.putSession("messageCount", messageCount);
				write2ajax("1");

				Cookie cookie = new Cookie("SHM_isLogin", "true");
				cookie.setPath("/SHM");
				getResponse().addCookie(cookie);
			}
		}
		return NONE;
	}

	public String logout() {
		if (backUrl != null) {
			this.getRequest().setAttribute("backUrl", backUrl);
		} else {
			String referer = getRequest().getHeader("Referer");
			boolean isUserPage = UrlUtil.isUserPage(referer);// 判断当前是否为用户私有界面
			if (isUserPage) {
				referer = "/";// 跳到首页
			}
			this.getRequest().setAttribute("backUrl", referer);
		}
		Cookie cookie = new Cookie("SHM_User", null);
		cookie.setMaxAge(0);
		cookie.setPath("/SHM");
		getResponse().addCookie(cookie);
		getSession().removeAttribute("loginUser");
		return "backUrl";
	}

	public String sendmail() {
		String email = model.getEmail();
		boolean isSend = true;
		boolean isEmail = VerifyUtil.isEmail(email);
		// System.out.println(isEmail);
		if (isEmail) {
			String emialVcode = VerifyUtil.getVcode(email);
			System.out.println("emialVcode=" + emialVcode);
			if ("true".equals(PropertiesLoad.getSendMail())) {
				isSend = SendMailServise.send("SHM验证码", "您的校园二手市场验证码是：" + emialVcode, email, PropertiesLoad.getEmailAccount(),
						PropertiesLoad.getEmailPassword(), PropertiesLoad.getEmailSMTP());
			}
			write2ajax(isSend ? "1" : "0");
		}
		return NONE;
	}

	public String register() {
		boolean userNotEmpty = ObjectUtil.checkUNPE(new String[] { "N", "P", "E" }, model);
		if (userNotEmpty) {
			String email = model.getEmail();
			model.setLastLoginIP(IpUtil.getIp(getRequest()));
			boolean isVcode = VerifyUtil.isVcode(email, vcode);
			boolean isEmail = VerifyUtil.isEmail(email);
			boolean notExist = userService.notExist(model);
			if (isEmail && isVcode && notExist) {
				ShmUser user = userService.register(model);
				if (user != null) {
					this.putSession("loginUser", user);
					messageService.sendWelcomeMessage(user);
					int messageCount = messageService.getMessageCount(user);
					this.putSession("messageCount", messageCount);
					put("backUrl", backUrl);
					return "success";
				}
			}
		}
		this.put("registererror", "注册失败");
		return "register";
	}

	public String findUserByName() {
		ShmUser user = userService.findUserByName(model.getUserName());
		if (user != null) {
			write2ajax("{\"valid\":false}");
		} else {
			write2ajax("{\"valid\":true}");
		}
		return NONE;
	}

	public String findUserByName2() {
		System.out.println(StringUtil.getNewString(model.getUserName()));
		ShmUser user = userService.findUserByName(StringUtil.getNewString(model.getUserName()));
		if (user != null) {
			put("userInfo", user);
			return "userInfoCard";
		} else {
			return "404";
		}

	}

	public String findUserByEmail() {
		ShmUser user = userService.findUserByEmail(model.getEmail());
		if (user != null) {
			write2ajax("{\"valid\":false}");
		} else {
			write2ajax("{\"valid\":true}");
		}
		return NONE;
	}

	public String findUserByEmail2() {
		ShmUser user = userService.findUserByEmail(model.getEmail());
		if (user == null) {
			write2ajax("{\"valid\":false}");
		} else {
			write2ajax("{\"valid\":true}");
		}
		return NONE;
	}

	public String nickNameIsExist() {
		boolean isExist = userService.nickNameIsExist(model);
		if (isExist) {
			write2ajax("{\"valid\":false}");
		} else {
			write2ajax("{\"valid\":true}");
		}
		return NONE;
	}

	public String checkVcode() {
		// System.out.println("vcode=" + vcode);
		if (VerifyUtil.isVcode(model.getEmail(), vcode)) {
			write2ajax("{\"valid\":true}");
		} else {
			write2ajax("{\"valid\":false}");
		}
		return NONE;
	}

	public String forget() {
		String email = model.getEmail();
		boolean isSend = true;
		boolean isVcode = VerifyUtil.isVcode(email, vcode);
		boolean isEmail = VerifyUtil.isEmail(email);
		if (isEmail && isVcode) {

			String randomPwd = StringUtil.getRandomPwd();
			System.out.println("randomPwd=" + randomPwd);
			// TODO 此处需要去数据库修改密码
			model.setPassword(randomPwd);
			boolean isResetPwd = userService.resetPwd(model);
			if ("true".equals(PropertiesLoad.getSendMail())) {
				isSend = SendMailServise.send("您的SHM系统密码已重置", "随机密码为：" + randomPwd, email, PropertiesLoad.getEmailAccount(),
						PropertiesLoad.getEmailPassword(), PropertiesLoad.getEmailSMTP());
			}
			if (isSend && isResetPwd) {
				put("backUrl", backUrl);
				return "success";
			}
		}
		this.put("forgeterror", "重置失败");
		return "forget";

	}

	public String saveSchool() {
		ShmUser user = (ShmUser) getSession().getAttribute("loginUser");
		user.setAddress(model.getAddress());
		boolean isUpdate = userService.update(user);
		if (isUpdate) {
			write2ajax("1");
		} else {
			write2ajax("0");
		}
		return NONE;
	}

	public String update() {
		if (ObjectUtil.empty(getSession().getAttribute("loginUser"))) {
			return "404";
		} else if (ObjectUtil.empty(model.getUserName())) {
			return "myInfo";
		}
		ShmUser user = (ShmUser) getSession().getAttribute("loginUser");
		user = (ShmUser) ObjectUtil.coverPojo(user, model);
		boolean isUpdate1 = userService.update(user);

		if (isUpdate1) {
			// 更新成功
			getSession().setAttribute("loginUser", user);
			put("isUpdate", "1");
			return "myInfo";
		} else {
			// 更新失败
			put("isUpdate", "0");
			return "myInfo";
		}
	}

	public String myInfo() {
		if (ObjectUtil.empty(getSession().getAttribute("loginUser"))) {
			return "404";
		}
		return "myInfo";
	}

	public String uploadHeadImage() {
		System.out.println("执行uploadHeadImage");
		String filePath1 = PropertiesLoad.getImageDir();
		String filePath2 = "/image/head";
		String newName = StringUtil.getNewName(headImageFileName);
		File newFile = new File(filePath1 + filePath2, newName);
		if (!new File(filePath1 + filePath2).exists()) {
			new File(filePath1 + filePath2).mkdirs();
		}
		if (!VerifyUtil.isImage(headImageContentType)) {
			write2ajax("-1");// 不是图片
			return NONE;
		} else {
			try {
				IOUtil.copy(headImage, newFile);// 保存原始图片
				IOUtil.imageCut(headImage, filePath1 + filePath2, "cut_" + newName);// 裁剪并保存图片
			} catch (Exception e) {
				write2ajax("0");// 保存失败
				return NONE;
			}
			String cutImagePath = filePath2 + "/cut_" + newName;
			ShmUser user = (ShmUser) getSession().getAttribute("loginUser");
			user.setHeadImagePath(cutImagePath);
			userService.update(user);
			write2ajax("1");// 成功
		}
		return NONE;
	}

	public String updatePassword() {
		ShmUser user = (ShmUser) getSession().getAttribute("loginUser");
		if (ObjectUtil.empty(user)) {
			return "404";
		}
		user.setPassword(model.getPassword());
		ShmUser login = userService.login(user);
		if (login != null) {
			// String md5Password = StringUtil.getMD5Password(model.getProp1(), user.getEmail());
			// user.setPassword(md5Password);
			user.setPassword(model.getProp1());
			boolean isUpdate = userService.updatePassword(user);
			if (isUpdate) {
				this.put("changeok", "1");
				return "myInfo";
			} else {
				this.put("changeerror", "修改失败");
				return "changePwd";
			}
		} else {
			this.put("changeerror", "原始密码错误");
			return "changePwd";
		}
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public File getHeadImage() {
		return headImage;
	}

	public void setHeadImage(File headImage) {
		this.headImage = headImage;
	}

	public String getHeadImageFileName() {
		return headImageFileName;
	}

	public void setHeadImageFileName(String headImageFileName) {
		this.headImageFileName = headImageFileName;
	}

	public String getHeadImageContentType() {
		return headImageContentType;
	}

	public void setHeadImageContentType(String headImageContentType) {
		this.headImageContentType = headImageContentType;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

}
