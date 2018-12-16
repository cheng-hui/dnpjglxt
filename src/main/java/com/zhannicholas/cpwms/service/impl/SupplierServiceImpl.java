package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Supplier;
import com.zhannicholas.cpwms.domain.repository.SupplierRepository;
import com.zhannicholas.cpwms.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public Supplier findOne(int supplierId) {
        return supplierRepository.findBySupplierId(supplierId);
    }

    @Override
    public Supplier findBySupplierName(String supplierName) {
        return supplierRepository.findBySupplierName(supplierName);
    }

    @Override
    public void delete(int supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
