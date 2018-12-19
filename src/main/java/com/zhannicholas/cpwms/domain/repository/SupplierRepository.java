package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    /**
     * 根据 supplierId 查找供应商
     * @param supplierId    供应商id
     * @return 和 supplierId 对应的供应商
     */
    Supplier findBySupplierId(int supplierId);

    /**
     * 根据 supplierName 查找供应商---模糊查询
     * @param supplierName 供应商名称
     * @param pageable  分页参数
     * @return 和 supplierName 及 pageable 对应的所有供应商
     */
    Page<Supplier> findBySupplierNameContaining(String supplierName, Pageable pageable);

    /**
     * 查询所有供应商---分页
     * @param pageable 分页参数
     * @return 和 pageable 对应的所有供应商
     */
    Page<Supplier> findAll(Pageable pageable);

    /**
     * 查找所有的供应商---模糊查询
     * @param supplierName  供应商名称
     * @return  和 supplierName 匹配的所有供应商
     */
    List<Supplier> findAllBySupplierNameContaining(String supplierName);
}
