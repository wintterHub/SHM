package com.zcj.shm.util;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesLoad {

	private static String strClassName = PropertiesLoad.class.getName();
	private static Logger log = Logger.getLogger(strClassName);

	private static Properties properties = null;
	private static InputStream inputStream = null;

	private static String sendMail = null;
	private static String imageDir = null;
	private static String emailAccount = null;
	private static String emailPassword = null;
	private static String emailSMTP = null;

	static {
		try {
			properties = new Properties();
			inputStream = PropertiesLoad.class.getClassLoader().getResourceAsStream("shmInfo.properties");
			properties.load(inputStream);
			sendMail = properties.getProperty("sendMail");
			imageDir = properties.getProperty("imageDir");
			emailAccount = properties.getProperty("emailAccount");
			emailPassword = properties.getProperty("emailPassword");
			emailSMTP = properties.getProperty("emailSMTP");
		} catch (Exception e) {
			log.severe("SHM系统配置文件读取失败,可能是scr目录下没有shmInfo.properties配置文件");
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				log.severe("SHM系统配置文件关闭失败");
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			inputStream = null;
		}
		if (ObjectUtil.empty(sendMail)) {
			log.severe("sendMail值不能为空");
			throw new RuntimeException("sendMail值不能为空");
		} else {
			log.info("sendMail = " + sendMail);
		}
		if (ObjectUtil.empty(imageDir)) {
			log.severe("imageDir值不能为空");
			throw new RuntimeException("imageDir值不能为空");
		} else {
			log.info("imageDir = " + imageDir);
		}

		if ("true".equals(sendMail)) {
			if (ObjectUtil.empty(emailAccount)) {
				log.severe("emailAccount值不能为空");
				throw new RuntimeException("emailAccount值不能为空");
			} else {
				log.info("emailAccount = " + emailAccount);
			}
			if (ObjectUtil.empty(emailPassword)) {
				log.severe("emailPassword值不能为空");
				throw new RuntimeException("emailPassword值不能为空");
			} else {
				log.info("emailPassword = " + emailPassword);
			}
			if (ObjectUtil.empty(emailSMTP)) {
				log.severe("emailSMTP值不能为空");
				throw new RuntimeException("emailSMTP值不能为空");
			} else {
				log.info("emailSMTP = " + emailSMTP);
			}
		}

	}

	public static String getSendMail() {
		return sendMail;
	}

	public static String getImageDir() {
		return imageDir;
	}

	public static String getEmailAccount() {
		return emailAccount;
	}

	public static String getEmailPassword() {
		return emailPassword;
	}

	public static String getEmailSMTP() {
		return emailSMTP;
	}

}
