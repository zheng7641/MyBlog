package com.zheng.logic;

import com.zheng.base.page.PageList;
import com.zheng.entity.Tag;
import com.zheng.base.BaseService;

public interface TagService extends BaseService{

    public PageList<Tag> listTag(Integer pageSize,Integer currentPage);
}
