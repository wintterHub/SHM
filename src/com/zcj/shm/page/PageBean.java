package com.zcj.shm.page;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 封装分页信息
 * 
 * @author Changjin.Zhao
 * @param <T>
 */
public class PageBean<T> {

	/**
	 * 前三个为必填项
	 */
	private int currentPage; // 当前页
	private int pageSize; // 每页显示个数
	private DetachedCriteria detachedCriteria;// 离线条件查询对象，包装查询条件

	private String sort;
	private String order;

	private int rowCount; // 总数据行
	private int total; // 总数据行(EasyUI使用)
	private int pageCount; // 总页数
	private List<T> dataRows; // 分页数据
	private List<T> rows; // 分页数据(EasyUI使用)

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	public List<T> getDataRows() {
		return dataRows;
	}

	public void setDataRows(List<T> dataRows) {
		this.dataRows = dataRows;
	}

	public List<T> getRows() {
		return dataRows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return rowCount;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", sort=" + sort + ", order=" + order + ", rowCount="
				+ rowCount + ", total=" + total + ", pageCount=" + pageCount + "]";
	}

}
