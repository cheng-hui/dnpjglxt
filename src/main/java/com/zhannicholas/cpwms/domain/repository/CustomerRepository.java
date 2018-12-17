package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    /**
     * 分页查询
     * @param pageable 分页参数
     * @return  分页参数对应的所有客户
     */
    Page<Customer> findAll(Pageable pageable);

    /**
     * 根据 customerId 查询客户
     * @param customerId    客户id
     * @return  具有 customerId 的客户
     */
    Customer findByCustomerId(int customerId);

    /**
     * 根据 customerName 查询客户---模糊查询
     * @param customerName  客户名称
     * @param pageable 分页参数
     * @return  和 customerName 匹配的所有客户
     */
    Page<Customer> findByCustomerNameContaining(String customerName, Pageable pageable);
}
