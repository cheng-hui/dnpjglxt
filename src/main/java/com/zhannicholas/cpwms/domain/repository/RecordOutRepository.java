package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.dto.RecordDTO;
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

    @Modifying
    @Query(value = "INSERT INTO cpims_record_out(RECORD_CUSTOMERID, RECORD_PARTSID, RECORD_NUMBER, RECORD_TIME, RECORD_PERSON, RECORD_REPOSITORYID) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void saveRecord(int customerId, int partsId, int number, Date time, String person, int repoId);

    /**
     * 查询指定仓库在某个时间段内的出库记录
     * @param repoId    仓库Id
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param pageable  分页参数
     * @return  指定仓库在某个时间段内的出库记录
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.RecordDTO(ro.id, ct.customerName, pt.partsName, rp.repoId, ro.number, ro.time, ro.person, 'Out') " +
            "from RecordOut ro, Respository rp, Parts pt, Customer ct " +
            "where ro.parts.partsId = pt.partsId and ro.customer.customerId = ct.customerId and ro.repository.repoId = rp.repoId and rp.repoId= ?1 and ro.time between ?2 and ?3")
    Page<RecordDTO> findUsingRepoIdAndDate(int repoId, Date startDate, Date endDate, Pageable pageable);

    /**
     * 查询所有仓库在某个时间段内的出库记录
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param pageable  分页参数
     * @return  所有仓库在某个时间段内的出库记录
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.RecordDTO(ro.id, ct.customerName, pt.partsName, rp.repoId, ro.number, ro.time, ro.person, 'Out') " +
            "from RecordOut ro, Respository rp, Parts pt, Customer ct " +
            "where ro.parts.partsId = pt.partsId and ro.customer.customerId = ct.customerId and ro.repository.repoId = rp.repoId and ro.time between ?1 and ?2")
    Page<RecordDTO> findUsingDate(Date startDate, Date endDate, Pageable pageable);


}
