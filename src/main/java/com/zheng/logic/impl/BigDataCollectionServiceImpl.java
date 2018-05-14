package com.zheng.logic.impl;

import com.zheng.logic.BigDataCollectionService;
import com.zheng.model.ActionInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author zhengct on 2018/5/14
 */
public class BigDataCollectionServiceImpl implements BigDataCollectionService {

    private static Logger logger = LogManager.getLogger(BigDataCollectionServiceImpl.class.getName());

    @Override
    public void DataImport(ActionInfo actionInfo) {
        logger.info(actionInfo.toString());
    }
}
