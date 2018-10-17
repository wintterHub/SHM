package com.zcj.shm.user.service;

import java.util.List;

import com.zcj.shm.page.PageBean;
import com.zcj.shm.user.domain.ShmUser;

public interface IUserService {

	boolean save(ShmUser user);

	ShmUser login(ShmUser model);

	void pageQuery(PageBean<ShmUser> pageBean);

	void delete(String loginName);

	boolean update(ShmUser user);

	boolean updatePassword(ShmUser user);

	ShmUser register(ShmUser model);

	ShmUser findUserByName(String username);

	ShmUser findUserByEmail(String email);

	boolean resetPwd(ShmUser model);

	boolean updateUserName(String userName, String email);

	// ShmUser findUserByNickName(ShmUser model);

	// boolean saveOrUpdate(ShmUser model);

	boolean notExist(ShmUser model);

	boolean nickNameIsExist(ShmUser model);

	boolean emailIsExist(ShmUser model);

	boolean update4Admin(ShmUser user);

	boolean doStart(String ids);

	boolean doStop(String ids);

	boolean haveMobileOrQQ(ShmUser user);

	ShmUser cookieLogin(ShmUser model);

	ShmUser findUserByNickName(String nickName);

}
