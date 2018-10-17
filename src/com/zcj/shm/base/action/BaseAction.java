package com.zcj.shm.base.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zcj.shm.code.SortDeal;
import com.zcj.shm.collection.service.ICollectionService;
import com.zcj.shm.goods.domain.ShmGoods;
import com.zcj.shm.goods.service.IGoodsService;
import com.zcj.shm.manager.service.IManagerService;
import com.zcj.shm.message.service.IMessageService;
import com.zcj.shm.page.PageBean;
import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.user.service.IUserService;
import com.zcj.shm.util.DateUtil;
import com.zcj.shm.util.ObjectUtil;
import com.zcj.shm.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 基础Action，封装了PageBean、Service，简化了值栈操作、对象转json、数据装载
 * 
 * @author Changjin.Zhao
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = 608507892998234472L;
	protected T model;

	public void setT(T model) {
		this.model = model;
	}

	// 对象封装
	@Override
	public T getModel() {
		return model;
	}

	// ---------------------------------------------------------------------------------------
	// 分页数据及PageBean的一些配置
	protected PageBean<T> pageBean = new PageBean<T>();
	DetachedCriteria detachedCriteria = null; // 此处需要获得相应的类后再赋值
	private String sort;// 字段名
	private String searchSort;// 搜索字段名
	private String order;// 排序方式
	private String key;// 查询关键字
	private String startTime;
	private String endTime;

	// 行数
	public void setRows(int rows) {
		pageBean.setPageSize(rows);
		// pageBean.setPageSize(1);
	}

	// 页码
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	// 字段名
	public void setSort(String sort) {
		sort = SortDeal.change(sort);
		if (SortDeal.ASC.equalsIgnoreCase(order)) {
			detachedCriteria.addOrder(Order.asc(sort));
		} else if (SortDeal.DESC.equalsIgnoreCase(order)) {
			detachedCriteria.addOrder(Order.desc(sort));
		}
	}

	// 查询字段
	public void setSearchSort(String searchSort) {
		if (ObjectUtil.notEmpty(key)) {
			if (SortDeal.PRICE.equals(searchSort)) {
				if (StringUtil.isFloatString(key)) {
					float parseFloat = Float.parseFloat(key);
					detachedCriteria.add(Restrictions.eq(searchSort, parseFloat));
				} else {
					detachedCriteria.add(Restrictions.eq(searchSort, -1));
				}
			} else if (SortDeal.AGE.equals(searchSort)) {
				if (StringUtil.isNumberString(key)) {
					int parseInt = Integer.parseInt(key);
					detachedCriteria.add(Restrictions.eq(searchSort, parseInt));
				} else {
					detachedCriteria.add(Restrictions.eq(searchSort, -1));
				}
			} else if (SortDeal.isUserSort(searchSort)) {
				if (ObjectUtil.notEmpty(key)) {
					detachedCriteria.add(Restrictions.eq(searchSort, new ShmUser(key)));
				}
			} else if (SortDeal.isGoodsSort(searchSort)) {
				if (ObjectUtil.notEmpty(key)) {
					detachedCriteria.add(Restrictions.eq(searchSort, new ShmGoods(key)));
				}
			} else {
				detachedCriteria.add(Restrictions.like(searchSort, "%" + key + "%"));
			}
		}
	}

	public void setKey(String key) {
		this.key = key;
	}

	// 排序方式
	public void setOrder(String order) {
		this.order = order;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
		if (ObjectUtil.notEmpty(startTime) && ObjectUtil.notEmpty(endTime)) {
			detachedCriteria.add(Restrictions.between("addTime", DateUtil.string2Calendar(startTime), DateUtil.string2Calendar(endTime)));
		}
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	// ---------------------------------------------------------------------------------------
	// 在构造函数中创建传入类型的对象
	public BaseAction() {
		ParameterizedType paramtype = null;
		if (this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
			paramtype = (ParameterizedType) this.getClass().getGenericSuperclass();
		} else {
			paramtype = (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass();
		}

		Class<T> clazz = (Class<T>) paramtype.getActualTypeArguments()[0];
		detachedCriteria = DetachedCriteria.forClass(clazz);// 离线查询
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			// 通过反射创建对象
			model = clazz.newInstance();
		} catch (Exception e) {
			// 编译时异常转换成运行时异常
			throw new RuntimeException(e);
		}
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 将Object转换成Json返回
	 * 
	 * @param excludes
	 * @throws IOException
	 */
	public void writeObject2Json(Object object, String[] excludes) throws IOException {
		// 将封装了数据的object进行转换成json
		JsonConfig jsonConfig = new JsonConfig();
		// 需要排除部分属性是为了防止对象中的一对多关系相互序列化导致死循环
		jsonConfig.setExcludes(excludes);
		if (object instanceof PageBean) {
			PageBean<T> pageBean = (PageBean<T>) object;
			pageBean.setPageCount((pageBean.getRowCount() + pageBean.getPageSize() - 1) / pageBean.getPageSize());
		}
		JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
		String json = jsonObject.toString();
		this.getResponse().setContentType("text/json;charset=utf-8");
		this.getResponse().getWriter().print(json);
		System.out.println(json);
	}

	/**
	 * 将list转换成Json返回
	 * 
	 * @param list
	 * @param strings
	 * @throws IOException
	 */
	public void writeList2Json(List<T> list, String[] excludes) throws IOException {
		// 将封装了数据的pageBean进行转换成json
		JsonConfig jsonConfig = new JsonConfig();
		// 需要排除部分属性是为了防止对象中的一对多关系相互序列化导致死循环
		jsonConfig.setExcludes(excludes);
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		String json = jsonArray.toString();
		this.getResponse().setContentType("text/json;charset=utf-8");
		this.getResponse().getWriter().print(json);
		System.out.println(json);
	}

	/**
	 * 装载pagebean和自定义数据到action，给jsp页面使用
	 * 
	 * @param pageBean
	 * @param hashmap
	 */
	public void writeData(PageBean<T> pageBean, Map<String, Object> hashmap) {
		System.out.println(pageBean);
		pageBean.setPageCount((pageBean.getRowCount() + pageBean.getPageSize() - 1) / pageBean.getPageSize());
		put("DataRows", pageBean.getDataRows());
		put("CurrentPage", pageBean.getCurrentPage());
		put("PageSize", pageBean.getPageSize());
		put("RowCount", pageBean.getRowCount());
		put("PageCount", pageBean.getPageCount());

		if (hashmap != null) {
			Set<String> keySet = hashmap.keySet();
			for (String key : keySet) {
				put(key, hashmap.get(key));
			}
		}
	}

	// 简化ajax响应操作
	public void write2ajax(String flag) {
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			System.out.println("响应已发送");
			ServletActionContext.getResponse().getWriter().println(flag);
		} catch (IOException e) {
			throw new RuntimeException("响应ajax失败");
		}
	}

	// ---------------------------------------------------------------------------------------
	// 简化值栈、request、response等操作
	public void pushValueStack(Object value) {
		ActionContext.getContext().getValueStack().push(value);
	}

	public void setValueStack(String key, Object value) {
		ActionContext.getContext().getValueStack().set(key, value);
	}

	public void put(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

	public void putSession(String key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}

	public void putApplication(String key, Object value) {
		ActionContext.getContext().getApplication().put(key, value);
	}

	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	// ---------------------------------------------------------------------------------------
	// 注入service
	@Autowired
	protected IUserService userService;

	@Autowired
	protected IGoodsService goodsService;

	@Autowired
	protected IMessageService messageService;

	@Autowired
	protected ICollectionService collectionService;

	@Autowired
	protected IManagerService managerService;
}
