package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.model.RecordIn;
import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.model.Supplier;
import com.zhannicholas.cpwms.domain.repository.RecordInRepository;
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
public class RecordInServiceImpl implements RecordInService {
    private final RecordInRepository recordInRepository;
    private final SupplierService supplierService;
    private final PartsService partsService;
    private final RespositoryService respositoryService;
    private final StorageService storageService;

    @Autowired
    public RecordInServiceImpl(RecordInRepository recordInRepository, SupplierService supplierService, PartsService partsService, RespositoryService respositoryService, StorageService storageService) {
        this.recordInRepository = recordInRepository;
        this.supplierService = supplierService;
        this.partsService = partsService;
        this.respositoryService = respositoryService;
        this.storageService = storageService;
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
        return recordInRepository.findById(recordId);
    }

    @Override
    public void save(RecordIn recordIn) {
        recordInRepository.save(recordIn);
    }

    @Override
    public boolean delete(int recordId) {
        if(isValidRecordInId(recordId)) {
            recordInRepository.deleteById(recordId);
            return true;
        }
        return false;
    }

    @Override
    public boolean saveRecord(int supplierId, int partsId, int number, String person, int repoId) {
        if(supplierService.isValidSupplierId(supplierId) &&
                partsService.isValidPartsId(partsId) &&
                respositoryService.isValidRepoId(repoId) &&
                person != null &&
                number > 0){
            // 保存到入库表
            recordInRepository.saveRecord(supplierId, partsId, number, DateUtil.fromUtilDate(), person, repoId);
            // 更新库存
            storageService.increaseStorage(partsId, repoId, number);
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidRecordInId(int recordInId) {
        return recordInRepository.findById(recordInId) != null;
    }
}
