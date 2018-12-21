package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordInRepositoryTest {
    @Autowired
    private RecordInRepository recordInRepository;

    @Test
    public void findUsingRepoIdAndDate(){
        System.out.println(
                recordInRepository.findUsingRepoIdAndDate(
                        1004,
                        DateUtil.fromDateStr("2016-12-30", false),
                        DateUtil.fromDateStr("2018-12-26", true),
                        PageRequest.of(0, 5)
                ).getContent()
        );
    }

    @Test
    public void findUsingDate(){
        System.out.println(
                recordInRepository.findUsingDate(
                        DateUtil.fromDateStr("2016-12-30", false),
                        DateUtil.fromDateStr("2018-12-26", true),
                        PageRequest.of(0, 5)
                ).getContent()
        );
    }
}