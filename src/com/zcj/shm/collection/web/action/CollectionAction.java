package com.zcj.shm.collection.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcj.shm.base.action.BaseAction;
import com.zcj.shm.collection.domain.ShmCollection;
import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.ObjectUtil;

@Controller
@Scope("prototype")
public class CollectionAction extends BaseAction<ShmCollection> {

	private static final long serialVersionUID = -4651764713090414091L;

	public String collection() {
		ShmUser user = (ShmUser) getSession().getAttribute("loginUser");
		if (ObjectUtil.empty(user) && ObjectUtil.empty(model.getCollGoods())) {
			return "404";
		}
		model.setCollUser(user.getUserName());
		boolean isSave = collectionService.save(model);
		if (isSave) {
			write2ajax("1");
		} else {
			write2ajax("0");
		}
		return NONE;
	}

	public String myCollection() {
		ShmUser user = ((ShmUser) getSession().getAttribute("loginUser"));
		if (ObjectUtil.empty(user)) {
			return "404";
		}
		List<ShmGoods> goodsList = collectionService.findCollctionByUser(user);
		if (ObjectUtil.empty(goodsList)) {
			return "myCollection";
		}
		for (int i = 0; i < goodsList.size(); i++) {
			String[] images = goodsList.get(i).getImagePath().split(",");
			if (images.length != 0) {
				goodsList.get(i).setImages(images);
			}
		}
		put("goodsList", goodsList);
		return "myCollection";

	}

	public String isCollFull() {
		ShmUser user = (ShmUser) getSession().getAttribute("loginUser");
		if (ObjectUtil.empty(user)) {
			return "404";
		}
		boolean isCollFull = collectionService.isCollFull(user);
		if (isCollFull) {
			write2ajax("1");
		} else {
			write2ajax("0");
		}
		return NONE;

	}

	public String del() {
		boolean isDelete = collectionService.delete(model);
		if (isDelete) {
			write2ajax("1");
		} else {
			write2ajax("0");
		}
		return NONE;

	}
}
