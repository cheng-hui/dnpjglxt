package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Customer;
import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.model.RecordOut;
import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.repository.RecordOutRepository;
import com.zhannicholas.cpwms.service.RecordOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecordOutServiceImpl implements RecordOutService {
    private final RecordOutRepository recordOutRepository;

    @Autowired
    public RecordOutServiceImpl(RecordOutRepository recordOutRepository) {
        this.recordOutRepository = recordOutRepository;
    }

    @Override
    public List<RecordOut> findAll() {
        return recordOutRepository.findAll();
    }

    @Override
    public Page<RecordOut> findByCustomer(Customer customer, Pageable pageable) {
        return recordOutRepository.findByCustomer(customer, pageable);
    }

    @Override
    public Page<RecordOut> findByParts(Parts parts, Pageable pageable) {
        return recordOutRepository.findByParts(parts, pageable);
    }

    @Override
    public Page<RecordOut> findByRepository(Respository respository, Pageable pageable) {
        return recordOutRepository.findByRepository(respository, pageable);
    }

    @Override
    public RecordOut findOne(int recordId) {
        return recordOutRepository.findByRecordId(recordId);
    }

    @Override
    public void save(RecordOut recordOut) {
        recordOutRepository.save(recordOut);
    }

    @Override
    public void delete(int recordId) {
        recordOutRepository.deleteById(recordId);
    }
}
