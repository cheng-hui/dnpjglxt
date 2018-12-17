package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.repository.PartsRepository;
import com.zhannicholas.cpwms.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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
        List<Parts> partsList = partsPage.getContent();

        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("rows", partsList);
        resultSet.put("total", partsPage.getTotalElements());
        return resultSet;
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
        List<Parts> partsList = new ArrayList<>();
        partsList.add(parts);

        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("rows", partsList);
        resultSet.put("total", 1);
        return resultSet;
    }

    @Override
    public Map<String, Object> findByPartsNameContaining(String partsName, Pageable pageable) {
        Page<Parts> partsPage = partsRepository.findByPartsNameContaining(partsName, pageable);
        List<Parts> partsList = partsPage.getContent();

        System.out.println(partsList);

        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("rows", partsList);
        resultSet.put("total", partsPage.getTotalElements());
        return resultSet;
    }

    @Override
    public boolean delete(int partsId) {
        Parts parts = partsRepository.findByPartsId(partsId);
        if(parts == null){
            return false;
        }
        partsRepository.deleteById(partsId);
        return true;
    }


    /**
     * 检查配件信息是否满足要求
     * @param parts 配件信息
     * @return 若配件信息满足要求则返回true，否则返回false
     */
    private boolean isValidParts(Parts parts){
        if(parts != null){
            return parts.getPartsName() != null && parts.getPartsValue() > 0;
        }
        return false;
    }
}
