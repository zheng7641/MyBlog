package com.zheng.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zheng.dao.TagDAO;
import com.zheng.base.BaseDAOImpl;
import com.zheng.base.BaseMapper;
import com.zheng.mapper.TagMapper;
import com.zheng.entity.Tag;

@Repository
public class TagDAOImpl extends BaseDAOImpl<Tag,String> implements TagDAO {

	@Resource
	private TagMapper tagMapper;

	@Override
	public BaseMapper<Tag, String> getMapper() {
		return tagMapper;
	}
	
}
