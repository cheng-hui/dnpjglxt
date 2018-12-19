package com.zhannicholas.cpwms.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageRepositoryTest {
    @Autowired
    private StorageRepository storageRepository;

    @Test
    public void findById(){
        System.out.println(storageRepository.findNumberById(103, 1005));
    }

    @Test
    public void saveStorage(){
        storageRepository.saveStorage(126, 1006, 1111);
    }
}