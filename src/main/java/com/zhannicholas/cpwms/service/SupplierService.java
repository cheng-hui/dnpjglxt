package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Supplier;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface SupplierService {
    Map<String, Object> findAll(Pageable pageable);
    boolean save(Supplier supplier);
    Map<String, Object> findOne(int supplierId);
    int countSupplierName(String supplierName);
    Map<String, Object> findBySupplierNameContaining(String supplierName, Pageable pageable);
    boolean delete(int supplierId);
    Map<String, Object> findAllBySupplierNameContaining(String supplierName);
    boolean isValidSupplier(Supplier supplier);
    boolean isValidSupplierId(int supplierId);
}
