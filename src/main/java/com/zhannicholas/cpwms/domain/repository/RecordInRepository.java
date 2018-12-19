package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.model.RecordIn;
import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface RecordInRepository extends JpaRepository<RecordIn, Integer> {
    /**
     * 根据 recordId 查找入库记录
     * @param id  入库记录id
     * @return 和 recordId 对应的入库记录
     */
    RecordIn findById(int id);

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

    /**
     * 配件入库---保存操作
     * @param number    配件数量
     * @param time  入库时间
     * @param partsId   配件Id
     * @param supplierId    供应商Id
     * @param repoId    仓库Id
     */
    @Modifying
    @Query(value = "INSERT INTO cpims_record_in(RECORD_SUPPLIERID, RECORD_PARTSID, RECORD_NUMBER, RECORD_TIME, RECORD_PERSON, RECORD_REPOSITORYID) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void saveRecord(int supplierId, int partsId, int number, Date time,  String person, int repoId);
}
