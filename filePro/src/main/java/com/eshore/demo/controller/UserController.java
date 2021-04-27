package com.eshore.demo.controller;

import com.eshore.demo.common.ResultJson;
import com.eshore.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ResultJson userLogin(HttpServletRequest request){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        ResultJson resultJson = userService.userLogin(name, password);
        return resultJson;
    }

    @GetMapping("/getUsers")
    @ResponseBody
    public ResultJson getUsers(){
        System.out.println("getusers");
        return userService.queryUsers();
    }
}
