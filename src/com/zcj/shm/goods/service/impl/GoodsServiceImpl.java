package com.zcj.shm.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcj.shm.goods.dao.IGoodsDao;
import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.goods.service.IGoodsService;
import com.zcj.shm.page.PageBean;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.ObjectUtil;

@Service
@Transactional
public class GoodsServiceImpl implements IGoodsService {
	@Autowired
	private IGoodsDao goodsDao;

	// @Autowired
	// private IUserDao userDao;

	@Override
	public void pageQuery(PageBean<ShmGoods> pageBean) {
		goodsDao.findAll(pageBean);
	}

	@Override
	public String save(ShmGoods model) {
		if (ObjectUtil.empty(model.getImagePath())) {
			model.setImagePath("/image/image404.jpg");
		}
		model.setAddTime(DateUtil.getCalendar());
		// model.setDueTime(DateUtil.addDay(new Date(), 90));
		model.setSaleFlag("N");
		model.setDelFlag("N");
		model.setViewCount(0);
		model.setCollectionCount(0);
		return goodsDao.save(model);
	}

	@Override
	public ShmGoods findById(String id) {
		ShmGoods goods = goodsDao.findById(id);
		if (ObjectUtil.notEmpty(goods)) {
			goods.setViewCount((int) goods.getViewCount() + 1);
		}

		return goods;
	}

	@Override
	public boolean sale(String id) {
		try {
			goodsDao.update("update ShmGoods set saleFlag=? where id=?", new Object[] { "Y", id });
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean del(String id) {
		try {
			goodsDao.update("update ShmGoods set delFlag=? where id=?", new Object[] { "Y", id });
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// @Override
	// public boolean updateImagePath(String id, String imagePath) {
	// try {
	// goodsDao.update("update ShmGoods set imagePath=? where id=?", new Object[] { imagePath, id });
	// return true;
	// } catch (Exception e) {
	// return false;
	// }
	// }

	@Override
	public boolean update(ShmGoods goods) {
		try {
			ShmGoods goods2 = goodsDao.findById(goods.getId());
			goods2 = (ShmGoods) ObjectUtil.coverPojo(goods2, goods);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean onSale(String ids) {
		String[] IDs = ids.split(",");
		try {
			for (String id : IDs) {
				goodsDao.update("update ShmGoods set delFlag='N' where id=?", new Object[] { id });
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean offSale(String ids) {
		String[] IDs = ids.split(",");
		try {
			for (String id : IDs) {
				goodsDao.update("update ShmGoods set delFlag='Y' where id=?", new Object[] { id });
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
