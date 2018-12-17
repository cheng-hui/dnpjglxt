package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.RecordIn;
import com.zhannicholas.cpwms.domain.repository.RepoRepository;
import com.zhannicholas.cpwms.service.PartsService;
import com.zhannicholas.cpwms.service.RecordInService;
import com.zhannicholas.cpwms.service.RespositoryService;
import com.zhannicholas.cpwms.service.SupplierService;
import com.zhannicholas.cpwms.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordInServiceImplTest {
    @Autowired
    private RecordInService recordInService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private PartsService partsService;
    @Autowired
    private RespositoryService respositoryService;

    @Test
    public void findAll() {
        System.out.println(recordInService.findAll());
    }

    @Test
    public void findBySupplier() {
    }

    @Test
    public void findByParts() {
    }

    @Test
    public void findByRepository() {
    }

    @Test
    public void findOne() {
    }

    @Test
    public void save() {
        RecordIn recordIn = new RecordIn();
        recordIn.setRecordNumber(1);
        recordIn.setRecordPerson("詹伟伟");
        recordIn.setRecordTime(DateUtil.fromUtilDate());
        recordIn.setSupplier(supplierService.findBySupplierName("小米"));
        //recordIn.setParts(partsService.findByPartsName("小米8").get(0));
        recordIn.setRepository(respositoryService.findOne(1006));

        recordInService.save(recordIn);
    }

    @Test
    public void delete() {
    }
}