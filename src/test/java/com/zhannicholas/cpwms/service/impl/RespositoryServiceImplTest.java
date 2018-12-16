package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.service.RespositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RespositoryServiceImplTest {
    @Autowired
    RespositoryService respositoryService;

    @Test
    public void findAll() {
        System.out.println(respositoryService.findAll());
    }

    @Test
    public void save() {
        Respository respository = new Respository();
        respository.setRepoAddress("贵州省贵阳市花溪区");
        respository.setRepoStatus("可用");
        respository.setRepoArea("5000平米");
        respository.setRepoDesc("大容量，交通不太方便");
        respositoryService.save(respository);
    }

    @Test
    public void findOne() {
        System.out.println(respositoryService.findOne(1003));
    }

    @Test
    public void findByRepoAddress() {
        System.out.println(respositoryService.findByRepoAddress("花溪"));
    }

    @Test
    public void delete() {

    }
}