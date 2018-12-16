package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    /**
     * 根据 customerId 查询客户
     * @param customerId    客户id
     * @return  具有 customerId 的客户
     */
    Customer findByCustomerId(int customerId);

    /**
     * 根据 customerName 查询客户---模糊查询
     * @param customerName  客户名称
     * @return  和 customerName 匹配的所有客户
     */
    List<Customer> findByCustomerNameContaining(String customerName);
}
