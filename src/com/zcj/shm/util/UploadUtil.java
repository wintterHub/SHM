package com.zcj.shm.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class UploadUtil {

	private static final String serverPath = PropertiesLoad.getImageDir();

	private static String relativePath;

	private static String newName;

	private static File imageDir;

	public static String uploadImg(File imgFile, String fileName, String fileContentType) {
		// relativePath = "/image/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
		relativePath = "/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
		newName = StringUtil.getNewName(fileName);
		imageDir = new File(serverPath + relativePath);
		File newFile = new File(serverPath + relativePath, newName);
		if (!imageDir.exists()) {
			imageDir.mkdirs();
		}
		try {
			IOUtil.imageCut(imgFile, serverPath + relativePath, "cut_" + newName);
			IOUtil.copy(imgFile, newFile);
		} catch (Exception e) {
			return null;
		}
		return relativePath + "/" + "cut_" + newName;
	}

	public static boolean saveImgPath2Session(HttpSession session, String sessionAttr, String imgPath) {
		try {
			if (session.getAttribute(sessionAttr) == null) {
				session.setAttribute(sessionAttr, imgPath);// 在session中保存已上传图片路径
			} else {
				session.setAttribute(sessionAttr, session.getAttribute(sessionAttr) + "," + imgPath);// 在session中保存已上传图片路径
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
