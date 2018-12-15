package com.zhannicholas.cpwms.web;

import com.zhannicholas.cpwms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("users")
    @ResponseBody
    public String showAllUsers(){
        return userService.findAll().toString();
    }
}
