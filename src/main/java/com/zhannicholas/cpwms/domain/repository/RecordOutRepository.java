package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface RecordOutRepository extends JpaRepository<RecordOut, Integer> {
    /**
     * 根据 recordId 查找出库记录
     * @param id  出库记录id
     * @return 和 recordId 对应的出库记录
     */
    RecordOut findById(int id);

    /**
     * 查找与 Customer 和 pageable 对应的出库记录
     * @param customer  供应商
     * @param pageable  分页参数
     * @return  与 pageable 和 Customer 匹配的出库记录
     */
    Page<RecordOut> findByCustomer(Customer customer, Pageable pageable);

    /**
     * 查找与 parts 和 pageable 对应的出库记录
     * @param parts 电脑配件
     * @param pageable  分页参数
     * @return  与 parts 和 pageable 对应的出库记录
     */
    Page<RecordOut> findByParts(Parts parts, Pageable pageable);

    /**
     * 查找与 respositoty 和 pageable 对应的出库记录
     * @param respository   仓库
     * @param pageable      分页参数
     * @return  与 respository 和 pageable 对应的出库记录
     */
    Page<RecordOut> findByRepository(Respository respository, Pageable pageable);

    @Modifying
    @Query(value = "INSERT INTO cpims_record_out(RECORD_CUSTOMERID, RECORD_PARTSID, RECORD_NUMBER, RECORD_TIME, RECORD_PERSON, RECORD_REPOSITORYID) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void saveRecord(int customerId, int partsId, int number, Date time, String person, int repoId);
}
