package com.zcj.shm.goods.service;

import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.page.PageBean;

public interface IGoodsService {

	void pageQuery(PageBean<ShmGoods> pageBean);

	String save(ShmGoods model);

	ShmGoods findById(String id);

	boolean sale(String id);

	boolean del(String id);

	// boolean updateImagePath(String id, String imagePath);

	boolean update(ShmGoods goods);

	boolean onSale(String ids);

	boolean offSale(String ids);

	// boolean findCollecter(ShmUser userName, ShmGoods string);

}
