package com.eshore.demo.service;

import com.eshore.demo.common.ResultJson;
import com.eshore.demo.entity.User;

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
    public User userLogin(String name , String password);

    /**
     * 查询所有
     * @return
     */
    public ResultJson queryUsers();
}
