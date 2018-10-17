package com.zcj.shm.manager.dao;

import com.zcj.shm.base.dao.IBaseDao;
import com.zcj.shm.manager.domain.ShmManager;

public interface IManagerDao extends IBaseDao<ShmManager> {

	ShmManager login(ShmManager model);

	boolean userNameIsExist(String id, String userName);

	boolean emailIsExist(String id, String email);

}
