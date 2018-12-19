package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.repository.PartsRepository;
import com.zhannicholas.cpwms.service.PartsService;
import com.zhannicholas.cpwms.util.ToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PartsServiceImpl implements PartsService {
    private final PartsRepository partsRepository;

    @Autowired
    public PartsServiceImpl(PartsRepository partsRepository) {
        this.partsRepository = partsRepository;
    }


    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        Page<Parts> partsPage = partsRepository.findAll(pageable);
        return ToMapUtil.fromAPage(partsPage);
    }

    @Override
    public boolean save(Parts parts) {
        if(isValidParts(parts)) {
            partsRepository.save(parts);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> findOne(int partsId) {
        Parts parts =  partsRepository.findByPartsId(partsId);
        return ToMapUtil.fromAInstance(parts);
    }

    @Override
    public Map<String, Object> findByPartsNameContaining(String partsName, Pageable pageable) {
        if(pageable == null){
            return findAllByPartsNameContaining(partsName);
        }
        Page<Parts> partsPage = partsRepository.findByPartsNameContaining(partsName, pageable);
        return ToMapUtil.fromAPage(partsPage);
    }

    @Override
    public boolean delete(int partsId) {
        if(isValidPartsId(partsId)) {
            partsRepository.deleteById(partsId);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> findAllByPartsNameContaining(String partsName) {
        return ToMapUtil.fromAList(partsRepository.findAllByPartsNameContaining(partsName));
    }


    /**
     * 检查配件信息是否满足要求
     * @param parts 配件信息
     * @return 若配件信息满足要求则返回true，否则返回false
     */
    public boolean isValidParts(Parts parts){
        if(parts != null){
            return parts.getPartsName() != null && parts.getPartsValue() > 0;
        }
        return false;
    }

    public boolean isValidPartsId(int partsId){
        return partsRepository.findByPartsId(partsId) != null;
    }
}
