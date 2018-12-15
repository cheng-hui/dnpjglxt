package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.User;
import com.zhannicholas.cpwms.domain.repository.UserRepository;
import com.zhannicholas.cpwms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findOne(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void delete(int userId) {
        userRepository.delete(findOne(userId));
    }
}
