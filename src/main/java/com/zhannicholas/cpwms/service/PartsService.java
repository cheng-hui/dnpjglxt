package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Parts;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PartsService {
    Map<String, Object> findAll(Pageable pageable);
    boolean save(Parts parts);
    Map<String, Object> findOne(int partsId);
    Map<String, Object> findByPartsNameContaining(String partsName, Pageable pageable);
    boolean delete(int partsId);
    Map<String, Object> findAllByPartsNameContaining(String partsName);
    boolean isValidParts(Parts parts);
    boolean isValidPartsId(int partsId);
}
