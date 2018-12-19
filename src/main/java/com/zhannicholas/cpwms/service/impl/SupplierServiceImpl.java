package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Supplier;
import com.zhannicholas.cpwms.domain.repository.SupplierRepository;
import com.zhannicholas.cpwms.service.SupplierService;
import com.zhannicholas.cpwms.util.ToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        return ToMapUtil.fromAPage(supplierPage);
    }

    @Override
    public boolean save(Supplier supplier) {
        if(isValidSupplier(supplier)){
            supplierRepository.save(supplier);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> findOne(int supplierId) {
        Supplier supplier = supplierRepository.findBySupplierId(supplierId);
        return ToMapUtil.fromAInstance(supplier);
    }

    @Override
    public Map<String, Object> findBySupplierNameContaining(String supplierName, Pageable pageable) {
        if(pageable == null){
            System.out.println(findAllBySupplierNameContaining(supplierName));
            return findAllBySupplierNameContaining(supplierName);
        }
        Page<Supplier> supplierPage = supplierRepository.findBySupplierNameContaining(supplierName, pageable);
        return ToMapUtil.fromAPage(supplierPage);
    }


    @Override
    public boolean delete(int supplierId) {
        if(isValidSupplierId(supplierId)) {
            supplierRepository.deleteById(supplierId);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> findAllBySupplierNameContaining(String supplierName) {
        return ToMapUtil.fromAList(supplierRepository.findAllBySupplierNameContaining(supplierName));
    }


    /**
     * 检查供应商信息是否满足要求
     * @param supplier  供应商
     * @return  若供应商信息满足要求则返回true，否则返回false
     */
    private boolean isValidSupplier(Supplier supplier){
        if(supplier != null){
            return supplier.getSupplierName() != null &&
                    supplier.getSupplierPerson() != null &&
                    supplier.getSupplierTel() != null &&
                    supplier.getSupplierEmail() != null &&
                    supplier.getSupplierAddress() != null;
        }
        return false;
    }

    /**
     * 检查 supplierId 是否合法
     * @param supplierId    供应商id
     * @return  若供应商id合法则返回true,否则返回false
     */
    private boolean isValidSupplierId(int supplierId){
        Supplier supplier = supplierRepository.findBySupplierId(supplierId);
        return supplier != null;
    }
}
