package com.zcj.shm.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zcj.shm.base.dao.IBaseDao;
import com.zcj.shm.page.PageBean;
import com.zcj.shm.page.PageHibernateCallback;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> beanClass;

	/*
	 * 使用注解方式为HibernateDaoSupport注入SessionFactory
	 * 但是HibernateDaoSupport中的setSessionFactory为final，不允许覆盖，所以此处解决方式为，再包装一层。
	 */
	@Resource // 默认地，拥有与@Resource注解所提供名字相匹配的“bean name”的Spring管理对象会被注入。
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public BaseDaoImpl() {
		// 获得运行时的类型，BaseDaoImpl<>被参数化的类型
		ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获得实际参数，获得所有
		beanClass = (Class<T>) paramType.getActualTypeArguments()[0];
	}

	@Override
	public String save(T t) {
		// this.getHibernateTemplate().getSessionFactory().getCurrentSession().close();
		return this.getHibernateTemplate().save(t).toString();
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from " + beanClass.getName());
	}

	@Override
	public List<T> findAll(String condition, Object[] params) {
		String hql = "from " + beanClass.getName() + " where 1=1 " + condition;
		System.out.println(params[0]);
		return (List<T>) this.getHibernateTemplate().find(hql, params);
	}

	@Override
	public List<T> findAll(String condition, Object[] params, int startIndex, int pageSize) {
		// TODO 可能报错
		String hql = "from " + beanClass.getName() + " where 1=1 " + condition;
		return (List<T>) this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, params, startIndex, pageSize));
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(beanClass, id);
	}

	@Override
	public int getTotalRecord(String condition, Object[] params) {
		String hql = "select count(*) from " + beanClass.getName() + " where 1=1 " + condition;
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, params);
		return list.get(0).intValue();
	}

	@Override
	public int getTotalRecord(DetachedCriteria criteria) {
		// DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		// 相当于在查询语句前加"select count(*);
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		return list.get(0).intValue();
	}

	@Override
	public List<T> findAll(DetachedCriteria criteria) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<T> findAll(DetachedCriteria criteria, int startIndex, int pageSize) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria, startIndex, pageSize);
	}

	@Override
	public void findAll(PageBean<T> pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		// 改变Hibernate框架发出的sql形式,相当于select count(*) from table
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		int rowCount = list.get(0).intValue();
		// 设置总数据行
		pageBean.setRowCount(rowCount);
		// 修改sql的形式为select * from ....
		detachedCriteria.setProjection(null);
		// 重置表和类的映射关系
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		// 当前页展示的数据集合
		int firstResult = (currentPage - 1) * pageSize;
		int maxResults = pageSize;
		List<T> dataRows = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setDataRows(dataRows);
	}

	/**
	 * 通用分页查询方法
	 */
	// @Override
	// public void pageQuery(PageBean<T> pageBean) {
	// int currentPage = pageBean.getCurrentPage();
	// int pageSize = pageBean.getPageSize();
	// DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
	// // 总数据量----select count(*) from bc_staff
	// // 改变Hibernate框架发出的sql形式
	// detachedCriteria.setProjection(Projections.rowCount());// select
	// // count(*) from
	// // bc_staff
	// List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	// // String hql = "select count(*) from " + beanClass.getName();
	// // List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
	// // System.out.println(list);
	// int rowCount = list.get(0).intValue();
	// pageBean.setRowCount(rowCount);// 设置总数据行
	// detachedCriteria.setProjection(null);// 修改sql的形式为select * from ....
	// // 重置表和类的映射关系
	// detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
	// // 当前页展示的数据集合
	// int firstResult = (currentPage - 1) * pageSize;
	// int maxResults = pageSize;
	// List<T> dataRows = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
	// pageBean.setDataRows(dataRows);
	// }

	@Override
	public void update(String hql, Object[] objects) {
		Session session = this.getSession();// 从本地线程中获得session对象
		Query query = session.createQuery(hql);
		// 为HQL语句中的？赋值
		int i = 0;
		for (Object arg : objects) {
			query.setParameter(i++, arg);
		}
		query.executeUpdate();// 执行更新
	}

}
