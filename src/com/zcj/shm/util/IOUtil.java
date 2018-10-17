package com.zcj.shm.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class IOUtil {

	private static String strClassName = IOUtil.class.getName();
	private static Logger log = Logger.getLogger(strClassName);

	private static List<File> filelist = new ArrayList<File>();

	/**
	 * 递归遍历文件夹中所有文件
	 * 
	 * @param directory 需要遍历的文件夹
	 * @return List<File>
	 */
	public static List<File> getFilelist(File file) {
		if (file.isFile()) {
			filelist.add(file);
		} else if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File listFile : listFiles) {
				getFilelist(listFile);
			}
		}
		return filelist;
	}

	/**
	 * 批量复制文件
	 * 
	 * @param originalDirPath 原始文件夹路径
	 * @param newDirPath 目标文件夹路径
	 * @param extension 文件名批量追加字符串
	 * @param size 文件大小过滤（字节），将小于该值的文件排除
	 */
	public static void copyAll(String originalDirPath, String newDirPath, String extension, long size) {

		File originalDir = new File(originalDirPath);

		if (!originalDir.exists()) {
			log.warning("原文件夹不存在");
		} else {
			File newDir = new File(newDirPath);
			if (!newDir.exists()) {
				newDir.mkdir();
			}

			for (File fileOnWin : originalDir.listFiles()) {
				File newFile = new File(newDirPath + "/" + fileOnWin.getName() + extension);
				if (fileOnWin.length() > size) {
					if (!newFile.exists()) {
						filelist.add(fileOnWin);
						copy(fileOnWin, newFile);
					} else {
						log.info("文件 “" + newFile.getName() + "” 已存在");
					}
				}
			}
		}

	}

	/**
	 * 复制文件
	 * 
	 * @param originalFile 原文件
	 * @param newFile 新文件
	 */
	public static void copy(File originalFile, File newFile) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new BufferedInputStream(new FileInputStream(originalFile));
			os = new BufferedOutputStream(new FileOutputStream(newFile));
			byte[] b = new byte[4096];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				os.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.severe("复制文件失败");
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 字符串保存成txt文本文件
	 * 
	 * @param path
	 * @param content
	 */
	public static void saveAsTxt(String path, String filename, String content) {
		File file = new File(path + "/" + filename + ".txt");
		FileWriter fos = null;
		try {
			fos = new FileWriter(file);
			fos.write(content);
		} catch (IOException ex) {
			Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * 网络图片保存到本地
	 * 
	 * @param url 图片地址
	 * @param filepath 文件路径
	 * @throws Exception
	 */
	public static void saveAsImg(String url, String filepath) throws Exception {
		// System.out.println("--" + url + "--");
		// new一个URL对象
		URL myUrl = new URL(url);
		// 打开链接
		HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
		// 设置请求方式为"GET"
		conn.setRequestMethod("GET");
		// 超时响应时间为5秒
		conn.setConnectTimeout(5 * 1000);
		// 通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = readInputStream(inStream);
		// new一个文件对象用来保存图片，默认保存当前工程根目录
		File imageFile = new File(filepath);
		// 创建输出流
		FileOutputStream outStream = new FileOutputStream(imageFile);
		// 写入数据
		outStream.write(data);
		// 关闭输出流
		outStream.close();
	}

	private static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}

	/**
	 * 按1：1的比例从中心开始裁剪图片,并保存
	 * 
	 * @param imageFile
	 * @param saveFileName
	 */
	public static void imageCut(File imageFile, String savePath, String saveFileName) {
		BufferedImage imageCut = imageCut(imageFile, 1, 1);
		File file = new File(savePath, saveFileName);
		try {
			String fileType = StringUtil.getFileType(saveFileName);
			ImageIO.write(imageCut, fileType, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将图片按比例从中心开始裁剪
	 * 
	 * @param file
	 * @return
	 */
	public static BufferedImage imageCut(File file, int Pwidth, int Pheight) {
		BufferedImage bfImage = null;
		BufferedImage bufferedImage = null;
		try {
			bfImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width = bfImage.getWidth();// 图片宽度
		int height = bfImage.getHeight();// 图片高度

		if (width > height) {
			bfImage = crop(bfImage, width / 2 - height * (Pwidth / Pheight) / 2, 0, height * (Pwidth / Pheight), height);
		} else {
			System.out.println("width < height");
			bfImage = crop(bfImage, 0, height / 2 - width * (Pheight / Pwidth) / 2, width, width * (Pheight / Pwidth));
		}
		return bfImage;
	}

	/**
	 * 按坐标裁剪图片
	 * 
	 * @param source 待处理的图片流
	 * @param startX 开始x坐标
	 * @param startY 开始y坐标
	 * @param w 目标切片 宽度
	 * @param h 目标切片 高度
	 * @return
	 */
	private static BufferedImage crop(BufferedImage source, int startX, int startY, int w, int h) {
		int width = source.getWidth();
		int height = source.getHeight();

		if (startX <= -1) {
			startX = 0;
		}
		if (startY <= -1) {
			startY = 0;
		}
		if (w <= -1) {
			w = width - 1;
		}
		if (h <= -1) {
			h = height - 1;
		}
		BufferedImage result = new BufferedImage(w, h, source.getType());
		for (int y = startY; y < h + startY; y++) {
			for (int x = startX; x < w + startX; x++) {
				int rgb = source.getRGB(x, y);
				result.setRGB(x - startX, y - startY, rgb);
			}
		}
		return result;
	}

	/**
	 * 清除服务器没有关联商品的图片
	 * 
	 * @param value 不删除的文件序列，用
	 * @param startDirName
	 * @param endDirName
	 * @return
	 */
	public static boolean clearServerImage(HashSet<String> value, String startDirName, String endDirName) {
		int startInt = Integer.parseInt(startDirName);
		int endInt = Integer.parseInt(endDirName);
		boolean isDelete = true;
		String serverImageDirPath = PropertiesLoad.getImageDir();
		for (int i = startInt; i <= endInt; i++) {
			File imageDir = new File(serverImageDirPath + "/" + i);
			boolean exists = imageDir.exists();
			if (exists) {
				String[] namelist = imageDir.list();
				for (String imageFileName : namelist) {
					if (imageFileName.startsWith("cut_")) {
						boolean contains = value.contains(imageFileName);
						if (!contains) {
							isDelete = new File(serverImageDirPath + "/" + i + "/" + imageFileName).delete();
						}
					} else {
						boolean contains = value.contains("cut_" + imageFileName);
						if (!contains) {
							isDelete = new File(serverImageDirPath + "/" + i + "/" + imageFileName).delete();
						}
					}
					if (!isDelete) {
						return false;
					}
				}
			}
		}
		return true;
	}

}
