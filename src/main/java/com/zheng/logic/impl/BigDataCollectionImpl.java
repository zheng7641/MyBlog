package com.zheng.logic.impl;

import com.zheng.controller.IndexController;
import com.zheng.logic.BigDataCollection;
import com.zheng.model.ActionInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author zhengct on 2018/5/14
 */
public class BigDataCollectionImpl implements BigDataCollection {

    private static Logger logger = LogManager.getLogger(BigDataCollectionImpl.class.getName());

    @Override
    public void DataImport(ActionInfo actionInfo) {
        logger.trace(actionInfo.toString());
    }
}
