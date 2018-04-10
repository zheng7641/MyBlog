package com.zheng.base.page;

import com.zheng.base.BaseConstants;

/**
 * 分页基础类
 */
public class Page {
	/**
	 * 每页件数
	 */
	private int pageSize = 10;
	/**
	 * 当前页数
	 */
	private int currentPage = 1;
	/**
	 * 是否查询总件数
	 */
	private boolean needTotal = true;
	/**
	 * 总件数
	 */
	private int totalCount;

	private int startPosition=0;

	public Page() {
	}

	public Page(int pageNum) {
		if (pageNum > 1) {
			this.currentPage = pageNum;
		}
		this.startPosition = (this.currentPage-1)*this.pageSize;
	}

	public Page(int pageSize, int pageNum) {
		if (pageSize > 0 && pageSize <= BaseConstants.MAX_PAGE_SIZE) {
			this.pageSize = pageSize;
		}
		if (pageNum > 1) {
			this.currentPage = pageNum;
		}
		this.startPosition = (this.currentPage-1)*this.pageSize;
	}

	public Page(int pageSize, int pageNum, boolean needTotal) {
		if (pageSize > 0 && pageSize <= BaseConstants.MAX_PAGE_SIZE) {
			this.pageSize = pageSize;
		}
		if (pageNum > 1) {
			this.currentPage = pageNum;
		}
		this.startPosition = (this.currentPage-1)*this.pageSize;
		this.needTotal = needTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize > BaseConstants.MAX_PAGE_SIZE ? BaseConstants.MAX_PAGE_SIZE : pageSize;
		this.startPosition = (this.currentPage-1)*this.pageSize;
	}

	public boolean isNeedTotal() {
		return needTotal;
	}

	public void setNeedTotal(boolean needTotal) {
		this.needTotal = needTotal;
	}

	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage < 1 ? 1 : currentPage;
		this.startPosition = (this.currentPage-1)*this.pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public String toString() {
		return new StringBuilder("[pageSize:").append(pageSize).append(",currentPage:").append(currentPage)
				.append(",totalRecord:").append(totalCount).append("]").toString();
	}

}
