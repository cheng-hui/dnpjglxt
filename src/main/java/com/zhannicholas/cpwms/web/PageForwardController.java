package com.zhannicholas.cpwms.web;

import com.zhannicholas.cpwms.util.AccountStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PageForwardController {
    @RequestMapping("")
    public String showLoginView(){
        return "login";
    }

    @RequestMapping("mainPage")
    public String showMainPage(HttpServletRequest request){
        return "mainPage";
        // 为了方便测试，先注释登录代码
//        HttpSession session = request.getSession();
//        if(session.getAttribute("userID") != null &&
//            session.getAttribute("account_status").equals(AccountStatus.SIGN_IN)){
//            return "mainPage";
//        }
//        return "login";
    }
}
