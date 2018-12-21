package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    boolean save(User user);
    User findOne(int userId);
    boolean delete(int userId);
    Map<String, Object> modifyPassword(int userId, String oldPassword, String newPassword, String rePassword);
}
