package com.zhannicholas.cpwms.controller;

import com.zhannicholas.cpwms.domain.model.User;
import com.zhannicholas.cpwms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.zhannicholas.cpwms.util.Constants.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登陆
     *
     * @param loginUser    用户信息
     * @param request HttpServletRequest
     * @return 返回一个 Map 对象，其中包含登陆操作的结果
     */
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> signIn(@RequestBody User loginUser, HttpServletRequest request) {
        System.out.println(loginUser + "登录-------");
        String result = RESULT_ERROR;
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
            session.setAttribute(USER_ID, loginUser.getId());
            session.setAttribute(USER_NAME, user.getUsername());
            session.setAttribute(ACCOUNT_STATUS, SIGN_IN);
            result = RESULT_SUCCESS;
        }

        Map<String, Object> response = new HashMap<>();
        response.put(RESPONSE_RESULT, result);
        response.put(RESPONSE_MSG, errorMsg);

        return response;
    }

    /**
     * 注销登陆
     */
    @RequestMapping(value = "signOut", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> signOut(HttpServletRequest request) {
        System.out.println("注销");
        Map<String, String> resultSet = new HashMap<>();
        // do something when sign out

        HttpSession session = request.getSession();
        session.setAttribute(USER_ID, NONE);
        session.setAttribute(USER_NAME, NONE);
        session.setAttribute(ACCOUNT_STATUS, SIGN_OUT);

        resultSet.put(RESPONSE_RESULT, RESULT_SUCCESS);
        return resultSet;
    }

    /**
     * 修改账户密码
     * @param request      请求
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param rePassword 确认的新密码
     * @return 返回一个 Map 对象，其中键值为 result 代表修改密码操作的结果，
     * 值为 success 或 error；键值为 msg 代表需要返回给用户的信息
     */
    @ResponseBody
    @RequestMapping(value = "passwordModify", method = RequestMethod.POST)
    public Map<String, Object> passwordModify(@RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword,
                                       @RequestParam("rePassword") String rePassword,
                                       HttpServletRequest request) {
        // 获取用户 ID
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");
        return userService.modifyPassword(userID, oldPassword, newPassword, rePassword);
    }

}
