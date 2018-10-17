package com.zcj.shm.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcj.shm.page.PageBean;
import com.zcj.shm.user.dao.IUserDao;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.user.service.IUserService;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.ObjectUtil;
import com.zcj.shm.util.StringUtil;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public boolean save(ShmUser user) {
		try {
			String md5Password = StringUtil.getMD5Password(user.getPassword(), user.getEmail());
			user.setPassword(md5Password);
			user.setDelFlag("N");
			user.setAuthenticationStatus("N");
			user.setAddTime(DateUtil.getCalendar());
			userDao.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void pageQuery(PageBean<ShmUser> pageBean) {
		userDao.findAll(pageBean);
	}

	@Override
	public ShmUser login(ShmUser model) {
		String email = model.getEmail();
		String password = model.getPassword();
		String md5Password = StringUtil.getMD5Password(password, email);
		model.setPassword(md5Password);
		return userDao.login(model);

	}

	@Override
	public ShmUser cookieLogin(ShmUser model) {
		return userDao.login(model);
	}

	@Override
	public void delete(String loginName) {
	}

	@Override
	public boolean update(ShmUser user) {
		try {
			user.setModifyTime(DateUtil.getCalendar());
			userDao.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean update4Admin(ShmUser user) {
		try {
			ShmUser findUser = findUserByName(user.getUserName());
			findUser.setNickName(user.getNickName());
			findUser.setStudentID(user.getStudentID());
			findUser.setEmail(user.getEmail());
			findUser.setGender(user.getGender());
			findUser.setAge(user.getAge());
			findUser.setAddress(user.getAddress());
			findUser.setDepartment(user.getDepartment());
			findUser.setQqNumber(user.getQqNumber());
			findUser.setMobile(user.getMobile());
			findUser.setMemo(user.getMemo());
			findUser.setModifyTime(DateUtil.getCalendar());
			userDao.update(findUser);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ShmUser register(ShmUser model) {
		String email = model.getEmail();
		ShmUser user = findUserByEmail(email);
		if (user == null) {// 邮箱没有被注册
			// if (ObjectUtil.userNotEmpty(model)) {
			String password = model.getPassword();
			String md5Password = StringUtil.getMD5Password(password, email);
			model.setPassword(md5Password);
			model.setDelFlag("N");
			model.setAuthenticationStatus("N");
			model.setAddTime(DateUtil.getCalendar());
			// model.setModifyTime(DateUtil.getCalendar());
			model.setLastLoginTime(DateUtil.getCalendar());
			try {
				userDao.save(model);
				return model;
			} catch (Exception e) {
				e.printStackTrace();
			}
			// }
		}
		return null;
	}

	@Override
	public ShmUser findUserByName(String username) {
		return userDao.findById(username);
	}

	@Override
	public ShmUser findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	public boolean nickNameIsExist(ShmUser model) {
		String nickName = model.getNickName();
		String userName = model.getUserName();
		return userDao.nickNameIsExist(nickName, userName);
	}

	@Override
	public boolean emailIsExist(ShmUser model) {
		String email = model.getEmail();
		String userName = model.getUserName();
		return userDao.emailIsExist(email, userName);
	}

	@Override
	public boolean resetPwd(ShmUser model) {
		String password = model.getPassword();
		String email = model.getEmail();
		String hql = "update ShmUser u set u.password=? where email=? ";
		String md5Password = StringUtil.getMD5Password(password, email);
		try {
			Object[] objects = { md5Password, email };
			userDao.update(hql, objects);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUserName(String userName, String email) {
		try {
			userDao.update("Update ShmUser u set u.UserName=? where u.Email=?", new Object[] { userName, email });
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updatePassword(ShmUser user) {
		String md5Password = StringUtil.getMD5Password(user.getPassword(), user.getEmail());
		user.setPassword(md5Password);
		try {
			userDao.update("update ShmUser set password=? where userName=?", new Object[] { user.getPassword(), user.getUserName() });
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// @Override
	// public boolean saveOrUpdate(ShmUser model) {
	// if (ObjectUtil.notEmpty(model.getPassword())) {
	// String md5Password = StringUtil.getMD5Password(model.getPassword(), model.getEmail());
	// model.setPassword(md5Password);
	// }
	// try {
	// userDao.saveOrUpdate(model);
	// return true;
	// } catch (Exception e) {
	// return false;
	// }
	//
	// }

	@Override
	public boolean notExist(ShmUser model) {
		List<ShmUser> userlist = userDao.findAll("and nickName=? or email=?", new Object[] { model.getNickName(), model.getEmail() });
		if (ObjectUtil.empty(userlist)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean doStart(String ids) {
		String[] IDs = ids.split(",");
		try {
			for (String id : IDs) {
				userDao.update("update ShmUser set delFlag='N' where id=?", new Object[] { id });
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
				userDao.update("update ShmUser set delFlag='Y' where id=?", new Object[] { id });
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean haveMobileOrQQ(ShmUser user) {
		if (ObjectUtil.notEmpty(user.getQqNumber())) {
			return true;
		}
		if (ObjectUtil.notEmpty(user.getMobile())) {
			return true;
		}
		return false;
	}

	@Override
	public ShmUser findUserByNickName(String nickName) {
		List<ShmUser> findAll = userDao.findAll("and nickName=?", new Object[] { nickName });
		return findAll.get(0);
	}

	// @Override
	// public boolean nickNameIsExist(ShmUser model) {
	// findUserByNickName(model);
	// return false;
	// }

}
