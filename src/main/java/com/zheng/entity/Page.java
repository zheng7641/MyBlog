package com.zheng.entity;

public class Page {

	private Integer pageSize;
	private Integer pageNum;
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", pageNum=" + pageNum + "]";
	}
	
	
}
