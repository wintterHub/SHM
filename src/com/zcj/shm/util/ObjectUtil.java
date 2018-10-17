package com.zcj.shm.util;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import com.zcj.shm.manager.domain.ShmManager;
import com.zcj.shm.user.domain.ShmUser;
import com.zving.framework.data.DataTable;

public class ObjectUtil {

	public static boolean notEmpty(Object obj) {
		return !empty(obj);
	}

	public static boolean empty(Object obj) {
		if (obj == null) {
			return true;
		}
		if ((obj instanceof String)) {
			return obj.equals("");
		}
		if ((obj instanceof Number)) {
			return ((Number) obj).doubleValue() == 0.0D;
		}
		if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		if ((obj instanceof Collection)) {
			return ((Collection) obj).size() == 0;
		}
		if ((obj instanceof Map)) {
			return ((Map) obj).size() == 0;
		}
		if ((obj instanceof DataTable)) {
			return ((DataTable) obj).getRowCount() == 0;
		}
		return false;
	}

	public static boolean checkUNPE(String[] props, ShmUser model) {
		for (String prop : props) {
			switch (prop) {
			case "U":
				if (empty(model.getUserName())) {
					return false;
				}
				break;
			case "N":
				if (empty(model.getNickName())) {
					return false;
				}
				break;
			case "P":
				if (empty(model.getPassword())) {
					return false;
				}
				break;
			case "E":
				if (empty(model.getEmail())) {
					return false;
				}
				break;
			default:
				break;
			}
		}
		return true;
	}

	public static boolean checkUNPE(String[] props, ShmManager model) {
		for (String prop : props) {
			switch (prop) {
			case "I":
				if (empty(model.getId())) {
					return false;
				}
				break;
			case "U":
				if (empty(model.getUserName())) {
					return false;
				}
				break;
			case "P":
				if (empty(model.getPassword())) {
					return false;
				}
				break;
			case "E":
				if (empty(model.getEmail())) {
					return false;
				}
				break;
			default:
				break;
			}
		}
		return true;
	}

	//
	/**
	 * 覆盖POJO。
	 * <p>
	 * 用obj中get()得到的非null的数据
	 * 再用tagObj中set()覆盖同名的参数
	 * </p>
	 * 
	 * @param tagObj 目标对象
	 * @param obj 提供数据的对象
	 * @return Object 被覆盖的对象
	 * @author 参考自：指尖残雪[逻辑略有改动]
	 *         http://blog.csdn.net/bq1073100909/article/details/48413857
	 */
	public static Object coverPojo(Object tagObj, Object obj) {
		try {
			Class<?> clazz1 = tagObj.getClass();
			Class<?> clazz2 = obj.getClass();
			// 得到method方法
			Method[] method1 = clazz1.getMethods();
			Method[] method2 = clazz2.getMethods();

			int length1 = method1.length;
			int length2 = method2.length;
			if (length1 != 0 && length2 != 0) {
				// 创建一个get方法数组，专门存放class2的get方法。
				Method[] get = new Method[length2];
				for (int i = 0, j = 0; i < length2 - 1; i++) {
					// 只要get方法，且要排除getClass
					if (method2[i].getName().startsWith("get") && !method2[i].getName().equals("getClass")) {
						get[j] = method2[i];
						++j;
					}
				}

				for (int i = 0; i < get.length - 1; i++) {
					if (get[i] == null)// 数组初始化的长度多于get方法，所以数组后面的部分是null
						continue;
					// 得到get方法的值，判断时候为null，如果为null则进行下一个循环
					Object value = get[i].invoke(obj, new Object[] {});
					if (null == value)
						continue;
					// 得到get方法的名称 例如：getXxxx
					String getName = get[i].getName();
					// 得到set方法的时候传入的参数类型，就是get方法的返回类型
					Class<?> paramType = get[i].getReturnType();
					Method getMethod = null;
					try {
						// 判断在class1中时候有class2中的get方法，如果没有则抛异常继续循环
						getMethod = clazz1.getMethod(getName, new Class[] {});
					} catch (NoSuchMethodException e) {
						continue;
					}
					// class1的get方法不为空并且class2中get方法得到的值不为空，进行赋值，如果class2属性值为null，则跳过
					if (null == getMethod || null == getMethod.invoke(obj, new Object[] {}))
						continue;
					// 通过getName 例如getXxxx 截取后得到Xxxx，然后在前面加上set，就组装成set的方法名
					String setName = "set" + getName.substring(3);
					// 得到class1的set方法，并调用
					Method setMethod = clazz1.getMethod(setName, paramType);
					setMethod.invoke(tagObj, value);
				}
			}
		} catch (Exception e) {
			System.out.println("Pojo覆盖失败");
		}
		return tagObj;
	}

}
