package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.model.RecordOut;
import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecordOutService {
    List<RecordOut> findAll();
    Page<RecordOut> findByCustomer(Customer customer, Pageable pageable);
    Page<RecordOut> findByParts(Parts parts, Pageable pageable);
    Page<RecordOut> findByRepository(Respository respository, Pageable pageable);
    RecordOut findOne(int recordId);
    void save(RecordOut recordOut);
    void delete(int recordId);
}
