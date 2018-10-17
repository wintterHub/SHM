package com.zcj.shm.goods.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;

import com.zcj.shm.base.action.BaseAction;
import com.zcj.shm.code.Excludes;
import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.goods.service.impl.GoodsServiceImpl;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.IOUtil;
import com.zcj.shm.util.ObjectUtil;
import com.zcj.shm.util.StringUtil;
import com.zcj.shm.util.UploadUtil;
import com.zving.framework.expression.function.Substring;

public class GoodsAdminAction extends BaseAction<ShmGoods> {

	private static final long serialVersionUID = -3756439787275745872L;

	private String ids;
	private String mobile;
	private String qqNumber;
	private File goodsImgs;
	private String goodsImgsFileName;
	private String goodsImgsContentType;
	private String startClearDate;
	private String endClearDate;

	public String pageQuery() {
		goodsService.pageQuery(pageBean);
		List<ShmGoods> rows = pageBean.getRows();
		if (ObjectUtil.notEmpty(rows)) {
			for (ShmGoods goods : rows) {
				if (goods.getAddTime() != null) {
					goods.setPublishTime(DateUtil.getStrAddTime(goods.getAddTime()));
				}
				int collectionCount = collectionService.collectionCount(goods);
				goods.setCollectionCount(collectionCount);
				goods.setIdCopy(goods.getId());
				goods.setPublishUserId(goods.getPublisher().getUserName());
				goods.setPublishUser(goods.getPublisher().getNickName());
				goods.setImages(goods.getImagePath().split(","));
			}
		}
		try {
			writeObject2Json(pageBean, Excludes.GoodsExcludes);
		} catch (IOException e) {
			put("Error", "商品数据请求失败");
		}
		return NONE;
	}

	public String saveForm() {
		if (ObjectUtil.empty(model)) {
			write2ajax("{'status':false,'message':'无效请求'}");
		} else if (ObjectUtil.empty(model.getPublishUser())) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else {
			ShmUser user = userService.findUserByName(model.getPublishUser());
			model.setPublisher(user);
			if (user == null) {
				write2ajax("{'status':false,'message':'用户不存在'}");
			} else if (!userService.haveMobileOrQQ(user)) {
				user.setQqNumber(qqNumber);
				user.setMobile(mobile);
				if (!userService.haveMobileOrQQ(user)) {
					write2ajax("{'status':false,'message':'该用户没有联方式，请至少填写一种'}");
				}
			} else {
				// 只保存到session中，等后续步骤完成后再一起存入数据库
				getSession().setAttribute("model", model);
				write2ajax("{'status':true,'message':'保存成功'}");
			}
		}
		return NONE;
	}

	public String uploadImg() {
		String imgPath = UploadUtil.uploadImg(goodsImgs, goodsImgsFileName, goodsImgsContentType);
		if (imgPath != null) {
			write2ajax("{'status':true,'message':'保存成功'}");
			// 暂时将图片路径保存到session，用户点击完成后与表单一起存入数据库
			UploadUtil.saveImgPath2Session(getSession(), "imagePath", imgPath);
		} else {
			write2ajax("{'status':false,'message':'保存失败'}");
		}
		return NONE;
	}

	public String save() {
		String imagePath = (String) getSession().getAttribute("imagePath");
		if (imagePath == null) {
			write2ajax("{'status':false,'message':'请至少上传一张图片'}");
		} else {
			ShmGoods goods = (ShmGoods) getSession().getAttribute("model");
			goods.setImagePath(imagePath);
			String id = goodsService.save(goods);
			if (ObjectUtil.notEmpty(id)) {
				// boolean idUpdate = goodsService.updateImagePath(id, imagePath);
				// if (idUpdate) {
				write2ajax("{'status':true,'message':'保存成功'}");
				getSession().removeAttribute("model");
				// } else {
				// write2ajax("{'status':false,'message':'保存失败'}");
				// }
				getSession().removeAttribute("imagePath");
			} else {
				write2ajax("{'status':false,'message':'保存失败'}");
			}
		}
		return NONE;
	}

