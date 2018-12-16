package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Parts;

import java.util.List;

public interface PartsService {
    List<Parts> findAll();
    void save(Parts parts);
    Parts findOne(int partsId);
    List<Parts> findByPartsName(String partsName);
    List<Parts> findByPartsNameContaining(String partsName);
    void delete(int partsId);
}
