package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Customer;
import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.model.RecordOut;
import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.repository.RecordOutRepository;
import com.zhannicholas.cpwms.service.*;
import com.zhannicholas.cpwms.util.DateUtil;
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
    private final CustomerService customerService;
    private final PartsService partsService;
    private final RespositoryService respositoryService;
    private final StorageService storageService;

    @Autowired
    public RecordOutServiceImpl(RecordOutRepository recordOutRepository, CustomerService customerService, PartsService partsService, RespositoryService respositoryService, StorageService storageService) {
        this.recordOutRepository = recordOutRepository;
        this.customerService = customerService;
        this.partsService = partsService;
        this.respositoryService = respositoryService;
        this.storageService = storageService;
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
        return recordOutRepository.findById(recordId);
    }

    @Override
    public void save(RecordOut recordOut) {
        recordOutRepository.save(recordOut);
    }

    @Override
    public boolean delete(int recordId) {
        if(isValidRecordOutId(recordId)) {
            recordOutRepository.deleteById(recordId);
            return true;
        }
        return false;
    }

    @Override
    public boolean saveRecord(int customerId, int partsId, int number, String person, int repoId) {
        if(customerService.isValidCustomerId(customerId) &&
                partsService.isValidPartsId(partsId) &&
                respositoryService.isValidRepoId(repoId) &&
                person != null &&
                number > 0){
            // 保存到出库表
            recordOutRepository.saveRecord(customerId, partsId, number, DateUtil.fromUtilDate(), person, repoId);
            // 更新库存
            storageService.decreaseStorage(partsId, repoId, number);
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidRecordOutId(int recordOutId) {
        return recordOutRepository.findById(recordOutId) != null;
    }
}
