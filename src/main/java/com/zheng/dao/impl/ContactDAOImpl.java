package com.zheng.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zheng.dao.ContactDAO;
import com.zheng.base.BaseDAOImpl;
import com.zheng.base.BaseMapper;
import com.zheng.mapper.ContactMapper;
import com.zheng.entity.Contact;

@Repository
public class ContactDAOImpl extends BaseDAOImpl<Contact,String> implements ContactDAO {

	@Resource
	private ContactMapper contactMapper;

	@Override
	public BaseMapper<Contact, String> getMapper() {
		return contactMapper;
	}
	
}
