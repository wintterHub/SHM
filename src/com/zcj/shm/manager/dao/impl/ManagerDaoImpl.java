package com.zcj.shm.manager.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zcj.shm.base.dao.impl.BaseDaoImpl;
import com.zcj.shm.manager.dao.IManagerDao;
import com.zcj.shm.manager.domain.ShmManager;
import com.zcj.shm.util.DateUtil;

@Repository
public class ManagerDaoImpl extends BaseDaoImpl<ShmManager> implements IManagerDao {

	@Override
	public ShmManager login(ShmManager model) {
		String email = model.getEmail();
		String password = model.getPassword();
		String hql = "from ShmManager u where u.email=? and u.password=?";
		List<ShmManager> managerlist = (List<ShmManager>) this.getHibernateTemplate().find(hql, email, password);
		if (managerlist != null && managerlist.size() > 0) {
			System.out.println(email + "管理员登录成功");
			ShmManager manager = managerlist.get(0);
			manager.setLastLoginIP(model.getLastLoginIP());
			manager.setLastLoginTime(DateUtil.getCalendar());
			return manager;
		}
		System.out.println("管理员登录失败");
		return null;
	}

	@Override
	public boolean userNameIsExist(String id, String userName) {
		List<ShmManager> managerlist = new ArrayList<ShmManager>();
		if (id != null) {
			String hql = "from ShmManager u where u.userName=? and u.id<>?";
			managerlist = (List<ShmManager>) this.getHibernateTemplate().find(hql, id, userName);
		} else {
			String hql = "from ShmManager u where u.userName=?";
			managerlist = (List<ShmManager>) this.getHibernateTemplate().find(hql, userName);
		}
		if (managerlist != null && managerlist.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean emailIsExist(String id, String email) {
		List<ShmManager> managerlist = new ArrayList<ShmManager>();
		if (id != null) {
			String hql = "from ShmManager u where u.email=? and u.id<>?";
			managerlist = (List<ShmManager>) this.getHibernateTemplate().find(hql, id, email);
		} else {
			String hql = "from ShmManager u where u.email=?";
			managerlist = (List<ShmManager>) this.getHibernateTemplate().find(hql, email);
		}
		if (managerlist != null && managerlist.size() > 0) {
			return true;
		}
		return false;
	}

}
