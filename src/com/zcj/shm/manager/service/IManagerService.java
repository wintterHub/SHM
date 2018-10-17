package com.zcj.shm.manager.service;

import com.zcj.shm.manager.domain.ShmManager;
import com.zcj.shm.page.PageBean;

public interface IManagerService {

	ShmManager login(ShmManager model);

	void pageQuery(PageBean<ShmManager> pageBean);

	boolean userNameIsExist(ShmManager model);

	boolean emailIsExist(ShmManager model);

	boolean save(ShmManager model);

	boolean update(ShmManager model);

	boolean doStart(String ids);

	boolean doStop(String ids);

	boolean updatePassword(ShmManager model);

}
