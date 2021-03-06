package com.zheng.mapper;

import com.zheng.base.BaseMapper;
import com.zheng.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog, String> {

    List<Blog> searchBlog(@Param("key") String key, @Param("start") int start, @Param("end") int end);

    int searchBlogCount(@Param("key") String key);

    Integer deleteById(@Param("id")int id);
}