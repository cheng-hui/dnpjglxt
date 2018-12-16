package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    void save(Customer customer);
    Customer findOne(int customerId);
    List<Customer> findByCustomerName(String customerName);
    void delete(int customerId);
}
