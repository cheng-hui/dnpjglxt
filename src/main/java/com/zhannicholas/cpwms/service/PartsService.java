package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Parts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartsService {
    Page<Parts> findAll(Pageable pageable);
    void save(Parts parts);
    Parts findOne(int partsId);
    List<Parts> findByPartsName(String partsName);
    List<Parts> findByPartsNameContaining(String partsName);
    void delete(int partsId);
}
