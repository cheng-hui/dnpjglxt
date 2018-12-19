package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    Map<String, Object> findAll(Pageable pageable);
    boolean save(Customer customer);
    Map<String, Object> findOne(int customerId);
    Map<String, Object> findByCustomerNameContaining(String customerName, Pageable pageable);
    boolean delete(int customerId);
    Map<String, Object> findAllByCustomerNameContaining(String customerName);
    boolean isValidCustomer(Customer customer);
    boolean isValidCustomerId(int customerId);
}
