package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.model.RecordIn;
import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecordInService {
    List<RecordIn> findAll();
    Page<RecordIn> findBySupplier(Supplier supplier, Pageable pageable);
    Page<RecordIn> findByParts(Parts parts,Pageable pageable);
    Page<RecordIn> findByRepository(Respository respository, Pageable pageable);
    RecordIn findOne(int recordId);
    void save(RecordIn recordIn);
    boolean delete(int recordId);
    boolean saveRecord(int supplierId, int partsId, int number, String person, int repoId);
    boolean isValidRecordInId(int recordInId);
}
