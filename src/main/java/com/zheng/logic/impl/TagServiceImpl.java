package com.zheng.logic.impl;

import com.zheng.base.BaseServiceImpl;
import com.zheng.base.page.OrderBean;
import com.zheng.base.page.Page;
import com.zheng.base.page.PageList;
import com.zheng.dao.TagDAO;
import com.zheng.entity.Tag;
import com.zheng.logic.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TagServiceImpl extends BaseServiceImpl implements TagService {

    @Resource
    private TagDAO tagDao;

    @Override
    public PageList<Tag> listTag(Integer pageSize,Integer currentPage) {
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        PageList<Tag> tagPageList = tagDao.findByExamplePage(new Tag(), page, new OrderBean());
        page.setTotalCount(tagDao.getCount(new Tag()));
        tagPageList.setPage(page);
        return tagPageList;
    }
}
