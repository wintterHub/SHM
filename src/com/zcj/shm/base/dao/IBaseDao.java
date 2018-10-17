package com.zcj.shm.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zcj.shm.page.PageBean;

public interface IBaseDao<T> {

	/**
	 * 保存
	 * 
	 * @param t
	 * @return
	 */
	String save(T t);

	/**
	 * 删除
	 * 
	 * @param t
	 */
	void delete(T t);

	/**
	 * 更新
	 * 
	 * @param t
	 */
	void update(T t);

	/**
	 * 保存或更新（通过有无ID来判断）
	 * 
	 * @param t
	 */
	void saveOrUpdate(T t);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 条件查询
	 * 
	 * @param condition 拼接后的条件
	 * @param params 条件中的参数
	 * @return
	 */
	List<T> findAll(String condition, Object[] params);

	/**
	 * 分页+条件查询
	 * 
	 * @param condition
	 *            拼接后的条件
	 * @param params
	 *            条件中的参数
	 * @param startIndex
	 *            开始索引
	 * @param pageSize
	 *            每页行数
	 * @return
	 */
	List<T> findAll(String condition, Object[] params, int startIndex, int pageSize);

	/**
	 * 通过主键查询
	 * 
	 * @param id
	 * @return
	 */
	T findById(Serializable id);

	/**
	 * 查询总记录数
	 * 
	 * @param condition
	 * @param params
	 * @return
	 */
	int getTotalRecord(String condition, Object[] params);

	/**
	 * 查询总记录数
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	int getTotalRecord(DetachedCriteria criteria);

	/**
	 * 离线条件查询，使用QBC
	 * 
	 * @param criteria
	 * @return
	 */
	List<T> findAll(DetachedCriteria criteria);

	/**
	 * 使用Criteria进行分页查询
	 * 
	 * @param criteria
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<T> findAll(DetachedCriteria criteria, int startIndex, int pageSize);

	/**
	 * 对Criteria分页查询进行二次封装
	 * 
	 * @param pageBean
	 */
	void findAll(PageBean<T> pageBean);

	/**
	 * 通用更新方法
	 * 
	 * @param hql 更新语句
	 * @param params 更新语句中的参数
	 */
	void update(String hql, Object[] params);

	/**
	 * 通用分页查询方法
	 * 
	 * @param pageBean
	 */
	// void pageQuery(PageBean<T> pageBean);
}
