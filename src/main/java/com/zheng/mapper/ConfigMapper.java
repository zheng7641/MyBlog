package com.zheng.mapper;

import com.zheng.entity.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(Integer configid);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer configid);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}