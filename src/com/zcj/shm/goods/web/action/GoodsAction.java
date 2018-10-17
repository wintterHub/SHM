package com.zcj.shm.goods.web.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcj.shm.base.action.BaseAction;
import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.message.domain.ShmMessage;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.IOUtil;
import com.zcj.shm.util.ObjectUtil;
import com.zcj.shm.util.PropertiesLoad;
import com.zcj.shm.util.StringUtil;
import com.zcj.shm.util.UploadUtil;
import com.zcj.shm.util.VerifyUtil;

@Controller
@Scope("prototype")
public class GoodsAction extends BaseAction<ShmGoods> {

	private static final long serialVersionUID = -8808037043106614620L;
	private File myImage;
	private String myImageFileName;
	private String myImageContentType;
	private String qqNumber;
	private String mobile;

	/**
	 * 分类页面刷新
	 * 
	 * @return
	 */
	public String categroyPage() {
		// Cookie[] cookie = getRequest().getCookies();
		// System.out.println(cookie);
		// 添加查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("saleFlag", "N");
		map.put("delFlag", "N");
		map.put("categroy", model.getCategroy());

		/* 添加学校查询条件 */
		Cookie[] cookies = getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if ("User_School".equals(cookie.getName())) {
				pageBean.getDetachedCriteria().createAlias("publisher", "p");
				map.put("p.address", StringUtil.decode2Utf8(cookie.getValue()));
			}
		}

		Criterion criterion = Restrictions.allEq(map);
		pageBean.getDetachedCriteria().add(criterion).addOrder(Order.desc("addTime"));

		// 分页请求
		goodsService.pageQuery(pageBean);

		// 添加字段，优化前端显示效果
		List<ShmGoods> goodsList = pageBean.getDataRows();
		for (int i = 0; i < goodsList.size(); i++) {
			String[] images = goodsList.get(i).getImagePath().split(",");
			goodsList.get(i).setTitle(StringUtil.cutString(goodsList.get(i).getTitle(), 16));// 标题裁剪
			goodsList.get(i).setPublishTime(DateUtil.getStrAddTime(goodsList.get(i).getAddTime()));// 优化显示发布时间
			if (images.length != 0) {
				goodsList.get(i).setImages(images);
			}
		}

