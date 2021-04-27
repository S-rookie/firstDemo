package com.eshore.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eshore.demo.common.ResultJson;
import com.eshore.demo.entity.User;
import com.eshore.demo.mapper.UserMapper;
import com.eshore.demo.service.UserService;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eshore
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ResultJson userLogin(String name , String password) {
        ResultJson rs = new ResultJson();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        wrapper.eq("password",password);
        List<User> users = userMapper.selectList(wrapper);
        if (users.size() != 0){
            rs.setCode(ResultJson.SUCCESS_CODE);
            String s = JSONArray.toJSONString(users);
            rs.setResponseEntity(s);
        } else {
            rs.setCode(ResultJson.ERROR_CODE);
        }
        return rs;
    }

    @Override
    public ResultJson queryUsers() {
        ResultJson rs = new ResultJson();
        List<User> users = userMapper.selectList(null);
        if (users != null){
            rs.setCode(ResultJson.SUCCESS_CODE);
            rs.setResponseEntity(users);
        }
        return rs;
    }
}
