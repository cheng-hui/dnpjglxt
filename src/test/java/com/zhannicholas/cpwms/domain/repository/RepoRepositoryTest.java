package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Respository;
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
public class RepoRepositoryTest {
    @Autowired
    private RepoRepository repoRepository;

    @Test
    public void findAll(){
        Pageable pageable = PageRequest.of(0, 5);
        Page<Respository> respositoryPage = repoRepository.findAll(pageable);
        for(Respository respository: respositoryPage.getContent()) {
            System.out.println(respository);
        }
    }

    @Test
    public void findAllUnassigned(){
        System.out.println(repoRepository.findUnassignedRepo());
    }

}