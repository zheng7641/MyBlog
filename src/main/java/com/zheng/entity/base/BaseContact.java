package com.zheng.entity.base;

import java.io.Serializable;
import com.zheng.base.BaseBean;


public class BaseContact extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 回复id
	*/
	private Integer id ;
	/**
	* 回复人昵称
	*/
	private String name ;
	/**
	* 箱邮
	*/
	private String email ;
	/**
	* 题标
	*/
	private String title ;
	/**
	* 内容
	*/
	private String content ;


	/**
	* 回复id
	*/
	public Integer getId() {
			return this.id;
		}
	/**
	* 回复id
	*/
	public void setId(Integer id) {
			this.id = id;
		}
	/**
	* 回复人昵称
	*/
	public String getName() {
			return this.name;
		}
	/**
	* 回复人昵称
	*/
	public void setName(String name) {
			this.name = name;
		}
	/**
	* 箱邮
	*/
	public String getEmail() {
			return this.email;
		}
	/**
	* 箱邮
	*/
	public void setEmail(String email) {
			this.email = email;
		}
	/**
	* 题标
	*/
	public String getTitle() {
			return this.title;
		}
	/**
	* 题标
	*/
	public void setTitle(String title) {
			this.title = title;
		}
	/**
	* 内容
	*/
	public String getContent() {
			return this.content;
		}
	/**
	* 内容
	*/
	public void setContent(String content) {
			this.content = content;
		}
}