package com.zheng.mapper;

import com.zheng.entity.Image;

public interface ImageMapper {
    int deleteByPrimaryKey(Integer imgid);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer imgid);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}