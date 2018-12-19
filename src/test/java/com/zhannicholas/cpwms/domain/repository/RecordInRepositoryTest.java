package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordInRepositoryTest {
    @Autowired
    private RecordInRepository recordInRepository;

    @Test
    public void testSave(){
        recordInRepository.saveRecord(1019, 130, 1001, DateUtil.fromUtilDate(), "詹伟伟", 1006);
    }

}