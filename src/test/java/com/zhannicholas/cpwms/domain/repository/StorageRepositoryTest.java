package com.zhannicholas.cpwms.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageRepositoryTest {
    @Autowired
    private StorageRepository storageRepository;

    @Test
    public void findById(){
        System.out.println(storageRepository.findStorageDTO(103, 1005));
    }

    @Test
    public void saveStorage(){
        storageRepository.saveStorage(126, 1006, 1111);
    }

    @Test
    public void findByPartsName(){
        System.out.println(
                storageRepository.findUsingPartsName(
                        1005, "五", PageRequest.of(0, 5)
                ).getContent()
        );
    }

    @Test
    public void findByPartsType(){
        System.out.println(
                storageRepository.findUsingPartsType(
                        1005, "电器", PageRequest.of(0, 5)
                ).getContent()
        );
    }

    @Test
    public void findAll(){
        System.out.println(
                storageRepository.findAllStorageDTO(
                        1005, PageRequest.of(0, 5)
                ).getContent()
        );
    }

    @Test
    public void findById1(){
        System.out.println(
                storageRepository.findStorageDTO(103, PageRequest.of(0, 5))
                        .getContent()
        );
    }
}