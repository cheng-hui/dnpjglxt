package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.model.RecordIn;
import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordInRepository extends JpaRepository<RecordIn, Integer> {
    /**
     * 根据 recordId 查找入库记录
     * @param recordId  入库记录id
     * @return 和 recordId 对应的入库记录
     */
    RecordIn findByRecordId(int recordId);

    /**
     * 查找与 supplier 和 pageable 对应的入库记录
     * @param supplier  供应商
     * @param pageable  分页参数
     * @return  与 pageable 和 supplier 匹配的入库记录
     */
    Page<RecordIn> findBySupplier(Supplier supplier, Pageable pageable);

    /**
     * 查找与 parts 和 pageable 对应的入库记录
     * @param parts 电脑配件
     * @param pageable  分页参数
     * @return  与 parts 和 pageable 对应的入库记录
     */
    Page<RecordIn> findByParts(Parts parts, Pageable pageable);

    /**
     * 查找与 respositoty 和 pageable 对应的入库记录
     * @param respository   仓库
     * @param pageable      分页参数
     * @return  与 respository 和 pageable 对应的入库记录
     */
    Page<RecordIn> findByRepository(Respository respository, Pageable pageable);
}