	public String updateForm() {
		if (ObjectUtil.empty(model)) {
			write2ajax("{'status':false,'message':'无效请求'}");
		} else if (ObjectUtil.empty(model.getPublishUser())) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else {
			ShmUser user = userService.findUserByName(model.getPublishUserId());
			model.setPublisher(user);
			if (user == null) {
				write2ajax("{'status':false,'message':'用户不存在'}");
			} else if (!userService.haveMobileOrQQ(user)) {
				user.setQqNumber(qqNumber);
				user.setMobile(mobile);
				if (!userService.haveMobileOrQQ(user)) {
					write2ajax("{'status':false,'message':'该用户没有联方式，请至少填写一种'}");
				}
			} else {
				String imagePath = (String) getSession().getAttribute("imagePath");
				if (imagePath != null) {
					model.setImagePath(imagePath);
				}
				boolean isUpate = goodsService.update(model);
				if (isUpate) {
					write2ajax("{'status':true,'message':'保存成功'}");
					getSession().removeAttribute("model");
					getSession().removeAttribute("imagePath");
				} else {
					write2ajax("{'status':false,'message':'保存失败'}");
				}
			}
		}
		return NONE;
	}

	public String checkUpload() {
		String imagePath = (String) getSession().getAttribute("imagePath");
		if (imagePath != null) {
			write2ajax("{'status':true,'message':'保存成功',data:'" + imagePath + "'}");
		} else {
			write2ajax("{'status':true,'message':'您没有对任何图片进行修改'}");
		}
		return NONE;
	}

	// public String clear() {
	// getSession().removeAttribute("model");
	// getSession().removeAttribute("imagePath");
	// return NONE;
	// }

	public String cancelFirstImg() {
		String imagePath = (String) getSession().getAttribute("imagePath");
		if (ObjectUtil.notEmpty(imagePath)) {
			int index = imagePath.indexOf(",");
			imagePath = imagePath.substring(index);
			System.out.println(imagePath);
		}
		return NONE;
	}

	public String doStart() {
		if (ids.isEmpty()) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (goodsService.onSale(ids)) {
			write2ajax("{'status':true,'message':'操作成功'}");
		} else {
			write2ajax("{'status':false,'message':'操作失败'}");
		}
		return NONE;
	}

	public String doStop() {
		if (ids.isEmpty()) {
			write2ajax("{'status':false,'message':'必填字段数据缺失'}");
		} else if (goodsService.offSale(ids)) {
			write2ajax("{'status':true,'message':'操作成功'}");
		} else {
			write2ajax("{'status':false,'message':'操作失败'}");
		}
		return NONE;
	}

	public String clearImage() {
		String startDate = startClearDate.replace("-", "");
		String endDate = endClearDate.replace("-", "");
		System.out.println(startDate);
		System.out.println(endDate);
		boolean isClear = false;
		HashSet<String> hashSet = new HashSet<String>();
		if (ObjectUtil.notEmpty(startClearDate) && ObjectUtil.notEmpty(endClearDate)) {
			pageBean.getDetachedCriteria().add(Restrictions.between("addTime", DateUtil.string2Calendar(startClearDate + " 00:00:00"),
					DateUtil.string2Calendar(endClearDate + " 00:00:00")));
			goodsService.pageQuery(pageBean);
			List<ShmGoods> dataRows = pageBean.getDataRows();
			for (ShmGoods data : dataRows) {
				String[] images = data.getImagePath().split(",");
				for (String image : images) {
					String imageName = image.substring(image.lastIndexOf("/") + 1);
					hashSet.add(imageName);
				}
			}
			isClear = IOUtil.clearServerImage(hashSet, startDate, endDate);
		}
		if (isClear) {
			write2ajax("{'status':true,'message':'操作成功'}");
		} else {
			write2ajax("{'status':false,'message':'操作异常,部分文件未清除'}");
		}
		return NONE;

	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public void setGoodsImgs(File goodsImgs) {
		this.goodsImgs = goodsImgs;
	}

	public void setGoodsImgsFileName(String goodsImgsFileName) {
		this.goodsImgsFileName = goodsImgsFileName;
	}

	public void setGoodsImgsContentType(String goodsImgsContentType) {
		this.goodsImgsContentType = goodsImgsContentType;
	}

	public void setStartClearDate(String startClearDate) {
		this.startClearDate = startClearDate;
	}

	public void setEndClearDate(String endClearDate) {
		this.endClearDate = endClearDate;
	}

	public String goFrame() {
		put("flag", "goodsManage");
		return "frame";
	}

}
