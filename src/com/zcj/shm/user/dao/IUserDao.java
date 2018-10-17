package com.zcj.shm.user.dao;

import com.zcj.shm.base.dao.IBaseDao;
import com.zcj.shm.user.domain.ShmUser;

public interface IUserDao extends IBaseDao<ShmUser> {

	ShmUser findUserByEmail(String email);

	ShmUser login(ShmUser model);

	// ShmUser findUserByNickName(String nickName, String email);

	void saveOrUpdate(ShmUser model);

	boolean nickNameIsExist(String nickName, String userName);

	boolean emailIsExist(String email, String userName);

}