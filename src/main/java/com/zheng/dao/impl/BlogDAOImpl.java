package com.zheng.dao.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zheng.dao.BlogDAO;
import com.zheng.base.BaseDAOImpl;
import com.zheng.base.BaseMapper;
import com.zheng.mapper.BlogMapper;
import com.zheng.entity.Blog;

@Repository
public class BlogDAOImpl extends BaseDAOImpl<Blog,String> implements BlogDAO {

	@Autowired
	private BlogMapper blogMapper;

	@Override
	public BaseMapper<Blog, String> getMapper() {
		return blogMapper;
	}

	@Override
	public String hello() {
		return "hellow";
	}
}