		//
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("Categroy", model.getCategroy());
		writeData(pageBean, hm);
		return "goods";
	}

	/**
	 * 首页刷新
	 * 
	 * @return
	 */
	public String homePage() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("saleFlag", "N");
		map.put("delFlag", "N");

		/* 添加学校查询条件 */
		Cookie[] cookies = getRequest().getCookies();
		if (ObjectUtil.notEmpty(cookies)) {
			for (Cookie cookie : cookies) {
				if ("User_School".equals(cookie.getName())) {
					pageBean.getDetachedCriteria().createAlias("publisher", "p");
					map.put("p.address", StringUtil.decode2Utf8(cookie.getValue()));
				}
			}
		}

		Criterion criterion = Restrictions.allEq(map);
		pageBean.getDetachedCriteria().add(criterion).addOrder(Order.desc("addTime"));
		goodsService.pageQuery(pageBean);
		List<ShmGoods> goodsList = pageBean.getDataRows();
		for (int i = 0; i < goodsList.size(); i++) {
			String imagePath = goodsList.get(i).getImagePath();
			String[] images = imagePath.split(",");
			goodsList.get(i).setTitle(StringUtil.cutString(goodsList.get(i).getTitle(), 16));// 标题裁剪
			goodsList.get(i).setPublishTime(DateUtil.getStrAddTime(goodsList.get(i).getAddTime()));// 优化显示发布时间
			if (images.length != 0) {
				goodsList.get(i).setImages(images);
			}
		}
		writeData(pageBean, null);
		return "home";
	}

	public String uploadImg() {
		String filePath1 = PropertiesLoad.getImageDir();
		String filePath2 = "/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
		String newName = StringUtil.getNewName(myImageFileName);
		File newFile = new File(filePath1 + filePath2, newName);
		if (!new File(filePath1 + filePath2).exists()) {
			new File(filePath1 + filePath2).mkdirs();
		}
		/* 判断已上传图片张数，并返回已上传数和图片路径给前台 */
		if (!VerifyUtil.isImage(myImageContentType)) {
			write2ajax("-1");
		} else if (getSession().getAttribute("uploadCount") == null) {
			IOUtil.copy(myImage, newFile);// 保存原始图片
			IOUtil.imageCut(myImage, filePath1 + filePath2, "cut_" + newName);// 裁剪并保存图片

			getSession().setAttribute("uploadCount", 1);// 在session中保存图片上传图片张数
			getSession().setAttribute("imagePath", filePath2 + "/cut_" + newName);// 在session中保存已上传图片路径

			String cutImagePath = filePath2 + "/cut_" + newName;
			write2ajax("{\"index\":\"1\",\"cutImagePath\":\"" + cutImagePath + "\"}");
		} else if ((int) getSession().getAttribute("uploadCount") < 3) {
			IOUtil.copy(myImage, newFile);// 保存原始图片
			IOUtil.imageCut(myImage, filePath1 + filePath2, "cut_" + newName);// 裁剪并保存图片

			getSession().setAttribute("uploadCount", ((int) getSession().getAttribute("uploadCount")) + 1);// 在session中保存图片上传图片张数
			getSession().setAttribute("imagePath", getSession().getAttribute("imagePath") + "," + filePath2 + "/cut_" + newName);// 在session中保存已上传图片路径

			String cutImagePath = filePath2 + "/cut_" + newName;
			int index = (int) getSession().getAttribute("uploadCount");
			write2ajax("{\"index\":\"" + index + "\",\"cutImagePath\":\"" + cutImagePath + "\"}");
		} else {
			write2ajax("0");
		}
		return NONE;
	}

	public String uploadImg2() {
		String imgPath = UploadUtil.uploadImg(myImage, myImageFileName, myImageContentType);
		if (imgPath != null) {
			write2ajax("{'status':true,'message':'保存成功','data':imgPath}");
			// 暂时将图片路径保存到session，用户点击完成后与表单一起存入数据库
			UploadUtil.saveImgPath2Session(getSession(), "imagePath", imgPath);
		} else {
			write2ajax("{'status':false,'message':'保存失败'}");
		}
		return NONE;
	}

	public String goPublish() {
		ShmUser user = (ShmUser) getSession().getAttribute("loginUser");
		if (ObjectUtil.empty(user)) {
			return "login";
		}
		getSession().removeAttribute("uploadCount");
		getSession().removeAttribute("imagePath");
		return "publish";
	}

	public String publishGoods() {
		ShmUser user = (ShmUser) getSession().getAttribute("loginUser");
		if (ObjectUtil.empty(user)) {
			return "login";
		}
		if (ObjectUtil.empty(user.getMobile()) || ObjectUtil.empty(user.getQqNumber())) {
			user.setMobile(mobile);
			user.setQqNumber(qqNumber);
			boolean isUpdate = userService.update(user);
			if (isUpdate) {
				getSession().setAttribute("loginUser", user);
			}
		}

		model.setImagePath(getSession().getAttribute("imagePath").toString());
		model.setPublisher((ShmUser) getSession().getAttribute("loginUser"));
		if (ObjectUtil.notEmpty(goodsService.save(model))) {
			getSession().removeAttribute("uploadCount");
			getSession().removeAttribute("imagePath");
			this.getRequest().setAttribute("backUrl", "/SHM");
			return "success";
		} else {
			getSession().removeAttribute("uploadCount");
			getSession().removeAttribute("imagePath");
			return "500";
		}
	}

	public String detailedPage() {
		ShmGoods goods = goodsService.findById(model.getId());
		boolean isSale = !"N".equalsIgnoreCase(goods.getSaleFlag());
		boolean isDel = !"N".equalsIgnoreCase(goods.getDelFlag());
		if (ObjectUtil.empty(goods) || isSale || isDel) {
			return "404";
		}
		String[] images = goods.getImagePath().split(",");
		List<ShmMessage> messages = messageService.getMessage(goods, null);// 获取该物品的评论
		goods.setMessages(messages);
		goods.setPublishTime(DateUtil.getStrAddTime(goods.getAddTime()));// 时间显示优化

		ShmUser user = (ShmUser) getSession().getAttribute("loginUser");
		boolean isCollection = collectionService.isCollection(user, goods);
		if (images.length != 0) {
			goods.setImages(images);
		}
		pushValueStack(goods);
		put("isCollection", isCollection);
		return "detailed";
	}

	public String editGoodsPage() {
		ShmGoods goods = goodsService.findById(model.getId());
		boolean isSale = !"N".equalsIgnoreCase(goods.getSaleFlag());
		boolean isDel = !"N".equalsIgnoreCase(goods.getDelFlag());
		if (ObjectUtil.empty(goods) || isSale || isDel) {
			return "404";
		}
		String[] images = goods.getImagePath().split(",");
		goods.setPublishTime(DateUtil.getStrAddTime(goods.getAddTime()));// 时间显示优化
		if (images.length != 0) {
			goods.setImages(images);
		}
		pushValueStack(goods);
		return "editGoods";
	}

	public String editGoods() {
		// System.out.println(model);
		// ShmGoods findGoods = goodsService.findById(model.getId());
		// ShmGoods coverPojo = (ShmGoods) ObjectUtil.coverPojo(model, findGoods);
		goodsService.update(model);
		write2ajax("1");
		return NONE;
	}

	public String search() {
		// Cookie[] cookie = getRequest().getCookies();
		// System.out.println(cookie);
		String key = model.getProp1();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("saleFlag", "N");
		map.put("delFlag", "N");
		/* 添加学校查询条件 */
		Cookie[] cookies = getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if ("User_School".equals(cookie.getName())) {
				pageBean.getDetachedCriteria().createAlias("publisher", "p");
				map.put("p.address", StringUtil.decode2Utf8(cookie.getValue()));
			}
		}

		Criterion criterion1 = Restrictions.allEq(map);
		Criterion criterion2 = Restrictions.or(Restrictions.like("title", "%" + key + "%"), Restrictions.like("detailed", "%" + key + "%"));
		Criterion criterion = Restrictions.and(criterion1, criterion2);
		pageBean.getDetachedCriteria().add(criterion).addOrder(Order.desc("addTime"));
		goodsService.pageQuery(pageBean);

		List<ShmGoods> goodsList = pageBean.getDataRows();
		for (int i = 0; i < goodsList.size(); i++) {
			String[] images = goodsList.get(i).getImagePath().split(",");
			goodsList.get(i).setTitle(StringUtil.cutString(goodsList.get(i).getTitle(), 18));// 标题裁剪
			goodsList.get(i).setPublishTime(DateUtil.getStrAddTime(goodsList.get(i).getAddTime()));// 优化显示发布时间
			if (images.length != 0) {
				goodsList.get(i).setImages(images);
			}

		}

		writeData(pageBean, null);
		return "search";

	}

	public String myPublish() {
		ShmUser user = ((ShmUser) getSession().getAttribute("loginUser"));
		if (ObjectUtil.empty(user)) {
			return "404";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("delFlag", "N");
		map.put("publisher", user);
		Criterion criterion = Restrictions.allEq(map);
		pageBean.getDetachedCriteria().add(criterion).addOrder(Order.desc("addTime"));
		goodsService.pageQuery(pageBean);

		List<ShmGoods> goodsList = pageBean.getDataRows();
		for (int i = 0; i < goodsList.size(); i++) {
			String[] images = goodsList.get(i).getImagePath().split(",");
			if (images.length != 0) {
				goodsList.get(i).setImages(images);
			}
		}

		writeData(pageBean, null);
		return "myPublish";

	}

	public String sale() {
		ShmUser user = ((ShmUser) getSession().getAttribute("loginUser"));
		if (ObjectUtil.empty(user)) {
			return "404";
		}
		boolean isSale = goodsService.sale(model.getId());
		if (isSale) {
			write2ajax("1");
		} else {
			write2ajax("0");
		}
		return NONE;

	}

	public String del() {
		ShmUser user = ((ShmUser) getSession().getAttribute("loginUser"));
		if (ObjectUtil.empty(user)) {
			return "404";
		}
		boolean isDel = goodsService.del(model.getId());
		if (isDel) {
			write2ajax("1");
		} else {
			write2ajax("0");
		}
		return NONE;

	}

	public void setMyImage(File myImage) {
		this.myImage = myImage;
	}

	public void setMyImageFileName(String myImageFileName) {
		this.myImageFileName = myImageFileName;
	}

	public void setMyImageContentType(String myImageContentType) {
		this.myImageContentType = myImageContentType;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
