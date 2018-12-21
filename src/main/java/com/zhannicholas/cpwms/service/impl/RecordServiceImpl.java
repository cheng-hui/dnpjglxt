package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.dto.RecordDTO;
import com.zhannicholas.cpwms.domain.repository.RecordInRepository;
import com.zhannicholas.cpwms.domain.repository.RecordOutRepository;
import com.zhannicholas.cpwms.service.*;
import com.zhannicholas.cpwms.util.DateUtil;
import com.zhannicholas.cpwms.util.ToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {
    private final SupplierService supplierService;
    private final PartsService partsService;
    private final RespositoryService respositoryService;
    private final RecordInRepository recordInRepository;
    private final RecordOutRepository recordOutRepository;
    private final StorageService storageService;
    private final CustomerService customerService;

    @Autowired
    public RecordServiceImpl(SupplierService supplierService, PartsService partsService, RespositoryService respositoryService, RecordInRepository recordInRepository, RecordOutRepository recordOutRepository, StorageService storageService, CustomerService customerService) {
        this.supplierService = supplierService;
        this.partsService = partsService;
        this.respositoryService = respositoryService;
        this.recordInRepository = recordInRepository;
        this.recordOutRepository = recordOutRepository;
        this.storageService = storageService;
        this.customerService = customerService;
    }


    @Override
    public boolean saveInRecord(int supplierId, int partsId, int number, String person, int repoId) {
        if(supplierService.isValidSupplierId(supplierId) &&
                partsService.isValidPartsId(partsId) &&
                respositoryService.isValidRepoId(repoId) &&
                person != null &&
                number > 0){
            // 保存到入库表
            recordInRepository.saveRecord(supplierId, partsId, number, DateUtil.fromUtilDate(new java.util.Date()), person, repoId);
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

    @Override
    public Map<String, Object> findInRecordUsingRepoIdAndDate(int repoId, Date startDate, Date endDate, Pageable pageable) {
        return ToMapUtil.fromAPage(recordInRepository.findUsingRepoIdAndDate(repoId, startDate, endDate, pageable));
    }

    @Override
    public Map<String, Object> findInRecordUsingDate(Date startDate, Date endDate, Pageable pageable) {
        return ToMapUtil.fromAPage(recordInRepository.findUsingDate(startDate, endDate, pageable));
    }

    @Override
    public boolean saveOutRecord(int customerId, int partsId, int number, String person, int repoId) {
        if(customerService.isValidCustomerId(customerId) &&
                partsService.isValidPartsId(partsId) &&
                respositoryService.isValidRepoId(repoId) &&
                person != null &&
                number > 0){
            // 保存到出库表
            recordOutRepository.saveRecord(customerId, partsId, number, DateUtil.fromUtilDate(new java.util.Date()), person, repoId);
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

    @Override
    public Map<String, Object> findOutRecordUsingRepoIdAndDate(int repoId, Date startDate, Date endDate, Pageable pageable) {
        return ToMapUtil.fromAPage(recordOutRepository.findUsingRepoIdAndDate(repoId, startDate, endDate, pageable));
    }

    @Override
    public Map<String, Object> findOutRecordUsingDate(Date startDate, Date endDate, Pageable pageable) {
        return ToMapUtil.fromAPage(recordOutRepository.findUsingDate(startDate, endDate, pageable));
    }

    @Override
    public Map<String, Object> findUsingRepoIdAndDate(int repoId, Date startDate, Date endDate, Pageable recordInPageable, Pageable recordOutPageable) {
        List<RecordDTO> recordInList = recordInRepository.findUsingRepoIdAndDate(repoId, startDate, endDate, recordInPageable).getContent();
        List<RecordDTO> recordOutList = recordOutRepository.findUsingRepoIdAndDate(repoId, startDate, endDate, recordOutPageable).getContent();

        List<RecordDTO> recordDTOList = new ArrayList<>();
        recordDTOList.addAll(recordInList);
        recordDTOList.addAll(recordOutList);
        return ToMapUtil.fromAList(recordDTOList);
    }

    @Override
    public Map<String, Object> findUsingDate(Date startDate, Date endDate, Pageable recordInPageable, Pageable recordOutPageable) {
        List<RecordDTO> recordInList = recordInRepository.findUsingDate(startDate, endDate, recordInPageable).getContent();
        List<RecordDTO> recordOutList = recordOutRepository.findUsingDate(startDate, endDate, recordOutPageable).getContent();

        List<RecordDTO> recordDTOList = new ArrayList<>();
        recordDTOList.addAll(recordInList);
        recordDTOList.addAll(recordOutList);
        return ToMapUtil.fromAList(recordDTOList);
    }
}
