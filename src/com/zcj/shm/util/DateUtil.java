package com.zcj.shm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

	public static Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.clear();// 注：在使用set方法之前，必须先clear一下，否则很多信息会继承自系统当前时间
		cal.setTime(new Date());
		return cal;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.clear();// 注：在使用set方法之前，必须先clear一下，否则很多信息会继承自系统当前时间
		cal.setTime(date);
		return cal;
	}

	public static Date getDate(Calendar calendar) {
		return calendar.getTime();
	}

	// public static void cutCenterImage(String src, String dest, int w, int h) throws IOException {
	// Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("jpg");
	// ImageReader reader = (ImageReader) iterator.next();
	// InputStream in = new FileInputStream(src);
	// ImageInputStream iis = ImageIO.createImageInputStream(in);
	// reader.setInput(iis, true);
	// ImageReadParam param = reader.getDefaultReadParam();
	// int imageIndex = 0;
	// Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w, h);
	// param.setSourceRegion(rect);
	// BufferedImage bi = reader.read(0, param);
	// ImageIO.write(bi, "jpg", new File(dest));
	// }

	public static Calendar addDay(Date date, int count) {
		Calendar cal = Calendar.getInstance();
		cal.clear();// 注：在使用set方法之前，必须先clear一下，否则很多信息会继承自系统当前时间
		cal.setTime(new Date(date.getTime() + 86400000L * count));
		return cal;
	}

	public static Map<String, Integer> getTimeDifference(Calendar startTime) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Date begin = startTime.getTime();
		Date end = new Date();
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		long year = between / (24 * 3600) / (30 * 12);
		long month = between / (24 * 3600) / 30;
		long day = between / (24 * 3600);
		long hour = between % (24 * 3600) / 3600;
		long minute = between % 3600 / 60;
		long second = between % 60 / 60;
		map.put("year", (int) year);
		map.put("month", (int) month);
		map.put("day", (int) day);
		map.put("hour", (int) hour);
		map.put("minute", (int) minute);
		map.put("second", (int) second);
		return map;
	}

	public static String getStrAddTime(Calendar addTime) {
		Map<String, Integer> time = getTimeDifference(addTime);
		if (time.get("year") != 0) {
			return time.get("year") + "年前";
		} else if (time.get("month") != 0) {
			return time.get("month") + "月前";
		} else if (time.get("day") != 0) {
			return time.get("day") + "天前";
		} else if (time.get("hour") != 0) {
			return time.get("hour") + "小时前";
		} else if (time.get("minute") != 0) {
			return time.get("minute") + "分钟前";
		} else if (time.get("second") != 0) {
			return "刚刚";
		}
		return "刚刚";
	}

	public static Calendar string2Calendar(String value) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = null;
		try {
			parse = format.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getCalendar(parse);

	}

}
