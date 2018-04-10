package com.zheng.service;

import com.zheng.dao.BlogDAOImpl;
import com.zheng.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

	@Autowired
	private BlogDAOImpl blogDAOImpl ;

	public Blog selectByPrimaryKey(int blogId){
		Blog blog = blogDAOImpl.getBlogById(blogId);
		if(blog==null){
			return null;
		}
		return blog;
	}

//	public List<Blog> selectBlogList(Blog entity, Page page){
//		List<Blog> blogList = new ArrayList<Blog>();
//		for(Blog blog:blogList){
//			blog.setTag(TagDao.selectListByBlogId(entity.getBlogid()));
//		}
//		return null;
//	}
}
