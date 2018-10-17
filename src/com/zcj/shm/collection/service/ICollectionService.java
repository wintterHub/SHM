package com.zcj.shm.collection.service;

import java.util.List;

import com.zcj.shm.collection.domain.ShmCollection;
import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.user.domain.ShmUser;

public interface ICollectionService {

	boolean isCollection(ShmUser user, ShmGoods goods);

	boolean isCollFull(ShmUser user);

	List<ShmGoods> findCollctionByUser(ShmUser user);

	boolean save(ShmCollection model);

	boolean delete(ShmCollection model);

	int collectionCount(ShmGoods goods);

}
