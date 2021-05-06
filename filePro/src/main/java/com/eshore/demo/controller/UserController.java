package com.eshore.demo.controller;

import com.alibaba.fastjson.JSON;
import com.eshore.demo.common.ResultJson;
import com.eshore.demo.entity.User;
import com.eshore.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author eshore
 */
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userLogin")
    @ResponseBody
    public ResultJson userLogin(HttpServletRequest request,HttpSession session){
        ResultJson rs = new ResultJson();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = userService.userLogin(name, password);
        if (user != null){
            rs.setCode(ResultJson.SUCCESS_CODE);
            rs.setResponseEntity(JSON.toJSON(user));
            Cookie[] cookies = request.getCookies();
//            session.
            if (session.getAttribute("loginUser") == null) {
                session.setAttribute("loginUser", user);
            }
        }
        return rs;
    }

    @GetMapping("/getUsers")
    @ResponseBody
    public ResultJson getUsers(){
        userService.queryUsers();
        return userService.queryUsers();
    }
}
