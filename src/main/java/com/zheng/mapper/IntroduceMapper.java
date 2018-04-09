package com.zheng.mapper;

import com.zheng.entity.Introduce;

public interface IntroduceMapper {
    int deleteByPrimaryKey(Integer introduceid);

    int insert(Introduce record);

    int insertSelective(Introduce record);

    Introduce selectByPrimaryKey(Integer introduceid);

    int updateByPrimaryKeySelective(Introduce record);

    int updateByPrimaryKeyWithBLOBs(Introduce record);
}