package com.dream.service;

import com.dream.service.interfaces.TestService;

/**
 * 描述信息
 *
 * @author dream
 * @create 2022-07-23
 */
public class TestServiceImpl implements TestService {

    @Override
    public boolean existInAllowList() {
        return false;
    }
}
