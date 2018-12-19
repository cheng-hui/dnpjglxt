package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Customer;
import com.zhannicholas.cpwms.domain.repository.CustomerRepository;
import com.zhannicholas.cpwms.service.CustomerService;
import com.zhannicholas.cpwms.util.ToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return ToMapUtil.fromAPage(customerPage);
    }

    @Override
    public boolean save(Customer customer) {
        if(isValidCustomer(customer) &&
                countCustomerName(customer.getCustomerName()) < 1){
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> findOne(int customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        return ToMapUtil.fromAInstance(customer);
    }

    @Override
    public Map<String, Object> findByCustomerNameContaining(String customerName, Pageable pageable) {
        if(pageable == null){
            return findAllByCustomerNameContaining(customerName);
        }
        Page<Customer> customerPage = customerRepository.findByCustomerNameContaining(customerName, pageable);
        return ToMapUtil.fromAPage(customerPage);
    }

    @Override
    public boolean delete(int customerId) {
        if(isValidCustomerId(customerId)){
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> findAllByCustomerNameContaining(String customerName) {
        return ToMapUtil.fromAList(customerRepository.findAllByCustomerNameContaining(customerName));
    }

    @Override
    public int countCustomerName(String customerName) {
        return customerRepository.countByCustomerName(customerName);
    }

    /**
     * 检查客户信息是否满足要求
     * @param customer  客户
     * @return  若客户信息满足要求则返回true，否则返回false
     */
    public boolean isValidCustomer(Customer customer){
        if(customer != null){
            return customer.getCustomerName() != null &&
                    customer.getCustomerPerson() != null &&
                    customer.getCustomerTel() != null &&
                    customer.getCustomerEmail() != null &&
                    customer.getCustomerAddress() != null;
        }
        return false;
    }

    /**
     * 检查 customer 是否合法
     * @param customerId    客户id
     * @return  若客户id合法则返回true,否则返回false
     */
    public boolean isValidCustomerId(int customerId){
        return customerRepository.findByCustomerId(customerId) != null;
    }
}
