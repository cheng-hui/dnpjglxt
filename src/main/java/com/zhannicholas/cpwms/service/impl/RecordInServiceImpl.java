package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.model.RecordIn;
import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.model.Supplier;
import com.zhannicholas.cpwms.domain.repository.RecordInRepository;
import com.zhannicholas.cpwms.service.RecordInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecordInServiceImpl implements RecordInService {
    private final RecordInRepository recordInRepository;

    @Autowired
    public RecordInServiceImpl(RecordInRepository recordInRepository) {
        this.recordInRepository = recordInRepository;
    }

    @Override
    public List<RecordIn> findAll() {
        return recordInRepository.findAll();
    }

    @Override
    public Page<RecordIn> findBySupplier(Supplier supplier, Pageable pageable) {
        return recordInRepository.findBySupplier(supplier, pageable);
    }

    @Override
    public Page<RecordIn> findByParts(Parts parts, Pageable pageable) {
        return recordInRepository.findByParts(parts, pageable);
    }

    @Override
    public Page<RecordIn> findByRepository(Respository respository, Pageable pageable) {
        return recordInRepository.findByRepository(respository, pageable);
    }

    @Override
    public RecordIn findOne(int recordId) {
        return recordInRepository.findByRecordId(recordId);
    }

    @Override
    public void save(RecordIn recordIn) {
        recordInRepository.save(recordIn);
    }

    @Override
    public void delete(int recordId) {
        recordInRepository.deleteById(recordId);
    }
}
