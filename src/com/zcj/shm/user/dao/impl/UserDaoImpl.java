package com.zcj.shm.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zcj.shm.base.dao.impl.BaseDaoImpl;
import com.zcj.shm.user.dao.IUserDao;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.DateUtil;

@Repository
public class UserDaoImpl extends BaseDaoImpl<ShmUser> implements IUserDao {

	@Override
	public ShmUser login(ShmUser model) {
		String email = model.getEmail();
		String password = model.getPassword();
		String hql = "from ShmUser u where u.email=? and u.password=?";
		// System.out.println(email + password);
		List<ShmUser> userlist = (List<ShmUser>) this.getHibernateTemplate().find(hql, email, password);
		if (userlist != null && userlist.size() > 0) {
			System.out.println(email + "用户登录成功");
			ShmUser user = userlist.get(0);
			user.setLastLoginIP(model.getLastLoginIP());
			user.setLastLoginTime(DateUtil.getCalendar());
			return user;
		}
		System.out.println("用户登录失败");
		return null;
	}

	@Override
	public ShmUser findUserByEmail(String email) {
		String hql = "from ShmUser u where u.email=?";
		List<ShmUser> userlist = (List<ShmUser>) this.getHibernateTemplate().find(hql, email);
		if (userlist != null && userlist.size() > 0) {
			ShmUser user = userlist.get(0);
			return user;
		}
		return null;
	}

	@Override
	public boolean nickNameIsExist(String nickName, String userName) {
		List<ShmUser> userlist = new ArrayList<ShmUser>();
		if (userName != null) {
			String hql = "from ShmUser u where u.nickName=? and u.userName<>?";
			userlist = (List<ShmUser>) this.getHibernateTemplate().find(hql, nickName, userName);
		} else {
			String hql = "from ShmUser u where u.nickName=?";
			userlist = (List<ShmUser>) this.getHibernateTemplate().find(hql, nickName);
		}
		if (userlist != null && userlist.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean emailIsExist(String email, String userName) {
		List<ShmUser> userlist = new ArrayList<ShmUser>();
		if (userName != null) {
			String hql = "from ShmUser u where u.email=? and u.userName<>?";
			userlist = (List<ShmUser>) this.getHibernateTemplate().find(hql, email, userName);
		} else {
			String hql = "from ShmUser u where u.email=?";
			userlist = (List<ShmUser>) this.getHibernateTemplate().find(hql, email);
		}
		if (userlist != null && userlist.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void saveOrUpdate(ShmUser model) {
		this.getHibernateTemplate().saveOrUpdate(model);
	}

}
