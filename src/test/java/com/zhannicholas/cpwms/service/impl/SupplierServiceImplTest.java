package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Supplier;
import com.zhannicholas.cpwms.service.SupplierService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierServiceImplTest {
    @Autowired
    private SupplierService supplierService;

    @Test
    public void findAll() {
        System.out.println(supplierService.findAll());
    }

    @Test
    public void save() {
        Supplier supplier = new Supplier();
        supplier.setSupplierName("Intel");
        supplier.setSupplierPerson("Jack");
        supplier.setSupplierTel("534957349");
        supplier.setSupplierEmail("Jack@Intel.com");
        supplier.setSupplierAddress("中国 广州");
        supplierService.save(supplier);
    }

    @Test
    public void findOne() {
        System.out.println(supplierService.findOne(1017));
    }

    @Test
    public void findBySupplierName() {
        System.out.println(supplierService.findBySupplierName("Intel"));
    }

    @Test
    public void delete() {

    }
}