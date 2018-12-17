package com.zhannicholas.cpwms.web.controller;

import com.zhannicholas.cpwms.domain.model.User;
import com.zhannicholas.cpwms.service.UserService;
import com.zhannicholas.cpwms.util.AccountStatus;
import com.zhannicholas.cpwms.util.Response;
import com.zhannicholas.cpwms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {
    private static final String USER_ID = "id";
    private static final String USER_NAME = "userName";
    private static final String USER_PASSWORD = "password";

    private final UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("account")
    @ResponseBody
    public String showAllUsers(){
        return userService.findAll().toString();
    }

    /**
     * 登陆
     *
     * @param loginUser    用户信息
     * @param request HttpServletRequest
     * @return 返回一个 Map 对象，其中包含登陆操作的结果
     */
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> signIn(@RequestBody User loginUser, HttpServletRequest request) {
        System.out.println(loginUser + "登录-------");
        String result = Response.RESPONSE_RESULT_ERROR;
        String errorMsg = "";

        User user = userService.findOne(loginUser.getId());
        if(user == null){
            errorMsg = "unknownAccount";
        }
        else if(!user.getPassword().equals(loginUser.getPassword())){
            errorMsg = "incorrectPassword";
        }
        else{
            System.out.println(loginUser.getUsername() + ": 登录成功");
            // 获取 session
            HttpSession session = request.getSession();
            session.setAttribute("userID", loginUser.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("account_status", AccountStatus.SIGN_IN);
            result = Response.RESPONSE_RESULT_SUCCESS;
        }



        // 初始化 Response
        Response responseContent = ResponseUtil.newResponseInstance();
        // 设置 Response
        responseContent.setResponseResult(result);
        responseContent.setResponseMsg(errorMsg);
        return responseContent.generateResponse();
    }
}
