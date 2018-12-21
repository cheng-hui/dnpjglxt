package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.dto.RecordDTO;
import com.zhannicholas.cpwms.domain.model.RecordIn;
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

    /**
     * 查询指定仓库在某个时间段内的入库记录
     * @param repoId    仓库Id
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param pageable  分页参数
     * @return  指定仓库在某个时间段内的入库记录
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.RecordDTO(ri.id, sp.supplierName, pt.partsName, rp.repoId, ri.number, ri.time, ri.person, 'In') " +
            "from RecordIn ri, Respository rp, Parts pt, Supplier sp " +
            "where ri.parts.partsId = pt.partsId and ri.supplier.supplierId = sp.supplierId and ri.repository.repoId = rp.repoId and rp.repoId = ?1 and ri.time between ?2 and ?3")
    Page<RecordDTO> findUsingRepoIdAndDate(int repoId, Date startDate, Date endDate, Pageable pageable);

    /**
     * 查询所有仓库在某一个时间段内的入库记录
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param pageable  分页参数
     * @return  所有仓库在某个时间段内的所有入库记录
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.RecordDTO(ri.id, sp.supplierName, pt.partsName, rp.repoId, ri.number, ri.time, ri.person, 'In') " +
            "from RecordIn ri, Respository rp, Parts pt, Supplier sp " +
            "where ri.parts.partsId = pt.partsId and ri.supplier.supplierId = sp.supplierId and ri.repository.repoId = rp.repoId and ri.time between ?1 and ?2")
    Page<RecordDTO> findUsingDate(Date startDate, Date endDate, Pageable pageable);
}
