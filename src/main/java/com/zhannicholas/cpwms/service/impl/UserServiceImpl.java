package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.User;
import com.zhannicholas.cpwms.domain.repository.UserRepository;
import com.zhannicholas.cpwms.service.UserService;
import com.zhannicholas.cpwms.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.zhannicholas.cpwms.util.Constants.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean save(User user) {
        if (isValidUser(user)) {
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }

    @Override
    public User findOne(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public boolean delete(int userId) {
        if(isValidUserId(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> modifyPassword(int userId, String oldPassword, String newPassword, String rePassword) {
        if(oldPassword == null || oldPassword.length() == 0 ||
                newPassword == null || newPassword.length() == 0 ||
                rePassword == null || rePassword.length() == 0){
            return generateResponse(RESULT_ERROR, PASSWORD_ERROR);
        }
        if(!newPassword.equals(rePassword)){   // 密码不一致
            return generateResponse(RESULT_ERROR, PASSWORD_UNMATCH);
        }

        User user = userRepository.findById(userId);
        try {
            // 原密码不正确
            if(!user.getPassword().equals(EncryptUtil.MD5(oldPassword + userId))){
                return generateResponse(RESULT_ERROR, PASSWORD_ERROR);
            }
            // 获取新的密码并加密
            user.setPassword(EncryptUtil.MD5(newPassword + userId));
            save(user);
            return generateResponse(RESULT_SUCCESS, NONE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generateResponse(RESULT_ERROR, SERVER_ERROR);
    }

    private boolean isValidUser(User user){
        if(user != null){
            return user.getUsername() != null &&
                    user.getPassword() != null;
        }
        return false;
    }

    private boolean isValidUserId(int userId){
        return userRepository.findById(userId) != null;
    }

    private Map<String, Object> generateResponse(String result, String errorMsg){
        Map<String, Object> response = new HashMap<>();
        response.put(RESPONSE_RESULT, result);
        response.put(RESPONSE_MSG, errorMsg);

        return response;
    }
}
