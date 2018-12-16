package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Customer;
import com.zhannicholas.cpwms.domain.repository.CustomerRepository;
import com.zhannicholas.cpwms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findOne(int customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Customer> findByCustomerName(String customerName) {
        return customerRepository.findByCustomerNameContaining(customerName);
    }

    @Override
    public void delete(int customerId) {
        customerRepository.deleteById(customerId);
    }
}
