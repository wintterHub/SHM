package com.zcj.shm.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcj.shm.manager.dao.IManagerDao;
import com.zcj.shm.manager.domain.ShmManager;
import com.zcj.shm.manager.service.IManagerService;
import com.zcj.shm.page.PageBean;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.StringUtil;

@Service
@Transactional
public class ManagerServiceImpl implements IManagerService {

	@Autowired
	private IManagerDao managerDao;

	@Override
	public ShmManager login(ShmManager model) {
		String email = model.getEmail();
		String password = model.getPassword();
		String md5Password = StringUtil.getMD5Password(password, email);
		model.setPassword(md5Password);
		return managerDao.login(model);
	}

	@Override
	public void pageQuery(PageBean<ShmManager> pageBean) {
		managerDao.findAll(pageBean);
	}

	@Override
	public boolean userNameIsExist(ShmManager model) {
		String id = model.getId();
		String userName = model.getUserName();
		return managerDao.userNameIsExist(id, userName);
	}

	@Override
	public boolean emailIsExist(ShmManager model) {
		String id = model.getId();
		String email = model.getEmail();
		return managerDao.emailIsExist(id, email);
	}

	@Override
	public boolean save(ShmManager model) {
		try {
			String md5Password = StringUtil.getMD5Password(model.getPassword(), model.getEmail());
			model.setPassword(md5Password);
			model.setDelFlag("N");
			model.setAddTime(DateUtil.getCalendar());
			managerDao.save(model);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(ShmManager model) {
		try {
			ShmManager findManager = managerDao.findById(model.getId());
			findManager.setUserName(model.getUserName());
			findManager.setEmail(model.getEmail());
			findManager.setMobile(model.getMobile());
			findManager.setIsSuperAdmin(model.getIsSuperAdmin());
			findManager.setMemo(model.getMemo());
			findManager.setModifyTime(DateUtil.getCalendar());
			managerDao.update(findManager);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean doStart(String ids) {
		String[] IDs = ids.split(",");
		try {
			for (String id : IDs) {
				managerDao.update("update ShmManager set delFlag='N' where id=?", new Object[] { id });
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean doStop(String ids) {
		String[] IDs = ids.split(",");
		try {
			for (String id : IDs) {
				managerDao.update("update ShmManager set delFlag='Y' where id=?", new Object[] { id });
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updatePassword(ShmManager model) {
		String md5Password = StringUtil.getMD5Password(model.getPassword(), model.getEmail());
		model.setPassword(md5Password);
		try {
			managerDao.update("update ShmManager set password=? where id=?", new Object[] { model.getPassword(), model.getId() });
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
