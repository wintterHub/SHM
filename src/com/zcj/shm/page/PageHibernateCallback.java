package com.zcj.shm.page;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {

	private String hql;
	private Object[] params;
	private int startIndex;
	private int pageSize;

	public PageHibernateCallback(String hql, Object[] params, int startIndex, int pageSize) {
		super();
		this.hql = hql;
		this.params = params;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}

	@Override
	public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
		Query queryObject = session.createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			queryObject.setParameter(i, params[i]);
		}
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(pageSize);
		return queryObject.list();
	}

}
