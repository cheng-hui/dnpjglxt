package com.zhannicholas.cpwms.service;

import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.Map;

public interface RecordService {
    // 入库记录
    boolean saveInRecord(int supplierId, int partsId, int number, String person, int repoId);
    boolean isValidRecordInId(int recordInId);
    Map<String, Object> findInRecordUsingRepoIdAndDate(int repoId, Date startDate, Date endDate, Pageable pageable);
    Map<String, Object> findInRecordUsingDate(Date startDate, Date endDate, Pageable pageable);

    // 出库记录
    boolean saveOutRecord(int customerId, int partsId, int number, String person, int repoId);
    boolean isValidRecordOutId(int recordOutId);
    Map<String, Object> findOutRecordUsingRepoIdAndDate(int repoId, Date startDate, Date endDate, Pageable pageable);
    Map<String, Object> findOutRecordUsingDate(Date startDate, Date endDate, Pageable pageable);

    // 查询所有
    Map<String, Object> findUsingRepoIdAndDate(int repoId, Date startDate, Date endDate, Pageable recordInPageable, Pageable recordOutPageable);
    Map<String, Object> findUsingDate(Date startDate, Date endDate, Pageable recordInPageable, Pageable recordOutPageable);
}
