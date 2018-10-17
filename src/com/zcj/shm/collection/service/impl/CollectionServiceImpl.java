package com.zcj.shm.collection.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcj.shm.collection.dao.ICollectionDao;
import com.zcj.shm.collection.domain.ShmCollection;
import com.zcj.shm.collection.service.ICollectionService;
import com.zcj.shm.goods.dao.IGoodsDao;
import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.ObjectUtil;

@Service
@Transactional
public class CollectionServiceImpl implements ICollectionService {

	@Autowired
	private ICollectionDao collectionDao;

	@Autowired
	private IGoodsDao goodsDao;

	@Override
	public boolean isCollection(ShmUser user, ShmGoods goods) {
		if (ObjectUtil.empty(user)) {
			return false;
		}
		List<ShmCollection> collList = collectionDao.findAll("and CollUser=? and CollGoods=?",
				new Object[] { user.getUserName(), goods.getId() });
		if (collList.size() >= 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCollFull(ShmUser user) {
		List<ShmCollection> collList = collectionDao.findAll("and CollUser=?", new Object[] { user.getUserName() });
		if (collList.size() >= 50) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<ShmGoods> findCollctionByUser(ShmUser user) {
		List<ShmCollection> collList = collectionDao.findAll("and CollUser=?", new Object[] { user.getUserName() });
		List<ShmGoods> goodsList = new ArrayList<ShmGoods>();
		for (ShmCollection collection : collList) {
			ShmGoods goods = goodsDao.findById(collection.getCollGoods());
			if (ObjectUtil.notEmpty(goods) && goods.getDelFlag() != "Y") {
				goods.setCollectionId(collection.getId());
				goodsList.add(goods);
			}
		}
		return goodsList;
	}

	@Override
	public boolean save(ShmCollection model) {
		model.setAddTime(DateUtil.getCalendar());
		model.setDelFlag("N");
		try {
			collectionDao.save(model);
			goodsDao.update("update ShmGoods set collectionCount=collectionCount+1 where id=?", new Object[] { model.getCollGoods() });
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(ShmCollection model) {
		try {
			ShmCollection collection = collectionDao.findById(model.getId());
			collectionDao.delete(collection);
			goodsDao.update("update ShmGoods set collectionCount=collectionCount-1 where id=?", new Object[] { collection.getCollGoods() });
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int collectionCount(ShmGoods goods) {
		return collectionDao.getTotalRecord("and collGoods=?", new Object[] { goods.getId() });
	}
}
