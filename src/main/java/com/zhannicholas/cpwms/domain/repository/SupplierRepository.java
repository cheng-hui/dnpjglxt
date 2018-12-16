package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    /**
     * 根据 supplierId 查找供应商
     * @param supplierId    供应商id
     * @return 和 supplierId 对应的供应商
     */
    Supplier findBySupplierId(int supplierId);

    /**
     * 根据 supplierName 查找供应商---精确匹配
     * @param supplierName 供应商名称
     * @return 和 supplierName 对应的供应商
     */
    Supplier findBySupplierName(String supplierName);
}
