package com.zheng.base;

import java.io.Serializable;


/**
 *此类是基本类（对应数据库表，自动生成的，不允许修—要修改请修改子类）
 */
public abstract class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uid ;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}




}