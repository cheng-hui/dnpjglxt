package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Customer;
import com.zhannicholas.cpwms.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void findAll() {
        System.out.println(customerService.findAll());
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setCustomerName("贵阳小爱机器人科技有限公司");
        customer.setCustomerPerson("谢光莲");
        customer.setCustomerTel("574935749");
        customer.setCustomerEmail("monica@xiaoi.com");
        customer.setCustomerAddress("贵州省贵阳市观山湖区贵州金融城");

        customerService.save(customer);
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findByCustomerName() {
        System.out.println(customerService.findByCustomerName("有限公司"));
    }

    @Test
    public void delete() {

    }
}