package com.zheng.logic.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zheng.dao.ContactDAO;
import com.zheng.base.BaseDAO;
import com.zheng.entity.Contact;
import com.zheng.logic.ContactService;
import com.zheng.base.BaseServiceImpl;

@Service
public class ContactServiceImpl extends BaseServiceImpl  implements ContactService{
	@Resource
	private ContactDAO contactDAO;
	
	public BaseDAO<Contact, String> getBaseDAO() {
		return contactDAO;
	}

}