package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.User;
import com.zhannicholas.cpwms.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void findAll() {
        System.out.println(userService.findAll());
    }

    @Test
    public void save() {
        User user = new User();
        user.setUsername("詹伟伟");
        user.setPassword("123456");
        userService.save(user);
    }

    @Test
    public void findOne() {
        System.out.println(userService.findOne(100111));
    }

    @Test
    public void findByUsername(){
        System.out.println(userService.findByUsername("詹伟伟"));
    }

    @Test
    public void delete() {
        userService.delete(userService.findByUsername("詹伟伟").getId());
    }
}