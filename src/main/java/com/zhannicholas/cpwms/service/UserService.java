package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void save(User user);
    User findOne(int userId);
    User findByUsername(String username);
    void delete(int userId);
}
