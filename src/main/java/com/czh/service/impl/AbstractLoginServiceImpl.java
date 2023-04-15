package com.czh.service.impl;

import com.czh.service.AbstractLoginService;
import com.czh.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 封装登录接口
 */
public abstract class AbstractLoginServiceImpl<T> implements AbstractLoginService<T> {
    public Logger logger = LoggerFactory.getLogger(getClass());
}
