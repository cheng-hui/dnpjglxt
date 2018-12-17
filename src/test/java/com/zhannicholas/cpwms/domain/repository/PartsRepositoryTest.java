package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Parts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartsRepositoryTest {
    @Autowired
    PartsRepository partsRepository;

    @Test
    public void findByPartsNameContaining(){
        Pageable pageable = PageRequest.of(0, 5);
        String partsName = "Intel";
        Page<Parts> partsPage = partsRepository.findByPartsNameContaining(partsName, pageable);
        System.out.println(partsPage.getContent());
    }
}