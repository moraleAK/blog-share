package com.canno.blog.controller;

import com.canno.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Canno
 * @since 2018/6/28 14:49
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/add")
    public String addUser(@RequestParam String loginName, @RequestParam String password, @RequestParam String userName){
        System.out.println();
        return String.valueOf(userService.addUser(userName,password,loginName));
    }
}
