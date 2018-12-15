package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Customer, Integer> {
}
