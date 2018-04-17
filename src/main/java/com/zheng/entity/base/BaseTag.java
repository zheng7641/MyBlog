package com.zheng.entity.base;

import java.io.Serializable;
import com.zheng.base.BaseBean;


public class BaseTag extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 博客标签id
	*/
	private Integer id ;
	/**
	* 标签名
	*/
	private String name ;


	/**
	* 博客标签id
	*/
	public Integer getId() {
			return this.id;
		}
	/**
	* 博客标签id
	*/
	public void setId(Integer id) {
			this.id = id;
		}
	/**
	* 标签名
	*/
	public String getName() {
			return this.name;
		}
	/**
	* 标签名
	*/
	public void setName(String name) {
			this.name = name;
		}
}