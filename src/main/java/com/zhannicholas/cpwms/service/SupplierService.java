package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> findAll();
    void save(Supplier supplier);
    Supplier findOne(int supplierId);
    Supplier findBySupplierName(String supplierName);
    void delete(int supplierId);
}
