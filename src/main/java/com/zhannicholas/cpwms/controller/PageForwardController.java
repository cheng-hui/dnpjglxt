package com.zhannicholas.cpwms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.zhannicholas.cpwms.util.Constants.*;

@Controller
@RequestMapping("/")
public class PageForwardController {
    @RequestMapping("")
    public String showLoginView(){
        return "login";
    }

    @RequestMapping("mainPage")
    public String showMainPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute(USER_ID) != null &&
            session.getAttribute(ACCOUNT_STATUS).equals(SIGN_IN)){
            return "mainPage";
        }
        return "login";
    }
}
