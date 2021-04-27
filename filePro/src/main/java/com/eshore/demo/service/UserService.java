package com.eshore.demo.service;

import com.eshore.demo.common.ResultJson;

/**
 * @author eshore
 */
public interface UserService {

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    public ResultJson userLogin(String name , String password);

    /**
     * 查询所有
     * @return
     */
    public ResultJson queryUsers();
}
